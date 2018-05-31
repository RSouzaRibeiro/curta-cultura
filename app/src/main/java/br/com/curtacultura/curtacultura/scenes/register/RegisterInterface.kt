package br.com.curtacultura.curtacultura.scenes.register

import br.com.curtacultura.curtacultura.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

/**
 * Created by rafae on 27/05/2018.
 */
interface RegisterInterface {

    interface View{
        fun registerSuccess(userLogado: FirebaseUser)
        fun emitError(message: String)
    }

    interface Presenter{
        fun registerNewUser(user: String, password: String)

    }
}