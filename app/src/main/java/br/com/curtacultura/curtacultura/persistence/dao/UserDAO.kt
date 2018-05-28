package br.com.curtacultura.curtacultura.persistence.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import br.com.curtacultura.curtacultura.model.User
import com.google.gson.Gson

/**
 * Created by rafae on 11/05/2018.
 */
class UserDAO(context: Context) {

    lateinit var db: SQLiteDatabase
    var localDb: LocalDataBase

    init {
        localDb = LocalDataBase(context)
    }

    fun saveUser(localUser: User): Boolean {
        val contentValues = ContentValues()
        db = localDb.writableDatabase
        val gson = Gson()
        val userJson = gson.toJson(localUser)
        contentValues.put(LocalDataBase.FIELD_EMAIL, localUser.email)
        contentValues.put(LocalDataBase.FIELD_USER, userJson)

        val result = db.insert(LocalDataBase.TABLE_NAME_USER, null, contentValues)

        val ERROR: Long = -1
        db.close()

        if (result == ERROR) {
            return true
        }

        return false
    }

    fun loadLocalUser(): User? {
        var localUser: User? = null
        val cursor: Cursor
        val fields = arrayOf(LocalDataBase.FIELD_EMAIL, LocalDataBase.FIELD_USER)
        db = localDb.readableDatabase
        cursor = db.query(LocalDataBase.TABLE_NAME_USER, fields, null, null,
                null, null, null, "1")

        if (cursor != null && cursor.moveToFirst()) {
            val gson = Gson()
            val userJson = cursor.getString(cursor.getColumnIndex(LocalDataBase.FIELD_USER))
            localUser = gson.fromJson(userJson, User::class.java)
            cursor.close()
            db.close()
        }

        cursor.close()
        db.close()

        return localUser
    }

    fun removeLocalUser(email: String) {
        val where = LocalDataBase.FIELD_EMAIL + " =\'" + email + "\'"
        db = localDb.readableDatabase
        db.delete(LocalDataBase.TABLE_NAME_USER, where, null)
        db.close()
    }

    fun removeAllUsers() {
        db = localDb.writableDatabase
        db.execSQL("delete from " + LocalDataBase.TABLE_NAME_USER)
        db.close()
    }
}