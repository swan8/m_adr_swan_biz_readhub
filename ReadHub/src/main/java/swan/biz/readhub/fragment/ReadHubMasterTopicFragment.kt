package swan.biz.readhub.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.read_hub_master_topic.*
import swan.biz.readhub.R

/**
 * Created by stephen on 01/03/2018.
 */
class ReadHubMasterTopicFragment : AtomCoreBaseFragment() {

    override fun inflateLayoutResource(): Int {
        return R.layout.read_hub_master_topic
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        masterTopicContainer.layoutManager = LinearLayoutManager(context)
    }
}