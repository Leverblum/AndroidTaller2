package com.example.diddiy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            var btnIngresar = findViewById<Button>(R.id.btn_ingresar_rc)
            btnIngresar.setOnClickListener {
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)

            var tvRecCont = findViewById<TextView>(R.id.tv_RecupCont_rc)
            tvRecCont.setOnClickListener {
                val intent = Intent(this, Rec_ContraActivity::class.java)
                startActivity(intent)
            }

            val tvRegistrate = findViewById<TextView>(R.id.tv_Registrate_rc)
            tvRegistrate.setOnClickListener {
                val intent = Intent(this, RegistroActivity::class.java)
                startActivity(intent)
            }
        }
    }
}