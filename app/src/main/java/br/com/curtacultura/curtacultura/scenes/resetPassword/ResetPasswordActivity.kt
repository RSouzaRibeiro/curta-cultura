package br.com.curtacultura.curtacultura.scenes.resetPassword

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.curtacultura.curtacultura.R
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity(), ResetPasswordInterface.View {


    private val presenter = ResetPasswordPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        setListiners()
    }

    private fun setListiners(){
        enterBTN.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            presenter.resetPassword(emailResetTXT.text.toString())
        }
    }

    override fun resetPasswordSucess() {
        progressBar.visibility = View.GONE
       Toast.makeText(this, "Enviamos um email para resetar a senha", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun emitError(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
