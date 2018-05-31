package br.com.curtacultura.curtacultura.scenes.resetPassword

import com.google.firebase.auth.FirebaseAuth

class ResetPasswordPresenter(val view: ResetPasswordInterface.View): ResetPasswordInterface.Presenter {

    val mAuth = FirebaseAuth.getInstance()
    override fun resetPassword(email: String) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener{task ->
            if(task.isSuccessful){
                view.resetPasswordSucess()
            }else{
                task.exception?.let {
                    view.emitError(it.message.toString())
                }
            }
        }
    }
}