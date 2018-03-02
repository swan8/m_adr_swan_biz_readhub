package swan.biz.readhub.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.text.format.DateUtils
import com.github.ajalt.timberkt.Timber
import kotlinx.android.synthetic.main.read_hub_master.*
import swan.biz.readhub.R
import swan.biz.readhub.adapter.ReadHubMasterAdapter
import swan.biz.readhub.network.ReadHubRequestDelegate
import swan.biz.readhub.network.SchedulerTransformer
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by stephen on 01/03/2018.
 */
class ReadHubMasterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_hub_master)

        Timber.plant(Timber.DebugTree())

        masterContentContainer.adapter = ReadHubMasterAdapter(applicationContext, supportFragmentManager)
        masterContentContainer.offscreenPageLimit = ReadHubMasterAdapter.POSITION.ALL
        masterContentContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(masterTabContainer))

        masterTabContainer.setupWithViewPager(masterContentContainer)
        masterTabContainer.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(masterContentContainer))

        ReadHubRequestDelegate.ReadHub()!!.postRequestReadHubTopic("4djhWVo81n9")
                .compose(SchedulerTransformer())
                .subscribe({
                    Timber.e {
                        "postRequestReadHubTopic::${it?.createdAt} == ${getCreateDate(it.createdAt)}"
                    }
                }, {
                    it.printStackTrace()
                })
    }

    fun getCreateDate(createdAt: String?): String? {
        createdAt?.let {
            var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            format.timeZone = TimeZone.getTimeZone("UTC+08:00")

            var date: Date = format.parse(createdAt)
            date?.let {
                DateUtils.getRelativeTimeSpanString(it.time, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)
            }?.let {
                return it.toString()
            }
        }

        return null
    }
}