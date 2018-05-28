package com.example.thevacationplanner.thevacationplanner.webService


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rafae on 21/02/2018.
 */
class RetrofitInitializer {


      private val retrofit = Retrofit.Builder()
                .baseUrl("http://api.salic.cultura.gov.br/v1/swagger-def/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    fun noteService(): Service.NoteService {
        return retrofit.create(Service.NoteService::class.java)
    }

}