package com.example.thevacationplanner.thevacationplanner.webService




import br.com.curtacultura.curtacultura.core.App
import br.com.curtacultura.curtacultura.core.App.Companion.context
import br.com.curtacultura.curtacultura.helpers.AuthHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




/**
 * Created by rafae on 21/02/2018.
 */
class RetrofitInitializer {

    companion object {
        const val BASE_URL = "https://curta-cultura-back.herokuapp.com/"
    }

    private var httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                        .header("Authorization", "Token "+ AuthHelper().getAuthToken())
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build()

                chain.proceed(request)
            }.build()


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()


    fun service(): Service.NoteService {
        return retrofit.create(Service.NoteService::class.java)
    }

}