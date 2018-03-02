package swan.biz.readhub.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import swan.biz.readhub.model.News
import swan.biz.readhub.model.Topic
import swan.biz.readhub.model.TopicDetail

/**
 * https://www.juhe.cn/docs/api/id/90
 *
 * http://op.juhe.cn/onebox/
 *
 * Created by stephen on 18-2-9.
 */
interface IReadHubRequestService {

    companion object {

        const val BASE_URL: String = "https://api.readhub.me/"

        const val TOPIC : String = "topic" // 热门话题列表

        const val TOPIC_ID : String = "topic/{id}" // 话题详情

        const val NEWS : String = "news" // 科技动态

        const val TECH_NEWS : String = "technews" // 开发者资讯

        const val BLOCK_CHAIN : String = "blockchain" // 区块链资讯
    }

    @GET(IReadHubRequestService.TOPIC)
    fun postRequestReadHubTopic(
            @Query(IReadHubApiField.lastCursor) lastCursor: Int?,
            @Query(IReadHubApiField.pageSize) pageSize: Int
    ): Observable<ReadHubListResponse<Topic>>

    @GET(IReadHubRequestService.TOPIC_ID)
    fun postRequestReadHubTopic(
            @Path(IReadHubApiField.id) id: String
    ): Observable<TopicDetail>

    @GET(IReadHubRequestService.NEWS)
    fun postRequestReadHubNew(
            @Query(IReadHubApiField.lastCursor) lastCursor: Int?,
            @Query(IReadHubApiField.pageSize) pageSize: Int
    ): Observable<ReadHubListResponse<News>>

    @GET(IReadHubRequestService.TECH_NEWS)
    fun postRequestReadHubTechNews(
            @Query(IReadHubApiField.lastCursor) lastCursor: Int?,
            @Query(IReadHubApiField.pageSize) pageSize: Int
    ): Observable<ReadHubListResponse<News>>

    @GET(IReadHubRequestService.BLOCK_CHAIN)
    fun postRequestReadHubBlockChain(
            @Query(IReadHubApiField.lastCursor) lastCursor: Int?,
            @Query(IReadHubApiField.pageSize) pageSize: Int
    ): Observable<ReadHubListResponse<News>>
}