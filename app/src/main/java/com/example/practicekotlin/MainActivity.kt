package com.example.practicekotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.practicekotlin.databinding.ActivityMainBinding

    private lateinit var b:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnCal.setOnClickListener{
            val weight = b.etWeight.text.toString()
            val height = b.etHeight.text.toString()

            if(validateInput(weight,height)){

                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                //get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2Digits)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{

        return when{

            weight.isNullOrEmpty() -> {
                b.etWeight.setError("Weight is empty!")
                return false
            }

            height.isNullOrEmpty() -> {
                b.etHeight.setError("Height is empty!")
                return false
            }

            else -> {
                return true
            }

        }

    }

    private fun displayResult( bmi: Float){
        b.tvIndex.text = bmi.toString()
        b.tvInfo.text = "Normal range is 18.5 to 24.9"

        var resText = ""
        var color = 0

        when{
            bmi < 18.50 ->{
                resText = "Under weight"
                color = R.color.underWeight
            }

            bmi in 18.50..24.99 ->{
                resText = "Healthy"
                color = R.color.normal
            }

            bmi in 25.00..29.99 ->{
                resText = "Healthy"
                color = R.color.normal
            }

            bmi in 18.50..24.99 ->{
                resText = "Overweight"
                color = R.color.overWeight
            }

            bmi > 24.99 ->{
                resText = "Obese"
                color = R.color.obese
            }
        }

        b.tvRes.setTextColor(ContextCompat.getColor(this, color))
        b.tvRes.text = resText

    }
}