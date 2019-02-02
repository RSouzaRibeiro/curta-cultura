package br.com.curtacultura.curtacultura.scenes.login

import br.com.curtacultura.curtacultura.helpers.AuthHelper
import br.com.curtacultura.curtacultura.model.requests.LoginResquest
import br.com.curtacultura.curtacultura.model.response.TokenResponse
import com.example.thevacationplanner.thevacationplanner.webService.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by rafae on 27/05/2018.
 */
class LoginPresenter(val view: LoginInterface.View) : LoginInterface.Presenter {


    override fun login(user: String, password: String) {
        val call = RetrofitInitializer().service().login(LoginResquest(user, password))

        call.enqueue(object : Callback<TokenResponse>{
            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                view.emitError(t.localizedMessage)
            }

            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if(response.isSuccessful){
                    val token = response.body()
                    if(token!=null)

                        view.getTokenSuccess(token)

                }
                else
                    view.emitError(response.message()!!)
            }

        })


    }
}