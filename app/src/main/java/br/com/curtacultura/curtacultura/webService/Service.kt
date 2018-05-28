package com.example.thevacationplanner.thevacationplanner.webService

import br.com.curtacultura.curtacultura.model.Area
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

        @GET("projetos/areas")
        fun getAreas() : Call<ArrayList<Area>>

      /*  @GET("weather/")
        fun getWeathers() : Call<ArrayList<Weather>>

        @GET("cities/{woeid}/year/{year}/")
        fun getWeathersCity(@Path("woeid") woeid :String, @Path("year") year : String) : Call<ArrayList<WeathersCity>>*/
    }
}