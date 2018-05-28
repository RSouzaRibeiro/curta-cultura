package br.com.curtacultura.curtacultura.scenes.main

import br.com.curtacultura.curtacultura.model.Area

/**
 * Created by rafae on 27/05/2018.
 */
interface MainInterface {

    interface View{
        fun getAreasSucess(areas : ArrayList<Area>)
        fun emitErrorSnake(message: String)
    }

    interface Presenter{
        fun getAreas()
    }
}