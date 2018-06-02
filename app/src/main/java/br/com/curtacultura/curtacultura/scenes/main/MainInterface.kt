package br.com.curtacultura.curtacultura.scenes.main

import br.com.curtacultura.curtacultura.model.Area
import br.com.curtacultura.curtacultura.model.Previsao
import com.google.firebase.firestore.QuerySnapshot

/**
 * Created by rafae on 27/05/2018.
 */
interface MainInterface {

    interface View{
        fun getAreasSucess(areas : Previsao)
        fun emitErrorSnake(message: String)
        fun getCentrosCulturaisSuccess(result: QuerySnapshot)
    }

    interface Presenter{
        fun getAreas()
        fun getCentrosCulturais()
    }
}