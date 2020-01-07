package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_costumer.*
import kotlinx.android.synthetic.main.layout_update.view.*
import kotlinx.android.synthetic.main.lo_costumer.view.*
import kotlinx.android.synthetic.main.lo_costumer.view.btnUpdate

class CostumerAdapter(mCtx : Context , val costumers : ArrayList<Costumer>): RecyclerView.Adapter<CostumerAdapter.ViewHolder>(){
    val mCtx = mCtx

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtCustomerName = itemView.txtCustomerName
        val txtPetAge = itemView.txtPetAge
        val txtPetName = itemView.txtPetName
        val txtPetType = itemView.txtPetType
        val txtAddress = itemView.txtAddress
        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_costumer , parent , false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return costumers.size
    }

    override fun onBindViewHolder(holder: CostumerAdapter.ViewHolder, position: Int) {
        val costumer : Costumer = costumers[position]
        holder.txtCustomerName.text = costumer.costumerName
        holder.txtPetAge.text = costumer.petAge.toString()
        holder.txtPetName.text = costumer.petName
        holder.txtPetType.text = costumer.petType
        holder.txtAddress.text = costumer.alamat

        holder.btnDelete.setOnClickListener {
            val customername = costumer.costumerName

            val alertDialog = AlertDialog.Builder (mCtx)
                .setTitle("Warning")
                .setMessage("Apakah anda ingin menghapus $customername ?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if (MainActivity.dbHandler.deleteCustomer(costumer.costumerId)) {
                        costumers.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position,costumers.size)
                        Toast.makeText(mCtx, "$customername telah berhasil dihapus", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mCtx, "Error Deleting", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  })
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show()
        }

        holder.btnUpdate.setOnClickListener{
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.layout_update, null)
            val updatename : TextView = view.findViewById(R.id.updateCostumerName)
            val updateage : TextView = view.findViewById(R.id.updatePetAge)
            val updatepetname : TextView = view.findViewById(R.id.updatetNamaHewan)
            val updatepettype : TextView = view.findViewById(R.id.updateJenisHewan)
            val updatealamat : TextView = view.findViewById(R.id.updateAlamat)

            updatename.text = costumer.costumerName
            updateage.text = costumer.petAge.toString()
            updatepetname.text = costumer.petName
            updatepettype.text = costumer.petType
            updatealamat.text = costumer.alamat

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Update Data")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
                    if (view.updateCostumerName.text.isEmpty() || view.updatePetAge.text.isEmpty() || view.updatetNamaHewan.text.isEmpty() || view.updateJenisHewan.text.isEmpty() || view.updateAlamat.text.isEmpty()){
                        Toast.makeText(mCtx,"Data belum lengkap!", Toast.LENGTH_SHORT).show()
                    }else{
                        val isUpdate : Boolean = MainActivity.dbHandler.updateCustomer(
                            costumer.costumerId.toString(),
                            view.updateCostumerName.text.toString(),
                            view.updatePetAge.text.toString(),
                            view.updatetNamaHewan.text.toString(),
                            view.updateJenisHewan.text.toString(),
                            view.updateAlamat.text.toString())
                        if (isUpdate==true){
                            costumers[position].costumerName = view.updateCostumerName.text.toString()
                            costumers[position].petAge = view.updatePetAge.text.toString().toDouble()
                            costumers[position].petName = view.updatetNamaHewan.text.toString()
                            costumers[position].petType = view.updateJenisHewan.text.toString()
                            costumers[position].alamat = view.updateAlamat.text.toString()
                            notifyDataSetChanged()
                            Toast.makeText(mCtx, "Update Berhasil", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(mCtx, "Error Updating", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                })
            val alert = builder.create()
            alert.show()
        }
    }
}