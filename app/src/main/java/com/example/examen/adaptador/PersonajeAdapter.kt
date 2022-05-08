package com.example.examen.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.modelo.Personaje
import com.example.examen.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class PersonajeAdapter(val listaPersonajes: ArrayList<Personaje>):RecyclerView.Adapter<PersonajeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.vista_individual,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNombre.text = listaPersonajes[position].nombre
        Picasso.get().load(listaPersonajes[position].imagen).into(holder.ivPersonaje)
        holder.tvNombreKanji.text = listaPersonajes[position].nombreKanji
        holder.tvLikes.text = listaPersonajes[position].likes
    }

    override fun getItemCount(): Int {
        return  listaPersonajes.size
    }

    class ViewHolder(vista:View):RecyclerView.ViewHolder(vista){
        val tvNombre:TextView
        val ivPersonaje:ImageView
        val tvNombreKanji:TextView
        val tvLikes:TextView

        init {
            tvNombre = vista.findViewById(R.id.tvNombre)
            ivPersonaje= vista.findViewById(R.id.ivPersonaje)
            tvNombreKanji= vista.findViewById(R.id.tvNombreKanji)
            tvLikes= vista.findViewById(R.id.tvLikes)
        }
    }

}