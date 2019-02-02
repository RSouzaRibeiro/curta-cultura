package br.com.curtacultura.curtacultura.scenes.main

import br.com.curtacultura.curtacultura.model.CultureCenter
import com.example.thevacationplanner.thevacationplanner.webService.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by rafae on 27/05/2018.
 */
class MainPresenter(var view: MainInterface.View) : MainInterface.Presenter {
    override fun searchCultureCenter(search: String) {
        val call = RetrofitInitializer().service().searchCultureCenter(search)

        call.enqueue(object: Callback<ArrayList<CultureCenter>>{
            override fun onFailure(call: Call<ArrayList<CultureCenter>>, t: Throwable) {
                view.emitErrorSnake(t.localizedMessage)
            }

            override fun onResponse(call: Call<ArrayList<CultureCenter>>, response: Response<ArrayList<CultureCenter>>) {
                if(response.isSuccessful){
                    var cultureCenter = response.body()
                    if(cultureCenter!=null){
                        view.getCentrosCulturaisSuccess(cultureCenter)
                    }
                }else{
                    view.emitErrorSnake(response.message())
                }
            }

        })
    }

    override fun getCentrosCulturais() {
        val call = RetrofitInitializer().service().getCultureCenterAll()

        call.enqueue(object : Callback<ArrayList<CultureCenter>>{
            override fun onFailure(call: Call<ArrayList<CultureCenter>>, t: Throwable) {
                view.emitErrorSnake(t.localizedMessage)
            }

            override fun onResponse(call: Call<ArrayList<CultureCenter>>, response: Response<ArrayList<CultureCenter>>) {
                if(response.isSuccessful){
                    var cultureCenter = response.body()
                    if(cultureCenter!=null){
                        view.getCentrosCulturaisSuccess(cultureCenter)
                    }
                }else{
                    view.emitErrorSnake(response.message())
                }
            }

        })
    }


}