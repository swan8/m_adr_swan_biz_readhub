package swan.biz.readhub.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import kotlinx.android.synthetic.main.read_hub_topic.*
import swan.biz.readhub.R
import swan.biz.readhub.network.ReadHubRequestDelegate
import swan.biz.readhub.network.SchedulerTransformer

/**
 * Created by stephen on 07/03/2018.
 */
class ReadHubTopicActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.read_hub_topic)

        bodyTitle.text = intent.getStringExtra("title")

        ReadHubRequestDelegate.ReadHub()!!.postRequestReadHubTopic(intent.getStringExtra("id"))
                .compose(SchedulerTransformer())
                .subscribe({
                    bodyContent.text = it.summary
                }, {
                    bodyContent.text = it.message
                })
    }
}