package br.com.curtacultura.curtacultura.scenes.register

import android.content.Context
import br.com.curtacultura.curtacultura.core.App.Companion.context
import br.com.curtacultura.curtacultura.model.User
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by rafae on 27/05/2018.
 */
class RegisterPresenter(val view: RegisterInterface.View, val contexto: Context) : RegisterInterface.Presenter {


    val mAuth = FirebaseAuth.getInstance()
    val mFirebaseFirestore = FirebaseFirestore.getInstance()


    override fun registerNewUser(user: String, password: String) {
        mAuth.createUserWithEmailAndPassword(user, password)
                .addOnCompleteListener(contexto as RegisterActivity) { task ->
                    try {
                        if (task.isSuccessful) {
                            val user = mAuth!!.currentUser
                            if (user != null) {

                                view.registerSuccess(user)
                            }
                        } else {
                            task.exception?.let {
                                throw it
                            }
                        }
                    } catch (wpe: FirebaseAuthWeakPasswordException) {
                        view.emitError("Por favor preencha uma senha mais forte")
                    } catch (ice: FirebaseAuthInvalidCredentialsException) {
                        view.emitError("Por favor verifique se se email está correto")
                    } catch (fce: FirebaseAuthUserCollisionException) {
                        view.emitError("Já existe um usuário com essas credenciais cadastrado")
                    }

                }
    }

}