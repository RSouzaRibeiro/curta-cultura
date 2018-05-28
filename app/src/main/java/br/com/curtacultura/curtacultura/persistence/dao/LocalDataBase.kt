package br.com.curtacultura.curtacultura.persistence.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by rafae on 11/05/2018.
 */
class LocalDataBase(context: Context) :
        SQLiteOpenHelper(context, "curtaCultura.db", null, 1) {
    companion object {
        val FIELD_ID = "id"
        val FIELD_EMAIL = "email"
        val FIELD_USER = "user"
        val TABLE_NAME_USER = "USER"

        val FIELD_CLINIC_NAME = "clinicName"
        val FIELD_IS_PENDING = "isPending"
        val FIELD_APPOINTMENT_ID = "appointmentID"
        val TABLE_NAME_PENDING_RATES = "PENDING_RATES"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE " + TABLE_NAME_USER + "(" + FIELD_ID + " integer primary key autoincrement," +
                "                     " + FIELD_EMAIL + " text UNIQUE NOT NULL" +
                "                     ," + FIELD_USER + " text)"

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER)
        onCreate(db)
    }
}