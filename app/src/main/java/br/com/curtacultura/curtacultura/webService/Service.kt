package com.example.thevacationplanner.thevacationplanner.webService

import br.com.curtacultura.curtacultura.model.Area
import br.com.curtacultura.curtacultura.model.Previsao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by rafae on 21/02/2018.
 */
class Service {

    interface NoteService{

        @GET("forecast/locale/{id}/days/{days}?")
        fun getAreas(@Path("id") id :String, @Path("days") days : String, @Query("token") token: String) : Call<Previsao>


    }
}