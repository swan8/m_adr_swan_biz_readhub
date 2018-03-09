package swan.biz.koala.adapter.item

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.mzitu_album_list_body.view.*
import swan.atom.core.base.AtomCoreBaseFasterItem
import swan.biz.koala.KoalaApplicationImpl
import swan.biz.koala.R
import swan.biz.koala.model.MzituImage

/**
 * Created by stephen on 09/03/2018.
 */
class MzituAlbumListItem(album: MzituImage): AtomCoreBaseFasterItem<MzituImage, MzituAlbumListItem, MzituAlbumListItem.MzituAlbumBodyHolder>(album) {

    override fun getType(): Int {
        return R.id.mzituResAdapterItem_AlbumList
    }

    override fun getViewHolder(view: View?): MzituAlbumBodyHolder {
        return MzituAlbumBodyHolder(view)
    }

    override fun getLayoutRes(): Int {
        return R.layout.mzitu_album_list_body
    }

    override fun bindView(holder: MzituAlbumBodyHolder?, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)
        holder?.onBindView(KoalaApplicationImpl.getContext(), src)
    }

    class MzituAlbumBodyHolder(view: View?): AtomCoreBaseFasterItem.AtomPubFastAdapterItemHolder<MzituImage>(view) {

        override fun onBindView(context: Context?, album: MzituImage?) {
            itemView.bodyImage.setImageURI(album?.original)
        }
    }
}