package swan.biz.koala.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import swan.biz.koala.R
import swan.biz.koala.model.MzituImage
import swan.biz.koala.model.MzituModelConverter
import swan.biz.koala.network.MzituRequestDelegate

/**
 * Created by stephen on 18-3-9.
 */
class MzituMasterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mzitu_master)

        MzituRequestDelegate.Mzitu()?.postRequestMzituHot(1)!!
//                .compose(SchedulerTransformer())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val list: MutableList<MzituImage> = MzituModelConverter().mConverterList(it)
                    println("list::${list}")
                }, {

                })
    }
}