package swan.biz.readhub.activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.text.format.DateUtils
import android.view.Window
import com.github.ajalt.timberkt.Timber
import kotlinx.android.synthetic.main.read_hub_master.*
import swan.biz.readhub.R
import swan.biz.readhub.adapter.ReadHubMasterAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by stephen on 01/03/2018.
 */
class ReadHubMasterActivity: AppCompatActivity() {

    companion object {
        var context: Activity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.read_hub_master)

        context = this

        Timber.plant(Timber.DebugTree())

        masterContentContainer.adapter = ReadHubMasterAdapter(applicationContext, supportFragmentManager)
        masterContentContainer.offscreenPageLimit = ReadHubMasterAdapter.POSITION.ALL
        masterContentContainer.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(masterTabContainer))

        masterTabContainer.setupWithViewPager(masterContentContainer)
        masterTabContainer.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(masterContentContainer))
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