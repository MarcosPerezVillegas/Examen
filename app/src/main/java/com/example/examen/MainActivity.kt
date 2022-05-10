package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.examen.adaptador.PersonajeAdapter
import com.example.examen.modelo.Personaje

class MainActivity : AppCompatActivity() {
    lateinit var myRecycler: RecyclerView
    lateinit var listaPersonajes:ArrayList<Personaje>
    lateinit var adaptador: PersonajeAdapter
    lateinit var btnCreditos:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreditos = findViewById(R.id.btnCreditos)

        btnCreditos.setOnClickListener {
            val intentCreditos=Intent(this,CreditosActivity::class.java)
            startActivity(intentCreditos)
        }

        listaPersonajes = ArrayList<Personaje>()
        adaptador = PersonajeAdapter(listaPersonajes)

        myRecycler=findViewById(R.id.rvPersonajes)
        myRecycler.adapter = adaptador
        getPersonajes()
        myRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun getPersonajes(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.jikan.moe/v4/characters?page=1"

        val objectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { respuesta ->

                val personajesJson = respuesta.getJSONArray("data")
                for(indice in 0..personajesJson.length()-1){
                    val personajeIndJson = personajesJson.getJSONObject(indice)
                    val imgJson = personajeIndJson.getJSONObject("images")
                    val urlJson = imgJson.getJSONObject("jpg")
                    val personaje = Personaje(personajeIndJson.getString("name"),urlJson.getString("image_url"),personajeIndJson.getString("name_kanji"),personajeIndJson.getString("favorites"))
                    listaPersonajes.add(personaje)
                }
                adaptador.notifyDataSetChanged()
            },
            {
                Log.e("PersonajesAPI","Error")
            }
        )
        queue.add(objectRequest)
    }
}