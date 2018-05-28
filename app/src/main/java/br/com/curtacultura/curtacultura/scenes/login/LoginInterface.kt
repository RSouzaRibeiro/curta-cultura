package br.com.curtacultura.curtacultura.scenes.login

/**
 * Created by rafae on 27/05/2018.
 */
interface LoginInterface {

    interface View{

    }

    interface Presenter{
        fun login(user: String, password: String)
    }
}