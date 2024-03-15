package com.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val editTextPeso = findViewById<EditText>(R.id.editTextPeso)
        val editTextAltura = findViewById<EditText>(R.id.editTextAltura)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        buttonCalcular.setOnClickListener {
            calcularIMC(editTextPeso, editTextAltura, textViewResultado)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calcularIMC(editTextPeso: EditText, editTextAltura: EditText, textViewResultado: TextView) {
        val pesoStr = editTextPeso.text.toString()
        val alturaStr = editTextAltura.text.toString()

        if (pesoStr.isNotEmpty() && alturaStr.isNotEmpty()) {
            val peso = pesoStr.toFloat()
            val altura = alturaStr.toFloat()

            val imc = peso / (altura * altura)

            val resultado = when {
                imc < 18.5 -> "Abaixo do peso"
                imc < 25 -> "Peso normal"
                imc < 30 -> "Sobrepeso"
                imc < 35 -> "Obesidade grau I"
                imc < 40 -> "Obesidade grau II"
                else -> "Obesidade grau III"
            }

            textViewResultado.text = "IMC: $imc\n $resultado"
        } else {
            textViewResultado.text = "Por favor, insira o peso e a altura." // Caso o usuário não digite os valores
        }
    }
}