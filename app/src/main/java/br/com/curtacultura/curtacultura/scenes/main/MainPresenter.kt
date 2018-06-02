package br.com.curtacultura.curtacultura.scenes.main

import android.view.View
import br.com.curtacultura.curtacultura.core.App.Companion.context
import br.com.curtacultura.curtacultura.model.Area
import br.com.curtacultura.curtacultura.model.CentrosCulturais
import br.com.curtacultura.curtacultura.model.Previsao
import com.example.thevacationplanner.thevacationplanner.webService.RetrofitInitializer
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by rafae on 27/05/2018.
 */
class MainPresenter(var view: MainInterface.View) : MainInterface.Presenter {

    val firebaseStore = FirebaseFirestore.getInstance()

    override fun getCentrosCulturais() {
        firebaseStore.collection("centrosCulturais").get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                val result = task.getResult()
                view.getCentrosCulturaisSuccess(result)
            }else{
                task.exception?.let {
                    throw it
                }
            }
        }
    }

    override fun getAreas() {
        val call = RetrofitInitializer().noteService().getAreas("3477", "15", "26541611b0ed37585afb4439ed466cd4")

        call.enqueue(object : Callback<Previsao> {
            override fun onFailure(call: Call<Previsao>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Previsao>?, response: Response<Previsao>?) {
                if (response != null) {
                    var areas = response.body()
                    view.getAreasSucess(areas!!)
                }


            }


        })
    }
}