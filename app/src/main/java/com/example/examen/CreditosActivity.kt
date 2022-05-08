package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreditosActivity : AppCompatActivity() {
    lateinit var btnRegreso : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)
        btnRegreso = findViewById(R.id.btnRegresar)

        btnRegreso.setOnClickListener {
            val intentMain= Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }
    }
}