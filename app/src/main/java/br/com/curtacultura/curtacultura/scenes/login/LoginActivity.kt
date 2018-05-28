package br.com.curtacultura.curtacultura.scenes.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import br.com.curtacultura.curtacultura.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginInterface.View {

    private var presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListners()
    }


    private fun setListners(){
        enterBTN.setOnClickListener{
            when{
                TextUtils.isEmpty(userEDT.text) -> userEDT.error = "Digite um Usuario"
                TextUtils.isEmpty(passwordEDT.text) -> passwordEDT.error = "Digite sua senha"
                else -> presenter.login(userEDT.text.toString(), passwordEDT.text.toString())
            }
        }
    }
}
