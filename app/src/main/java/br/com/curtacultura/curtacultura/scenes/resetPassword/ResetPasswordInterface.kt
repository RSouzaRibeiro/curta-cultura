package br.com.curtacultura.curtacultura.scenes.resetPassword

interface ResetPasswordInterface {

    interface View {
        fun resetPasswordSucess()
        fun emitError(message: String)
    }

    interface Presenter {
        fun resetPassword(email: String)
    }
}