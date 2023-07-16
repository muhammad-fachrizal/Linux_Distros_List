package com.fachrizal.linuxdistroslist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fachrizal.linuxdistroslist.databinding.ItemLinuxDistrosBinding

class ListLinuxDistrosAdapter(private val linuxDistrosList: ArrayList<LinuxDistros>) : RecyclerView.Adapter<ListLinuxDistrosAdapter.ListViewHolder>() {

    class ListViewHolder (var binding: ItemLinuxDistrosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemLinuxDistrosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = linuxDistrosList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, icon) = linuxDistrosList[position]
        Glide.with(holder.itemView.context)
            .load(icon) // URL Gambar
            .into(holder.binding.imgItemIcon)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_linux_distros", linuxDistrosList[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }

    }
}