package com.example.androidtest

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabaseCorruptException
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context):SQLiteOpenHelper(context, dbname, factory, version){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table user(id integer primary key autoincrement," +
                "name varchar(30), email varchar(30), password varchar(30), contact int(10)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUserData(name:String, email:String, password:String, contact:Int){
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues= ContentValues()
        values.put("name",name)
        values.put("email",email)
        values.put("password",password)
        values.put("contact",contact)

        db.insert("user", null, values)
        db.close()
    }

    fun userPresent(email: String, password: String):Boolean{
        val db=writableDatabase
        val query= "select * from user where email = '$email' and password = '$password'"
        val cursor= db.rawQuery(query, null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
        companion object{
        internal val dbname = "user"
        internal val factory = null
        internal val version: Int = 1


    }
}