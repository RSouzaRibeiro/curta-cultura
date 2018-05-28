package br.com.curtacultura.curtacultura.core

import android.app.Application
import android.content.Context
import br.com.curtacultura.curtacultura.model.User

/**
 * Created by rafae on 11/05/2018.
 */
class App: Application() {

    companion object {
        lateinit var context: Context
        var loggedUser: User? = null


    }
    override fun onCreate() {
        context = this
        super.onCreate()
    }
}