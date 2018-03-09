package swan.biz.readhub.adapter.item

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View
import com.github.ajalt.timberkt.i
import swan.biz.readhub.R
import swan.biz.readhub.model.Topic

import kotlinx.android.synthetic.main.read_hub_master_topic_body.*
import kotlinx.android.synthetic.main.read_hub_master_topic_body.view.*
import swan.atom.core.base.AtomCoreBaseFasterItem
import swan.biz.readhub.activity.ReadHubMasterActivity
import swan.biz.readhub.activity.ReadHubTopicActivity

/**
 * Created by stephen on 05/03/2018.
 */
class ReadHubTopicListItem(topic: Topic): AtomCoreBaseFasterItem<Topic, ReadHubTopicListItem, ReadHubTopicListItem.ReadHubTopicListHolder>(topic) {

    override fun getType(): Int {
        return R.id.readHubResAdapterItem_TopicList
    }

    override fun getViewHolder(v: View?): ReadHubTopicListHolder {
        return ReadHubTopicListHolder(v!!)
    }

    override fun getLayoutRes(): Int {
        return R.layout.read_hub_master_topic_body
    }

    override fun bindView(holder: ReadHubTopicListHolder?, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)
        holder?.onBindView(null, src)
    }

    class ReadHubTopicListHolder(itemView: View): AtomCoreBaseFasterItem.AtomPubFastAdapterItemHolder<Topic>(itemView) {

        override fun onBindView(context: Context?, topic: Topic?) {
            ViewCompat.setTransitionName(itemView.bodyTitle, "guoxin")
            topic?.let {
                itemView.bodyTitle.text = "${adapterPosition}. ${it.title}"
                itemView.setOnClickListener {
                    var options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            ReadHubMasterActivity.context!!, itemView.bodyTitle, "guoxin"
                    )

                    var intent: Intent = Intent(ReadHubMasterActivity.context, ReadHubTopicActivity::class.java)
                    intent.putExtra("id", topic.id)
                    intent.putExtra("title", topic.title)
                    intent.putExtra("summary", topic.summary)

                    ReadHubMasterActivity.context!!.startActivity(intent, options.toBundle())
                }
            }
        }
    }
}