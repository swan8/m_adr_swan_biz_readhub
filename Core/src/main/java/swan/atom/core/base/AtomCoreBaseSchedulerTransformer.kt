package swan.atom.core.base

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by stephen on 01/03/2018.
 */
class AtomCoreBaseSchedulerTransformer<T> : ObservableTransformer<T, T> {

    companion object {
        fun <T> create(): AtomCoreBaseSchedulerTransformer<T> {
            return AtomCoreBaseSchedulerTransformer()
        }
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}