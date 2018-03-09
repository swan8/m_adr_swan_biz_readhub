package swan.atom.core.basectx

import android.app.Application

/**
 * Created by stephen on 18-2-13.
 */
open abstract class SwanAtomBaseApplication: Application() {

    companion object {

        private val module: MutableList<String> = mutableListOf()
    }

    abstract fun initModuleApplicationImpl(module: MutableList<String>)

    override fun onCreate() {
        super.onCreate()

        initModuleApplicationImpl(module)

        initImpl()
    }

    private fun initImpl() {
        module.map {
            val clazz: Class<*>? = Class.forName(it)
            val newInstance: Any? = clazz?.newInstance()
            if (newInstance is SwanAtomApplicationImpl) {
                newInstance.onCreate(this)
            }
        }
    }
}