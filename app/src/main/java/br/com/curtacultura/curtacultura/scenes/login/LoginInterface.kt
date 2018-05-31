package br.com.curtacultura.curtacultura.scenes.login

import com.google.firebase.auth.FirebaseUser

/**
 * Created by rafae on 27/05/2018.
 */
interface LoginInterface {

    interface View{
        fun loginSucess(userLogged: FirebaseUser)
        fun emitError(message: String)
    }

    interface Presenter{
        fun login(user: String, password: String)
    }
}