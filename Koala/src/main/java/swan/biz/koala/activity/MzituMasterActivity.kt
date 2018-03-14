package swan.biz.koala.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.TextView
import kotlinx.android.synthetic.main.mzt_master.*
import swan.atom.core.base.AtomCoreBaseActivity
import swan.biz.koala.R
import swan.biz.koala.adapter.MztMasterTabAdapter
import swan.biz.koala.model.MztUnit
import swan.biz.koala.vm.MztMasterSortedViewModel

/**
 * Created by stephen on 18-3-9.
 */
class MzituMasterActivity: AtomCoreBaseActivity() {

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
            it.addOnTabSelectedListener(OnMasterTabSelectedListener(masterPagerContainer))
        }

        val vm: MztMasterSortedViewModel = ViewModelProviders.of(this).get(MztMasterSortedViewModel::class.java)
        vm.postList.observe(this, android.arch.lifecycle.Observer {
            resetMasterTopLayout(it?.top)
        })
    }

    private fun resetMasterTopLayout(top: MutableList<MztUnit>?): Unit {
        val textViews: Array<TextView> = arrayOf(
                masterPostTopGolden, masterPostTopSilver, masterPostTopBronze
        )

        top?.indices?.forEach {
            if (it < textViews.size) {
                textViews[it].text = top[it].title
                textViews[it].setOnClickListener(this@MzituMasterActivity)
            }
        }
    }

    inner class OnMasterTabSelectedListener constructor(viewPager: ViewPager) : TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            super.onTabSelected(tab)
        }
    }
}