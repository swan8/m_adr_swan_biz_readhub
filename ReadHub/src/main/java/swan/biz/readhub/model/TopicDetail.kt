package swan.biz.readhub.model

/**
 * Created by stephen on 02/03/2018.
 */
data class TopicDetail(

        var id: String? = null,

        var createdAt: String? = null,

        var newsArray: List<News>? = null,

        var order: Int = 0,

        var publishDate: String? = null,

        var summary: String? = null,

        var title: String? = null,

        var updatedAt: String? = null,

        var timeline: Timeline? = null,

        var entityTopics: List<Entity>? = null,

//    var entityEventTopics: Any? = null,

        var hasInstantView: Boolean = false
) {
    data class Timeline(

        var topics: List<Topic>? = null,

        var message: String? = null,

        var keywords: List<String>? = null,

        val errorCode: Int = 0,

        val commonEntites: List<Entity>? = null
    )

    data class Entity (

        val weight: Int = 0,

        val nerName: String? = null,

        val entityId: Int = 0,

        val entityName: String? = null,

        val entityType: String? = null,

        val entityUniqueId: String? = null,

        val eventType: Int = 0,

        val eventTypeLabel: String? = null
    )
}