package swam.atom.core.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by stephen on 18-3-14.
 */

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}