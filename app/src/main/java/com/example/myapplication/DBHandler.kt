package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class DBHandler(context: Context , name : String? , factory: SQLiteDatabase.CursorFactory? , version: Int) : SQLiteOpenHelper(context , DATABASE_NAME , factory , DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "DataPet.db"
        private val DATABASE_VERSION = 1

        val COSTUMERS_TABLE_NAME = "Costumers"
        val COLUMN_COSTUMERID = "costumerid"
        val COLUMN_COSTUMERNAME = "costumername"
        val COLUMN_PETAGE = "petage"
        val COLUMN_PETNAME = "petname"
        val COLUMN_PETTYPE = "pettype"
        val COLUMN_ADDRESS = "address"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_COSTUMER_TABLE = ("CREATE TABLE $COSTUMERS_TABLE_NAME" +
                "(" + "$COLUMN_COSTUMERID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_COSTUMERNAME TEXT, "
                + "$COLUMN_PETAGE DOUBLE DEFAULT 0, "
                + "$COLUMN_PETNAME TEXT, "
                + "$COLUMN_PETTYPE TEXT, "
                + "$COLUMN_ADDRESS TEXT)")
        db?.execSQL(CREATE_COSTUMER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun getCostumers(mCtx: Context): ArrayList<Costumer> {
        val query = "select * from $COSTUMERS_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val customers = ArrayList<Costumer>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "Tidak Ada Record ", Toast.LENGTH_SHORT).show()
        else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val costumer = Costumer()
                costumer.costumerId = cursor.getInt(cursor.getColumnIndex(COLUMN_COSTUMERID))
                costumer.costumerName = cursor.getString(cursor.getColumnIndex(COLUMN_COSTUMERNAME))
                costumer.petAge = cursor.getDouble(cursor.getColumnIndex(COLUMN_PETAGE))
                costumer.petName = cursor.getString(cursor.getColumnIndex(COLUMN_PETNAME))
                costumer.petType = cursor.getString(cursor.getColumnIndex(COLUMN_PETTYPE))
                costumer.alamat = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS))
                customers.add(costumer)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Data Ditemukan ", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return customers
    }

    fun AddCustomer(mCtx: Context , costumer: Costumer){
        val values = ContentValues()
        values.put(COLUMN_COSTUMERNAME,costumer.costumerName)
        values.put(COLUMN_PETAGE,costumer.petAge)
        values.put(COLUMN_PETNAME,costumer.petName)
        values.put(COLUMN_PETTYPE,costumer.petType)
        values.put(COLUMN_ADDRESS,costumer.alamat)
        val db = this.writableDatabase
        try {
            db.insert(COSTUMERS_TABLE_NAME, null , values)

            Toast.makeText(mCtx , "Costumer Ditambahkan", Toast.LENGTH_SHORT).show()
        }catch (e : Exception){
            Toast.makeText(mCtx, e.message , Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteCustomer(customerID : Int) : Boolean{
        val query = "delete from $COSTUMERS_TABLE_NAME where $COLUMN_COSTUMERID = $customerID"
        val db = this.writableDatabase
        var result : Boolean = false
        try {
            val cursor = db.execSQL(query)
            result = true
        } catch(e: Exception) {
            Log.e(ContentValues.TAG, "Error deleting")
        }
        db.close()
        return result
    }

    fun updateCustomer (id : String, customername : String, petage : String, petname : String, pettype : String, address : String) : Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result : Boolean = false
        contentValues.put(COLUMN_COSTUMERNAME, customername)
        contentValues.put(COLUMN_PETAGE, petage)
        contentValues.put(COLUMN_PETTYPE, pettype)
        contentValues.put(COLUMN_PETNAME, petname)
        contentValues.put(COLUMN_ADDRESS, address)
        try{
            db.update(COSTUMERS_TABLE_NAME, contentValues, "$COLUMN_COSTUMERID = ?", arrayOf(id))
            result = true
        } catch(e : Exception) {
            Log.e(ContentValues.TAG,"Error Updating")
            result = false
        }

        return true
    }
}

