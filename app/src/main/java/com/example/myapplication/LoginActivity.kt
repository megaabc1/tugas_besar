package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val adus = findViewById<EditText>(R.id.adus)
        val pass = findViewById<EditText>(R.id.pass)

        val login = findViewById<Button>(R.id.login)
        val loginTentang = findViewById<Button>(R.id.btnTentang)

        login.setOnClickListener{
            val adus = adus.text.toString()
            val pass = pass.text.toString()

            if(  adus.isEmpty() && pass.isEmpty()){
                toast("Username dan Password Tidak Boleh Kosong", Toast.LENGTH_LONG)


            }else if (adus == "admin" && pass == "admin123"){
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)

            }
            else{
                toast("Username atau Password Anda Salah", Toast.LENGTH_LONG)
            }
        }

        loginTentang.setOnClickListener{
            val intent = Intent (this@LoginActivity, About::class.java )
            startActivity(intent)
        }


    }
    private fun toast(pesan: String, length: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, pesan, length).show()

    }
}