package br.com.curtacultura.curtacultura.scenes.main

import br.com.curtacultura.curtacultura.model.Area
import br.com.curtacultura.curtacultura.model.CultureCenter
import br.com.curtacultura.curtacultura.model.Previsao

/**
 * Created by rafae on 27/05/2018.
 */
interface MainInterface {

    interface View{

        fun emitErrorSnake(message: String)
        fun getCentrosCulturaisSuccess(listCultureCenter: ArrayList<CultureCenter>)
    }

    interface Presenter{

        fun getCentrosCulturais()
        fun searchCultureCenter(search: String)
    }
}