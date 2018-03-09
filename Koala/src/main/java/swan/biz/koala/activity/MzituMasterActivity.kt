package swan.biz.koala.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import com.fivehundredpx.greedolayout.GreedoLayoutManager
import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.mzitu_album_list.*
import swan.atom.core.base.AtomCoreBaseSchedulerTransformer
import swan.biz.koala.R
import swan.biz.koala.adapter.item.MzituAlbumListItem
import swan.biz.koala.model.MzituImage
import swan.biz.koala.model.MzituModelConverter
import swan.biz.koala.network.MzituRequestDelegate
import java.util.*


/**
 * Created by stephen on 18-3-9.
 */
class MzituMasterActivity: AppCompatActivity(), GreedoLayoutSizeCalculator.SizeCalculatorDelegate {

    override fun aspectRatioForIndex(position: Int): Double {
        return (Random().nextInt(25) + 55.0) / 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mzitu_album_list)

        MzituRequestDelegate.Mzitu()?.postRequestMzituHot(1)!!
                .compose(AtomCoreBaseSchedulerTransformer())
                .subscribe({
                    val data: MutableList<MzituImage> = MzituModelConverter().mConverterList(it)

                    val fastItemAdapter: FastItemAdapter<MzituAlbumListItem> = FastItemAdapter<MzituAlbumListItem>()
                    albumListContainer.let {
                        val layoutManager = GreedoLayoutManager(this@MzituMasterActivity)
                        layoutManager.setMaxRowHeight(dpToPx(256f, applicationContext))

                        it.layoutManager = layoutManager
                        it.adapter = fastItemAdapter
                        it.addItemDecoration(GreedoSpacingItemDecoration(10))
                        it.setHasFixedSize(true)
                    }

                    val items: MutableList<MzituAlbumListItem> = mutableListOf()
                    data.map {
                        items.add(MzituAlbumListItem(it))
                    }

                    fastItemAdapter.set(items)
                }, {

                })
    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }
}