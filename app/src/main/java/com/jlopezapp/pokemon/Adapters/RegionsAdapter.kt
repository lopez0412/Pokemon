package com.jlopezapp.pokemon.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlopezapp.pokemon.Objetos.RegionesObject
import com.jlopezapp.pokemon.R
import kotlinx.android.synthetic.main.regions_row.view.*

class RegionsAdapter (val regiones: List<RegionesObject>) : RecyclerView.Adapter<RegionsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.regions_row, parent, false))
    }

    override fun getItemCount(): Int {
       return regiones.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var nombredelaregion = regiones[position].name

        holder.name.text = nombredelaregion

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = itemView.nameregion
    }
}