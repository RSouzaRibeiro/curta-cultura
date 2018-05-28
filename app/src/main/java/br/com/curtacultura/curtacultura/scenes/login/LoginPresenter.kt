package br.com.curtacultura.curtacultura.scenes.login

import com.google.firebase.auth.FirebaseAuth




/**
 * Created by rafae on 27/05/2018.
 */
class LoginPresenter(val view: LoginInterface.View): LoginInterface.Presenter {

    private lateinit var mAuth: FirebaseAuth
    override fun login(user: String, password: String) {

        mAuth = FirebaseAuth.getInstance()




    }
}