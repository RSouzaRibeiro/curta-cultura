package br.com.curtacultura.curtacultura.helpers



import android.preference.PreferenceManager
import br.com.curtacultura.curtacultura.core.App.Companion.context


class AuthHelper {

    companion object {
        const val DEVELOP_MODE = false
        private const val DEVICE_TOKEN = "data.source.prefs.DEVICE_TOKEN"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var deviceToken = preferences.getString(DEVICE_TOKEN, "")
        set(value) = preferences.edit().putString(DEVICE_TOKEN, value).apply()


    fun getAuthToken() : String {
        return deviceToken

    }

    fun setAuthToken(token: String?) {
        preferences.edit().putString(DEVICE_TOKEN, token).apply()
    }
}