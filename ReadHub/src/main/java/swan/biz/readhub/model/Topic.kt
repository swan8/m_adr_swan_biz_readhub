package swan.biz.readhub.model

import swan.biz.readhub.network.IAtomResponse

/**
 * Created by stephen on 28/02/2018.
 */
class Topic: IAtomResponse {
    
    var id: String? = null
    
    var createdAt: String? = null
    
    var nelData: Nel? = null
    
    var eventData: Event? = null
    
    var newsArray: Array<News> = emptyArray()
    
    var order: Int = 0
    
    var publishDate: String? = null
    
    var summary: String? = null

    var title: String? = null

    var updatedAt: String? = null

    var timeline: Any? = null

    var extra: TopicExtra? = null

    class Nel: IAtomResponse {

        var state: Boolean = false

        var result: Array<NelResult> = emptyArray()

//        var nerResult: Any? = null
    }

    class NelResult: IAtomResponse {

        var weight: Float = 0f

        var nerName: String? = null

        var entityId: Int = 0

        var entityName: String? = null

        var entityType: String? = null

        var fromAlgorithm: Boolean = false

        var entityUniqueId: String? = null
    }

    class Event: IAtomResponse {

        var result: Array<EventResult> = emptyArray()
    }

    class EventResult: IAtomResponse {

        var eventId: Int = 0

        var entityId: Int = 0

        var eventType: Int = 0

        var entityName: String? = null

        var entityType: String? = null

        var entityUniqueId: String? = null
    }

    class TopicExtra: IAtomResponse {

        var instantView: Boolean = false
    }
}