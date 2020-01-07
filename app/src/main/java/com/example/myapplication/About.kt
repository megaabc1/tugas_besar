package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.TentangKami
import com.example.myapplication.model.TentangKamiDataData

class About : AppCompatActivity() {
    private lateinit var rvPresiden: RecyclerView
    private var listPresiden: ArrayList<TentangKami> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val intent = intent


        rvPresiden = findViewById(R.id.rv_presiden)
        rvPresiden.setHasFixedSize(true)

        listPresiden.addAll(TentangKamiDataData.listDataPresiden)
        showPresidenList()
    }

    private fun showPresidenList() {
        rvPresiden.layoutManager = LinearLayoutManager(this)
        val listPresidenAdapter = ListAboutAdapter(listPresiden)
        rvPresiden.adapter = listPresidenAdapter
    }
}