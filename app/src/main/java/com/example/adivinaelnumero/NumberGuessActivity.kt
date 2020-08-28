package com.example.adivinaelnumero

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class NumberGuessActivity : AppCompatActivity() {
    var guessThisNumber = 0
    var number_of_guess_attemps = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)

        guessThisNumber = GetNumber().toInt()

        val BtnClick = findViewById(R.id.restart) as Button
        val inputNumber = findViewById<TextInputEditText>(R.id.input_number)
        val HintLabel = findViewById(R.id.game_hint) as TextView

        BtnClick.setOnClickListener {
            //Toast.makeText(this@MainActivity, guessThisNumber.toString(), Toast.LENGTH_SHORT).show()
            guessThisNumber = GetNumber().toInt()
            inputNumber.setText("")
            HintLabel.text = ""
            HintLabel.visibility = View.INVISIBLE
            number_of_guess_attemps = 0
        }

        inputNumber.setOnClickListener {
            var guess_attemp: Int

            try {
                guess_attemp = inputNumber.text.toString().toInt()
                HintLabel.visibility = View.VISIBLE

                if (guess_attemp < guessThisNumber) {
                    HintLabel.text = "Pista: El número es mayor!"
                    number_of_guess_attemps++
                } else if (guess_attemp > guessThisNumber) {
                    HintLabel.text = "Pista: El número es menor!"
                    number_of_guess_attemps++
                } else {
                    HintLabel.text = "Felicidades! Adivinaste!" + " en " + number_of_guess_attemps.toString()+ " intentos."

                }
            }catch (e: NumberFormatException ){
                Toast.makeText(this@NumberGuessActivity, "Ingresa un número valido!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun GetNumber(): UInt {
        val randomInteger = (1..1000).shuffled().first()
        return randomInteger.toUInt()
    }
}