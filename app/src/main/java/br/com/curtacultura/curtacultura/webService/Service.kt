package com.example.thevacationplanner.thevacationplanner.webService

import br.com.curtacultura.curtacultura.model.Area
import br.com.curtacultura.curtacultura.model.CultureCenter
import br.com.curtacultura.curtacultura.model.Previsao
import br.com.curtacultura.curtacultura.model.requests.LoginResquest
import br.com.curtacultura.curtacultura.model.response.TokenResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by rafae on 21/02/2018.
 */
class Service {

    interface NoteService{

        @POST("auth")
        fun login(@Body loginResquest: LoginResquest ) : Call<TokenResponse>

        @GET("culturecenter")
        fun getCultureCenterAll() : Call<ArrayList<CultureCenter>>

        @GET("culturecenter")
        fun searchCultureCenter(@Query("search") search: String): Call<ArrayList<CultureCenter>>


    }
}