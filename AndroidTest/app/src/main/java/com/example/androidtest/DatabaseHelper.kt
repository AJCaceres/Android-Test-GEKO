package com.example.androidtest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context):SQLiteOpenHelper(context,dbname,factory, version){



    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table user(id integer primary key autoincrement," +
                "name varchar(30), email varchar(40), password varchar(30), contact int(10)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(name:String,email:String,password:String,contact:Int){
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("name",name)
        values.put("email",email)
        values.put("password", password)
        values.put("contact", contact)

        db.insert("user",null, values)
        db.close()
    }

    fun presentData(email: String, password: String):Boolean{
        val db=writableDatabase
        val query = "select * from user where email = '$email' and password= '$password'"
        val cursor= db.rawQuery(query,null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        else
        {
            cursor.close()
            return true
        }
    }

    companion object{
        internal val dbname = "userDB"
        internal val factory = null
        internal val version = 1
    }
}