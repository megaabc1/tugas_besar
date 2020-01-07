package com.example.myapplication.model
object TentangKamiDataData {
    private val presidenNama = arrayOf(
        "Yoldry Al Rahman (5170411001)",
        "Alwy Muhammad Syafii (5170411002)",
        "Febri Nugroho (5170411004)",
        "Reza Reinaldi (5170411011)",
        "Muhammad Furqon Fajar Fauzi (5170411024)",
        "Hernika Bid Candra (5170411039)"
    )

    private val presidenDetail = arrayOf(
        "",
         "",
        "",
        "",
        "",
        ""
       )

    private val presidePhoto = arrayOf(
        "https://sia.uty.ac.id/fotokecil/5170411001.jpg",
        "https://sia.uty.ac.id/fotokecil/5170411002.jpg",
        "https://sia.uty.ac.id/fotokecil/5170411004.jpg",
        "https://sia.uty.ac.id/fotokecil/5170411011.jpg",
        "https://sia.uty.ac.id/fotokecil/5170411024.jpg",
        "https://sia.uty.ac.id/fotokecil/5170411039.jpg"
    )


    val listDataPresiden: ArrayList<TentangKami>
        get() {
            val list = arrayListOf<TentangKami>()
            for (position in presidenNama.indices) {
                val presiden = TentangKami()
                presiden.nama = presidenNama[position]
                presiden.detail = presidenDetail[position]
                presiden.photo = presidePhoto[position]
                list.add(presiden)
            }
            return list
        }
}