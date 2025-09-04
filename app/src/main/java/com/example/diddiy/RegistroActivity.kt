package com.example.diddiy

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import android.util.Patterns // Import para validación de email


class RegistroActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etContrasena: EditText
    private lateinit var etRepetirContrasena: EditText
    private lateinit var cbTerminos: CheckBox
    private lateinit var btnRegistro: Button
    private lateinit var tvLoginRedirect: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        inicializarVistas()

        setupOnClickListeners()
    }

    private fun inicializarVistas() {
        etNombres = findViewById(R.id.et_user_rg)
        etApellidos = findViewById(R.id.et_apellidos_rg)
        etCorreo = findViewById(R.id.et_correo_rg)
        etTelefono = findViewById(R.id.et_telefono_rg)
        etContrasena = findViewById(R.id.et_contraseña_rg)
        etRepetirContrasena = findViewById(R.id.et_repetir_contraseña_rg)
        cbTerminos = findViewById(R.id.cb_terminos_rg)
        btnRegistro = findViewById(R.id.btn_registrate_rg)
        tvLoginRedirect = findViewById(R.id.tvLoginRedirect)
    }

    private fun setupOnClickListeners() {
        btnRegistro.setOnClickListener {
            registrarUsuario()
        }

        tvLoginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registrarUsuario() {
        val nombres = etNombres.text.toString().trim()
        val apellidos = etApellidos.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val telefono = etTelefono.text.toString().trim()
        val contrasena = etContrasena.text.toString()
        val repetirContrasena = etRepetirContrasena.text.toString()

        if (validarCampos(nombres, apellidos, correo, telefono, contrasena, repetirContrasena)) {
            guardarDatos(nombres, apellidos, correo, telefono)
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

             val intent = Intent(this, LoginActivity::class.java)
             startActivity(intent)
             finish()
        }
    }

    private fun validarCampos(
        nombres: String,
        apellidos: String,
        correo: String,
        telefono: String,
        contrasena: String,
        repetirContrasena: String
    ): Boolean {
        if (nombres.isEmpty()) {
            etNombres.error = "Por favor ingrese su nombre"
            etNombres.requestFocus() // Pone el foco en el campo
            return false
        }
        if (apellidos.isEmpty()) {
            etApellidos.error = "Por favor ingrese sus apellidos"
            etApellidos.requestFocus()
            return false
        }
        if (correo.isEmpty()) {
            etCorreo.error = "Por favor ingrese su correo electrónico"
            etCorreo.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.error = "Ingrese un correo electrónico válido"
            etCorreo.requestFocus()
            return false
        }
        if (telefono.isEmpty()) {
            etTelefono.error = "Por favor ingrese su teléfono"
            etTelefono.requestFocus()
            return false
        }

        if (contrasena.isEmpty()) {
            etContrasena.error = "Por favor ingrese su contraseña"
            etContrasena.requestFocus()
            return false
        }
        if (contrasena.length < 6) {
            etContrasena.error = "La contraseña debe tener al menos 6 caracteres"
            etContrasena.requestFocus()
            return false
        }
        if (repetirContrasena.isEmpty()) {
            etRepetirContrasena.error = "Por favor repita su contraseña"
            etRepetirContrasena.requestFocus()
            return false
        }
        if (contrasena != repetirContrasena) {
            etRepetirContrasena.error = "Las contraseñas no coinciden"
            etRepetirContrasena.requestFocus()
            etRepetirContrasena.text.clear()
            return false
        }
        if (!cbTerminos.isChecked) {
            Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun guardarDatos(
        nombres: String,
        apellidos: String,
        correo: String,
        telefono: String,
    ) {
        val editor = sharedPreferences.edit()
        editor.putString("nombres", nombres)
        editor.putString("apellidos", apellidos)
        editor.putString("correo", correo)
        editor.putString("telefono", telefono)
        editor.apply()
    }
}