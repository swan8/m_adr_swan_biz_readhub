package swan.biz.koala.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fivehundredpx.greedolayout.GreedoLayoutManager
import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.mzt_master_sorted.*
import me.dkzwm.widget.srl.SmoothRefreshLayout
import swan.atom.core.base.AtomCoreBaseFragment
import swan.biz.koala.KoalaApplicationImpl
import swan.biz.koala.R
import swan.biz.koala.adapter.item.MzituAlbumListItem
import swan.biz.koala.network.IMzituRequestService
import swan.biz.koala.vm.MztMasterSortedViewModel
import java.util.*

/**
 * Created by stephen on 13/03/2018.
 */
class MztMasterSortedFragment : AtomCoreBaseFragment(), SmoothRefreshLayout.OnRefreshListener, GreedoLayoutSizeCalculator.SizeCalculatorDelegate {

    var fastItemAdapter: FastItemAdapter<MzituAlbumListItem>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.mzt_master_sorted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sortedFilterNew.setOnClickListener(this)
        sortedFilterHot.setOnClickListener(this)
        sortedFilterRecommend.setOnClickListener(this)

        masterSortedRefreshContainer.setOnRefreshListener(this)

        masterSortedRecyclerContainer.let {
            val layoutManager = GreedoLayoutManager(this)
            layoutManager.setMaxRowHeight(dpToPx(256f, KoalaApplicationImpl.getContext()!!))

            it.layoutManager = layoutManager
            it.addItemDecoration(GreedoSpacingItemDecoration(4))
            it.setHasFixedSize(true)

            fastItemAdapter = FastItemAdapter<MzituAlbumListItem>()
            it.adapter = fastItemAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val vm: MztMasterSortedViewModel = ViewModelProviders.of(activity!!).get(MztMasterSortedViewModel::class.java)
        vm.category.observe(this, android.arch.lifecycle.Observer {
            onRefreshBegin(true)
        })

        vm.postList.observe(this, android.arch.lifecycle.Observer {
            val items: MutableList<MzituAlbumListItem> = mutableListOf()
            it?.postList?.map {
                items.add(MzituAlbumListItem(it))
            }

            if (vm.pageNo == 0) {
                fastItemAdapter?.clear()
            }

            fastItemAdapter?.add(items)

            masterSortedRefreshContainer.setDisableLoadMore(! it?.pageNavigationHasNext!!)
            masterSortedRefreshContainer.refreshComplete()
        })

        vm.category.value = IMzituRequestService.CATEGORY.INDEX
    }

    override fun onClick(v: View?) {
        val vm: MztMasterSortedViewModel = ViewModelProviders.of(activity!!).get(MztMasterSortedViewModel::class.java)
        when (v?.id) {
            R.id.sortedFilterNew ->
                vm.resetMasterSortedCategory(IMzituRequestService.CATEGORY.INDEX)
            R.id.sortedFilterHot ->
                vm.resetMasterSortedCategory(IMzituRequestService.CATEGORY.HOT)
            R.id.sortedFilterRecommend ->
                vm.resetMasterSortedCategory(IMzituRequestService.CATEGORY.BEST)
            else ->
                super.onClick(v)
        }
    }

    override fun onRefreshBegin(isRefresh: Boolean) {
        activity?.let {
            val vm: MztMasterSortedViewModel = ViewModelProviders.of(it).get(MztMasterSortedViewModel::class.java)
            vm.loadMasterDataCenter(isRefresh)
        }
    }

    override fun onRefreshComplete(isSuccessful: Boolean) {

    }

    override fun aspectRatioForIndex(p0: Int): Double {
        return (Random().nextInt(25) + 55.0) / 100
    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }
}