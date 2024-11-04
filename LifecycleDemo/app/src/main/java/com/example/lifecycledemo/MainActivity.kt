package com.example.lifecycledemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("LifecycleDemo", "Bem Vindo ao LifecycleDemo :) - onCreate chamado")
        Toast.makeText(this, "App Criado - onCreate chamado", Toast.LENGTH_LONG).show()

        val buttonHome: Button = findViewById(R.id.buttonHome)
        buttonHome.setOnClickListener {
            moveTaskToBack(true) 
        }

        val buttonFinish: Button = findViewById(R.id.buttonFinish)
        buttonFinish.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifecycleDemo", "Preparando a Tela para Você - onStart chamado")
        Toast.makeText(this, "Tela Pronta :) - onStart chamado", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifecycleDemo", "O Aplicativo está pronto para seu uso - onResume chamado")
        Toast.makeText(this, "Vamos lá - onResume chamado", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifecycleDemo", "Estamos salvando seu dados! - onPause chamado")
        Toast.makeText(this, "App Pausado - onPause chamado", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifecycleDemo", "Estamos ocultando o uso, aguarde um instante! - onStop chamado")
        Toast.makeText(this, "App oculto! - onStop chamado", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifecycleDemo", "Estamos encerrando seu uso! - onDestroy chamado")
        Toast.makeText(this, "App encerrado :( - onDestroy chamado", Toast.LENGTH_LONG).show()
    }
}
