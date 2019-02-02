package br.com.curtacultura.curtacultura.core

import android.app.Application
import android.content.Context
import br.com.curtacultura.curtacultura.R
import br.com.curtacultura.curtacultura.model.User
import com.google.android.gms.ads.MobileAds

/**
 * Created by rafae on 11/05/2018.
 */
class App : Application() {

    companion object {
        lateinit var context: Context
        var loggedUser: User? = null


    }

    override fun onCreate() {
        context = this
        super.onCreate()

        MobileAds.initialize(this,  getString(R.string.admob_id))
    }
}