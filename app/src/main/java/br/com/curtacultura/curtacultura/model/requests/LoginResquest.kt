package br.com.curtacultura.curtacultura.model.requests

class LoginResquest{
    private var username: String
    private var password : String


    constructor(username: String, password: String){
        this.username = username
        this.password = password
    }
}
