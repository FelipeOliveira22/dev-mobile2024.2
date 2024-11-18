package com.example.profileapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando componentes
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val nameText = findViewById<TextView>(R.id.nameText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val currentJobText = findViewById<TextView>(R.id.currentJobText)
        val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)

        // Definindo informações de perfil
        nameText.text = "Felipe Oliveira"
        descriptionText.text = "Estudante na Universidade Federal do Ceará, no 6º período do curso de Engenharia de Software."
        currentJobText.text = "Emprego Atual: Buscando Estágio"

        // Lista de experiências
        val experiencias = listOf(
            "Universidade Federal do Ceará, Programa de Educação Tutorial — bolsista",
            "Universidade Federal do Ceará, Pesquisador e Desenvolvedor na Área de Ciência de Dados — bolsista",
            "Centro Educacional Elos: Técnico em Informática, Desenvolvedor e Auxiliar de TI — estágio"
        )

        // Adicionando experiências dinamicamente
        for (experiencia in experiencias) {
            val textView = TextView(this)
            textView.text = experiencia
            textView.textSize = 16f
            textView.setPadding(0, 8, 0, 8) // Espaçamento entre os itens
            experienceLayout.addView(textView)
        }

        // Clique na foto de perfil
        profileImage.setOnClickListener {
            Toast.makeText(this, "Foto de perfil de Felipe Oliveira", Toast.LENGTH_SHORT).show()
        }
    }
}
