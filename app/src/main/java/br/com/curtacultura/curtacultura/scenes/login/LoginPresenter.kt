package br.com.curtacultura.curtacultura.scenes.login

import android.content.Context
import br.com.curtacultura.curtacultura.core.App.Companion.context
import com.google.firebase.auth.FirebaseAuth


/**
 * Created by rafae on 27/05/2018.
 */
class LoginPresenter(val view: LoginInterface.View, val contexto: Context) : LoginInterface.Presenter {

    val mAuth = FirebaseAuth.getInstance()
    override fun login(user: String, password: String) {

        mAuth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(contexto as LoginActivity) { task ->


                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        if (user != null) {
                            view.loginSucess(user)
                        }
                    } else {
                        task.exception?.let {
                            throw it
                        }
                    }


                }.addOnFailureListener {
                    it.printStackTrace()
                }


    }
}