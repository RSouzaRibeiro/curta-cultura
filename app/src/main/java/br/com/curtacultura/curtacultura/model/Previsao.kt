package br.com.curtacultura.curtacultura.model

import com.google.gson.annotations.SerializedName

class Previsao {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var nome: String? = null

    @SerializedName("state")
    var estado: String? = null

    @SerializedName("country")
    var pais: String? = null


}