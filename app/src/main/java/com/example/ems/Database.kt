package com.example.ems

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class Database(context: Context): SQLiteOpenHelper(context,db_name,null,db_version){
    companion object {
        private const val db_name="users.db"
        private const val db_version=1
        private const val tbl_name="users"
        private const val em="email"
        private const val pwd="password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val query="CREATE TABLE $tbl_name($em TEXT PRIMARY KEY,$pwd TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query="DROP TABLE IF EXISTS $tbl_name"
        db?.execSQL(query)
        onCreate(db)
    }

    fun put(data:Model): Boolean {
        val db:SQLiteDatabase=writableDatabase
        val cv= ContentValues()
        cv.put(em,data.em)
        cv.put(pwd,data.pwd)
        val result=db.insert(tbl_name,null,cv)
        db.close()
        if(result>0) return true
        return false
    }
    fun isExist(email:String):Boolean{
        val db: SQLiteDatabase =readableDatabase
        val rs:Cursor=db.rawQuery("SELECT * FROM $tbl_name where email=?", arrayOf(email))
        if (rs.moveToFirst()) {
            db.close()
            return true
        }
        db.close()
        return false
    }
    @SuppressLint("Range")
    fun getAll():ArrayList<Model>{
        val list:ArrayList<Model> =ArrayList()
        val query="SELECT * FROM $tbl_name"
        val db=readableDatabase
        val cursor: Cursor=db.rawQuery(query,null)
        var email:String
        var password:String
        if(cursor.moveToFirst()){
            do{
                email=cursor.getString(cursor.getColumnIndex(em))
                password=cursor.getString(cursor.getColumnIndex(pwd))
                val model=Model(email,password)
                list.add(model)
            }while (cursor.moveToNext())
        }
        db.close()
        return list
    }
    fun checkPassword(email:String,password:String):Boolean{
        val db: SQLiteDatabase =readableDatabase
        val rs:Cursor=db.rawQuery("SELECT $pwd FROM $tbl_name WHERE $em=?", arrayOf(email))
        rs.moveToFirst()
        val passwordFromTbl=rs.getString(0)
        db.close()
        if (passwordFromTbl.equals(password)) return true
        return false
    }















}