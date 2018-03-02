package swan.biz.readhub.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.read_hub_master.*
import swan.biz.readhub.R
import swan.biz.readhub.adapter.ReadHubMasterAdapter
import swan.biz.readhub.network.ReadHubRequestDelegate
import swan.biz.readhub.network.SchedulerTransformer

/**
 * Created by stephen on 01/03/2018.
 */
class ReadHubMasterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_hub_master)

        masterContentContainer.adapter = ReadHubMasterAdapter(applicationContext, supportFragmentManager)
        masterContentContainer.offscreenPageLimit = ReadHubMasterAdapter.POSITION.ALL
        masterContentContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(masterTabContainer))

        masterTabContainer.setupWithViewPager(masterContentContainer)
        masterTabContainer.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(masterContentContainer))

        ReadHubRequestDelegate.ReadHub()!!.postRequestReadHubNew(null, 2)
                .compose(SchedulerTransformer())
                .subscribe({
                    println("postRequestReadHubNew::${it}")
                }, {
                    it.printStackTrace()
                })
    }
}