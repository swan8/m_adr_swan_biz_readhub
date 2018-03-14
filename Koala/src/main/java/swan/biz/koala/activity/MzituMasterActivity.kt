package swan.biz.koala.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.mzt_master.*
import me.dkzwm.widget.srl.SmoothRefreshLayout
import org.jsoup.Jsoup
import swan.atom.core.base.AtomCoreBaseSchedulerTransformer
import swan.biz.koala.R
import swan.biz.koala.adapter.MztMasterTabAdapter
import swan.biz.koala.adapter.item.MzituAlbumListItem
import swan.biz.koala.model.MztDataCenter
import swan.biz.koala.network.IMztNodeField
import swan.biz.koala.network.MzituRequestDelegate
import java.util.*


/**
 * Created by stephen on 18-3-9.
 */
class MzituMasterActivity: AppCompatActivity(), GreedoLayoutSizeCalculator.SizeCalculatorDelegate, SmoothRefreshLayout.OnRefreshListener {

    var pageNo: Int = 1

    var fastItemAdapter: FastItemAdapter<MzituAlbumListItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mzt_master)

        masterPagerContainer.let {
            it.adapter = MztMasterTabAdapter(applicationContext, supportFragmentManager)
            it.offscreenPageLimit = MztMasterTabAdapter.POSITION.ALL
            it.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(masterTabContainer))
        }

        masterTabContainer.let {
            it.setupWithViewPager(masterPagerContainer)
            it.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(masterPagerContainer))
        }

        onRefreshBegin(true)

//        albumSrfLayoutContainer.let {
//            it.setOnRefreshListener(this@MzituMasterActivity)
//            it.autoRefresh()
//        }
//
//        albumListContainer.let {
//            val layoutManager = GreedoLayoutManager(this@MzituMasterActivity)
//            layoutManager.setMaxRowHeight(dpToPx(256f, applicationContext))
//
//            it.layoutManager = layoutManager
//            it.addItemDecoration(GreedoSpacingItemDecoration(4))
//            it.setHasFixedSize(true)
//
//            fastItemAdapter = FastItemAdapter<MzituAlbumListItem>()
//            it.adapter = fastItemAdapter
//        }
    }

    override fun aspectRatioForIndex(index: Int): Double {
        return (Random().nextInt(25) + 55.0) / 100
    }

    override fun onRefreshBegin(isRefresh: Boolean) {
        when (isRefresh) {
            true -> pageNo = 1
            false -> ++ pageNo
        }

        MzituRequestDelegate.Mzitu()?.postRequestMztIndex(pageNo)!!
                .compose(AtomCoreBaseSchedulerTransformer())
                .subscribe({
                    val dataCenter: MztDataCenter = MztDataCenter()
                    it.let {
                        Jsoup.parse(it)
                    }?.let {
                        dataCenter.searchPlaceHolder = it.selectFirst(IMztNodeField.SEARCH_INPUT).attr(IMztNodeField.NODE_PLACEHOLDER)
                        dataCenter.pageNavigationWithDocument(it)
                        dataCenter.topWithElements(it.select(IMztNodeField.WIDGET_TOP))
                        dataCenter.guessWithElements(it.select(IMztNodeField.WIDGET_LIKE_GUESS))
                        dataCenter.loveWithElements(it.select(IMztNodeField.WIDGET_LIKE_LOVE))
                        dataCenter.postListWithElements(it.select(IMztNodeField.POST_LIST))
                    }

                    val items: MutableList<MzituAlbumListItem> = mutableListOf()
                    dataCenter.postList.map {
                        items.add(MzituAlbumListItem(it))
                    }

//                    fastItemAdapter?.add(items)

//                    albumSrfLayoutContainer.let {
//                        it.setDisableLoadMore(false)
//                        it.refreshComplete()
//                    }
                }, {
//                    albumSrfLayoutContainer.refreshComplete()
                })
    }

    override fun onRefreshComplete(isSuccessful: Boolean) {

    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }
}