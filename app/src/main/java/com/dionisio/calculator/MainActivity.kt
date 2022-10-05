package com.dionisio.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

    fun onEqual(view: View) {
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            if(tvValue.startsWith("-")){
                prefix="-"
                tvValue = tvValue.substring(1)
            }

            try {

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                }

            }
            catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }


    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            false
        } else {
            value.contains('/')
                || value.contains("*")
                || value.contains("+")
                || value.contains("-")
        }
    }

  
}