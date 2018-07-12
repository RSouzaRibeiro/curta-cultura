package br.com.curtacultura.curtacultura.scenes.register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import br.com.curtacultura.curtacultura.R
import br.com.curtacultura.curtacultura.R.id.enterBTN
import br.com.curtacultura.curtacultura.scenes.main.MainActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterInterface.View {


    private var presenter= RegisterPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setListiners()
    }

    private fun setListiners(){

        enterBTN.setOnClickListener{
            when{
                TextUtils.isEmpty(nameTXT.text.toString()) -> nameTXT.error = getString(R.string.mensagem_digite_seu_nome)
                TextUtils.isEmpty(emailTXT.text.toString()) -> emailTXT.error =getString(R.string.mensagem_digite_email)
                TextUtils.isEmpty(senhaTXT.text.toString()) -> senhaTXT.error =getString(R.string.mensagem_informe_senha)
                TextUtils.isEmpty(senhaConfirmTXT.text.toString()) -> senhaConfirmTXT.error = getString(R.string.mensagem_confirme_senha)
                else -> when{
                    TextUtils.equals(senhaTXT.text.toString(), senhaConfirmTXT.text.toString()) ->{
                        progressBar.visibility = View.VISIBLE
                        presenter.registerNewUser(emailTXT.text.toString(), senhaTXT.text.toString())
                    }


                    else ->
                        Toast.makeText(this, getString(R.string.mensagem_campo_senha_diferente), Toast.LENGTH_LONG).show()

                }

            }
        }
    }


    override fun registerSuccess(userLogado:  FirebaseUser) {
        progressBar.visibility = View.GONE
        goToMain()
        Toast.makeText(this, userLogado.email, Toast.LENGTH_LONG).show()
    }

    override fun emitError(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
