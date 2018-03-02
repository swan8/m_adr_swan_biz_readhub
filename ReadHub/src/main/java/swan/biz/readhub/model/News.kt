package swan.biz.readhub.model

/**
 * Created by stephen on 28/02/2018.
 */
data class News(
    
    var id: String? = null,
    
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
)