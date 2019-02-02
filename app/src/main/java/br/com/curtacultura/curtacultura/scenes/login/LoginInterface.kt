package br.com.curtacultura.curtacultura.scenes.login

import br.com.curtacultura.curtacultura.model.response.TokenResponse


/**
 * Created by rafae on 27/05/2018.
 */
interface LoginInterface {

    interface View{
        fun loginSucess(token: String)
        fun emitError(message: String?)
        fun getTokenSuccess(token: TokenResponse)
    }

    interface Presenter{
        fun login(user: String, password: String)
    }
}