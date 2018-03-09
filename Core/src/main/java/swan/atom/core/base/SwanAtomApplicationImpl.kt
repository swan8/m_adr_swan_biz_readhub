package swan.atom.core.basectx

import android.app.Application
import android.content.Context

/**
 * Created by stephen on 18-2-14.
 */
interface SwanAtomApplicationImpl {

    fun onCreate(application: Application)

    fun getContext(): Context?
}