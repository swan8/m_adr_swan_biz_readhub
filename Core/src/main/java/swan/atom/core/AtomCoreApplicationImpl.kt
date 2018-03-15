package swan.atom.core

import android.app.Application
import android.content.Context
import swan.atom.core.basectx.SwanAtomApplicationImpl
import java.lang.ref.WeakReference

/**
 * Created by stephen on 18-3-14.
 */
class AtomCoreApplicationImpl : SwanAtomApplicationImpl {

    companion object {
        lateinit var instance: AtomCoreApplicationImpl
            private set

        fun getContext(): Context? {
            return instance?.getContext()
        }
    }

    private var context: WeakReference<Context>? = null

    override fun onCreate(application: Application) {
        instance = this
        instance.context = WeakReference(application.applicationContext)
    }

    override fun getContext(): Context? {
        context?.get()?.let {
            return it
        }

        return null
    }

}