package br.com.curtacultura.curtacultura.scenes.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import br.com.curtacultura.curtacultura.R
import br.com.curtacultura.curtacultura.helpers.AuthHelper
import br.com.curtacultura.curtacultura.model.response.TokenResponse
import br.com.curtacultura.curtacultura.scenes.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginInterface.View {



    private var presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListners()
    }


    private fun setListners() {
        enterBTN.setOnClickListener {
            when {
                TextUtils.isEmpty(userEDT.text) -> userEDT.error = "Digite um Usuario"
                TextUtils.isEmpty(passwordEDT.text) -> passwordEDT.error = "Digite sua senha"
                else -> {
                    presenter.login(userEDT.text.toString(), passwordEDT.text.toString())
                    progressBar.visibility = View.VISIBLE
                }
            }
        }


    }

    override fun loginSucess(token: String) {
        progressBar.visibility = View.GONE
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun getTokenSuccess(token: TokenResponse) {
        AuthHelper().setAuthToken(token.token!!)
        progressBar.visibility = View.GONE
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun emitError(message: String?) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
