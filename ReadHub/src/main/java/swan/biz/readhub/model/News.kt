package swan.biz.readhub.model

import com.github.ajalt.timberkt.Timber
import kotlin.properties.Delegates

/**
 * Created by stephen on 28/02/2018.
 */
data class News(
    
    var url: String? = null,

    var title: String? = null,

    var summary: String? = null,

    var content: String? = null,

    var groupId: Long = 0L,

    var siteName: String? = null,

    var siteSlug: String? = null,

    var mobileUrl: String? = null,

    var authorName: String? = null,

    var duplicateId: Int = 0,

    var publishDate: String? = null
) {
    var id: String by Delegates.observable("") {
        property, oldValue, newValue ->
        Timber.e {
            "${property.name}::${oldValue}, ${newValue}"
        }
    }
}