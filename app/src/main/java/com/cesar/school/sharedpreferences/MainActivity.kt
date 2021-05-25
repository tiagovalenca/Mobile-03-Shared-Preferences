package com.cesar.school.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar)).apply {
            title = "Files"
        }
    }

    fun ButtonClickEscrever(view: View){
        val prefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val editor = prefs.edit()
        val nome = findViewById<EditText>(R.id.textNome)
        val idade = findViewById<EditText>(R.id.textIdade)

        if(nome.text.trim().isEmpty()) {
            Toast.makeText(applicationContext, "Coloque um Nome!", Toast.LENGTH_SHORT).show()
        } else if(idade.text.trim().isEmpty()){
            Toast.makeText(applicationContext, "Coloque uma Idade!", Toast.LENGTH_SHORT).show()
        } else{
            editor.apply {
                putString("nome", nome.text.toString())
                putInt("idade",idade.text.toString().toInt())
                apply()
            }
            nome.text.clear()
            idade.text.clear()
        }
    }

    fun ButtonClickLer(view: View){
        val prefs: SharedPreferences = getSharedPreferences("sharedPrefs", 0)
        val nome = prefs.getString("nome", null)
        val idade = prefs.getInt("idade", 0)

        if(nome == null){
            Toast.makeText(applicationContext, "Salve as Preferências Primeiro!", Toast.LENGTH_SHORT).show()
        }else{
            val textView = findViewById<TextView>(R.id.textView)
            val texto = "O seu nome é $nome e a sua idade é $idade"
            textView.text = texto
        }
    }

}