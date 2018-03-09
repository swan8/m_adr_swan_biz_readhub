package swan.atom.core.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.IClickable;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.items.AbstractItem;

/**
 * Created by stephen on 11/08/2017.
 */
public abstract class AtomCoreBaseFasterItem<T, Item extends IItem & IClickable, VH extends AtomCoreBaseFasterItem.AtomPubFastAdapterItemHolder> extends AbstractItem<Item, VH> {

    public T src;

    public AtomCoreBaseFasterItem(T src) {
        this.src = src;
    }

    public static abstract class AtomPubFastAdapterItemHolder<T> extends RecyclerView.ViewHolder {

        public AtomPubFastAdapterItemHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBindView(Context context, T t);
    }
}
