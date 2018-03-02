package swan.biz.readhub.network

/**
 * Created by stephen on 18-2-9.
 */
class ReadHubListResponse<T> : IAtomResponse {

    var data: Array<T>? = null

    var pageSize: Int = 0

    var totalItems: Int = 0

    var totalPages: Int = 0
}