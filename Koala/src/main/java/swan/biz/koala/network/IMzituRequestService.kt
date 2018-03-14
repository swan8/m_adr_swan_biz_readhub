package swan.biz.koala.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by stephen on 18-3-9.
 */
interface IMzituRequestService {
    
    companion object {

        const val BASE_URL = "http://www.mzitu.com/"

        const val API_PAGE = "page/{page}"

        const val API_INDEX = API_PAGE

        const val API_ALL = "all/${API_PAGE}"

        const val API_HOT = "hot/${API_PAGE}"
        
        const val API_BEST = "best/${API_PAGE}"
        
        const val API_XING_GAN = "xinggan/${API_PAGE}"
        
        const val API_JAPAN = "japan/${API_PAGE}"
        
        const val API_TAIWAN = "taiwan/${API_PAGE}"
        
        const val API_MM = "mm/${API_PAGE}"

        const val API_ZI_PAI = "zipai/${API_PAGE}"
        
        const val API_IMAGE_LIST = "{imageId}/{page}"
    }

    @GET(IMzituRequestService.API_INDEX)
    fun postRequestMztIndex(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_ALL)
    fun postRequestMzituAll(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_HOT)
    fun postRequestMzituHot(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_BEST)
    fun postRequestMzituBest(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_XING_GAN)
    fun postRequestMzituSexy(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_JAPAN)
    fun postRequestMzituJapan(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_TAIWAN)
    fun postRequestMzituTaiwan(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_MM)
    fun postRequestMzituMM(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_ZI_PAI)
    fun postRequestMzituZiPai(
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>

    @GET(IMzituRequestService.API_IMAGE_LIST)
    fun postRequestMzituImageList(
            @Path(IMzituApiField.imageId) imageId: String,
            @Path(IMzituApiField.page) page: Int
    ): Observable<String>
}