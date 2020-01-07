package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dbHandler: DBHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHandler = DBHandler(this,null,null,1)
        viewCostumers()
        fab.setOnClickListener{
            val i = Intent(this,AddCostumerActivity::class.java)
            startActivity(i)
        }
    }

    private fun  viewCostumers(){
        val customerlist = dbHandler.getCostumers(this)
        val adapter = CostumerAdapter (this , customerlist)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager (this , LinearLayout.VERTICAL , false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onResume() {
        viewCostumers()
        super.onResume()
    }
}
