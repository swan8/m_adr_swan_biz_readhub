package swan.atom.core.base

import com.mikepenz.fastadapter.IClickable
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by stephen on 18-3-15.
 */
abstract class AtomCoreBaseFasterItem<T, Item, VH> constructor(src: T) : AbstractItem<Item, VH>()
        where Item : IItem<*, *>,
              Item : IClickable<*>,
              VH : AtomCoreBaseFasterItemHolder<T> {

    var src: T? = null

    init {
        this.src = src
    }
}