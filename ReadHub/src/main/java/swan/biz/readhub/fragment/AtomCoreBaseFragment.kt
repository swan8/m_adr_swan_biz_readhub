package swan.biz.readhub.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by stephen on 01/03/2018.
 */
open abstract class AtomCoreBaseFragment: Fragment() {

    abstract fun inflateLayoutResource(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(inflateLayoutResource(), null)
    }
}