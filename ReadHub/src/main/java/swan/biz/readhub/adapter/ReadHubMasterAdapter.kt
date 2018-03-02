package swan.biz.readhub.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import swan.biz.readhub.R
import swan.biz.readhub.fragment.ReadHubMasterMeFragment
import swan.biz.readhub.fragment.ReadHubMasterNewsFragment
import swan.biz.readhub.fragment.ReadHubMasterTopicFragment

/**
 * Created by stephen on 01/03/2018.
 */
class ReadHubMasterAdapter constructor(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    interface POSITION {

        companion object {

            const val TOPIC = 0

            const val NEWS = 1

            const val ME = 2

            const val ALL = 3
        }
    }

    var context: Context? = null

    var masterTopicFragment: ReadHubMasterTopicFragment? = null

    var masterNewsFragment: ReadHubMasterNewsFragment? = null

    var masterMeFragment: ReadHubMasterMeFragment? = null

    constructor(context: Context, fragmentManager: FragmentManager): this(fragmentManager) {
        this.context = context
    }

    init {
        masterTopicFragment = ReadHubMasterTopicFragment()
        masterNewsFragment = ReadHubMasterNewsFragment()
        masterMeFragment = ReadHubMasterMeFragment()
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            POSITION.TOPIC -> return masterTopicFragment
            POSITION.NEWS -> return masterNewsFragment
            POSITION.ME -> return masterMeFragment
        }

        return masterTopicFragment
    }

    override fun getCount(): Int {
        return POSITION.ALL
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var resource: Pair<Drawable?, Int>? = null
        when (position) {
            POSITION.TOPIC ->
                resource = Pair(ContextCompat.getDrawable(context!!, R.drawable.read_hub_ic_master_tab_topic), R.string.swan_biz_readHubMasterTabTopic)
            POSITION.NEWS ->
                resource = Pair(ContextCompat.getDrawable(context!!, R.drawable.read_hub_ic_master_tab_news), R.string.swan_biz_readHubMasterTabNews)
            POSITION.ME ->
                resource = Pair(ContextCompat.getDrawable(context!!, R.drawable.read_hub_ic_master_tab_me), R.string.swan_biz_readHubMasterTabMe)
        }

        if (null == resource) {
            return null
        } else{
            resource.first?.setBounds(0, 0, resource.first!!.intrinsicWidth, resource.first!!.intrinsicHeight)

            var builder = SpannableString("q\n${context!!.getString(resource.second)}")
            builder.setSpan(ImageSpan(resource.first, ImageSpan.ALIGN_BOTTOM), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return builder
        }
    }
}