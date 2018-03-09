package swan.biz.readhub.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.read_hub_master_topic.*
import swan.biz.readhub.R
import swan.biz.readhub.adapter.item.ReadHubTopicListItem
import swan.biz.readhub.model.Topic
import swan.biz.readhub.network.ReadHubRequestDelegate
import swan.biz.readhub.network.SchedulerTransformer


/**
 * Created by stephen on 01/03/2018.
 */
class ReadHubMasterTopicFragment : AtomCoreBaseFragment() {

    override fun inflateLayoutResource(): Int {
        return R.layout.read_hub_master_topic
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ReadHubRequestDelegate.ReadHub()!!.postRequestReadHubTopic(null, 10)
                .compose(SchedulerTransformer())
                .subscribe({
                    it?.data?.let {
                        success(it)
                    }
                }, {
                    it.printStackTrace()
                })
    }

    fun success(data: Array<Topic>): Unit {
        val fastItemAdapter: FastItemAdapter<ReadHubTopicListItem> = FastItemAdapter<ReadHubTopicListItem>()
        masterTopicContainer.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = fastItemAdapter
            it.setHasFixedSize(true)
        }

        val items: MutableList<ReadHubTopicListItem> = mutableListOf()
        data.map {
            items.add(ReadHubTopicListItem(it))
        }

        fastItemAdapter.set(items)
    }
}