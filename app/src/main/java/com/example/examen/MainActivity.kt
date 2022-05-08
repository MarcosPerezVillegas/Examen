package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.examen.adaptador.PersonajeAdapter
import com.example.examen.modelo.Personaje

class MainActivity : AppCompatActivity() {
    lateinit var myRecycler: RecyclerView
    lateinit var listaPersonajes:ArrayList<Personaje>
    lateinit var adaptador: PersonajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
            /*Response.Listener*/{ respuesta ->

                val personajesJson = respuesta.getJSONArray("data")
                for(indice in 0..personajesJson.length()-1){
                    val personajeIndJson = personajesJson.getJSONObject(indice)
                    val imgJson = personajeIndJson.getJSONObject("images")
                    val urlJson = imgJson.getJSONObject("jpg")
                    val personaje = Personaje(personajeIndJson.getString("name"),urlJson.getString("image_url"))
                    listaPersonajes.add(personaje)
                }
                adaptador.notifyDataSetChanged()
            },
            /*Response.ErrorListener*/{
                Log.e("PersonajesAPI","Error")
            }
        )
        queue.add(objectRequest)
    }
}