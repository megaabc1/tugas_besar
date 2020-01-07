package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.model.TentangKami



class ListAboutAdapter(private val listPresiden: ArrayList<TentangKami>) : RecyclerView.Adapter<ListAboutAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_presiden, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPresiden.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val presiden = listPresiden[position]

        Glide.with(holder.itemView.context)
            .load(presiden.photo)
            .apply(RequestOptions().override(60, 80))
            .into(holder.imgPhoto)

        holder.tvNama.text = presiden.nama
        holder.tvDetail.text = presiden.detail


        holder.itemView.setOnClickListener {
            val pindahDetailActivity = Intent(holder.mContext, DetailActivity::class.java)
            pindahDetailActivity.putExtra(DetailActivity.EXTRA_NAMA, presiden.nama)
            pindahDetailActivity.putExtra(DetailActivity.EXTRA_DETAIL, presiden.detail)
            pindahDetailActivity.putExtra(DetailActivity.EXTRA_PHOTO, presiden.photo)
            holder.mContext.startActivity(pindahDetailActivity)
        }


    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tv_item_nama)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)



        val mContext: Context = itemView.context
    }
}