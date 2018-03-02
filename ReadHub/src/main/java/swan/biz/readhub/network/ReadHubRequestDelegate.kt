package swan.biz.readhub.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by stephen on 18-2-9.
 */
object ReadHubRequestDelegate {

    private var readHubRequestService: IReadHubRequestService? = null

    init {

        var okHttpClient: OkHttpClient = OkHttpClient.Builder()
//                .cache(Cache(File("/mnt/sdcard/", "ReadHubRequestDelegate"), 10 * 1024 * 1024))
                .addInterceptor(ReadHubRequestInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

        var retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(IReadHubRequestService.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        readHubRequestService = retrofit.create(IReadHubRequestService::class.java)
    }

    fun ReadHub(): IReadHubRequestService? {
        return readHubRequestService
    }
}