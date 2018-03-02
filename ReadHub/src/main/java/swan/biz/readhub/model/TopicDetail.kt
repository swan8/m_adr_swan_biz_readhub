package swan.biz.readhub.model

/**
 * Created by stephen on 01/03/2018.
 */
class TopicDetail {

    var id: String? = null

    var createdAt: String? = null

    var newsArray: Array<News> = emptyArray()

    var order: Int = 0

    var publishDate: String? = null

    var summary: String? = null

    var title: String? = null

    var updatedAt: String? = null

    var timeline: Timeline? = null

    var entityTopics: Array<Entity> = emptyArray()

//    var entityEventTopics: Any? = null

    var hasInstantView: Boolean = false

    class Timeline {

        var topics: Array<Topic> = emptyArray()

        var message: String? = null

        var keywords: Array<String> = emptyArray()

        val errorCode: Int = 0

        val commonEntites: Array<Entity> = emptyArray()
    }

    class Entity {

        val weight: Int = 0

        val nerName: String? = null

        val entityId: Int = 0

        val entityName: String? = null

        val entityType: String? = null

        val entityUniqueId: String? = null

        val eventType: Int = 0

        val eventTypeLabel: String? = null
    }
}