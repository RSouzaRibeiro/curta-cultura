package br.com.curtacultura.curtacultura.scenes.main

import br.com.curtacultura.curtacultura.model.Area
import br.com.curtacultura.curtacultura.model.Previsao

/**
 * Created by rafae on 27/05/2018.
 */
interface MainInterface {

    interface View{
        fun getAreasSucess(areas : Previsao)
        fun emitErrorSnake(message: String)
    }

    interface Presenter{
        fun getAreas()
    }
}