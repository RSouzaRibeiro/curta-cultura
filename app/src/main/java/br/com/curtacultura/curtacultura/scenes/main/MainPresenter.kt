package br.com.curtacultura.curtacultura.scenes.main

import android.view.View
import br.com.curtacultura.curtacultura.model.Area
import com.example.thevacationplanner.thevacationplanner.webService.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by rafae on 27/05/2018.
 */
class MainPresenter(var view: MainInterface.View): MainInterface.Presenter {

    override fun getAreas() {
        val call = RetrofitInitializer().noteService().getAreas()

        call.enqueue(object: Callback<ArrayList<Area>>{
            override fun onResponse(call: Call<ArrayList<Area>>?, response: Response<ArrayList<Area>>?) {
                if(response!=null){
                    var areas = response?.body()
                    view.getAreasSucess(areas!!)
                }


            }

            override fun onFailure(call: Call<ArrayList<Area>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}