package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_costumer.*

class AddCostumerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_costumer)
        btnSave.setOnClickListener{
            if (editCostumerName.text.isEmpty() || editPetAge.text.isEmpty() || editNamaHewan.text.isEmpty() || editJenisHewan.text.isEmpty() || editAlamat.text.isEmpty()){
                Toast.makeText(this, "Data belum lengkap!", Toast.LENGTH_SHORT).show()
            }else{
                val customer = Costumer()
                customer.costumerName = editCostumerName.text.toString()
                customer.petAge = editPetAge.text.toString().toDouble()
                customer.petName = editNamaHewan.text.toString()
                customer.petType = editJenisHewan.text.toString()
                customer.alamat = editAlamat.text.toString()
                MainActivity.dbHandler.AddCustomer(this , customer)
                editCostumerName.requestFocus()
            }
        }
        btnClose.setOnClickListener{
            ClearEdits()
            finish()
        }
    }

    fun ClearEdits(){
        editCostumerName.text.clear()
        editPetAge.text.clear()
        editAlamat.text.clear()
        editJenisHewan.text.clear()
        editNamaHewan.text.clear()
    }
}
