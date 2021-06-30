package com.phamnam.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var result : EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }
    private var operation1 : Double? = null
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        findViewById<Button>(R.id.buttonClear).setOnClickListener{
            clearAll()
        }

            //number
        val button0 : Button = findViewById(R.id.button0)
        val button1 : Button = findViewById(R.id.button1)
        val button2 : Button = findViewById(R.id.button2)
        val button3 : Button = findViewById(R.id.button3)
        val button4 : Button = findViewById(R.id.button4)
        val button5 : Button = findViewById(R.id.button5)
        val button6 : Button = findViewById(R.id.button6)
        val button7 : Button = findViewById(R.id.button7)
        val button8 : Button = findViewById(R.id.button8)
        val button9 : Button = findViewById(R.id.button9)
        val buttonDot : Button = findViewById(R.id.buttonDot)


        // operation

        val buttonMultiply : Button = findViewById(R.id.buttonMultiply)
        val buttonEquals : Button = findViewById(R.id.buttonEquals)
        val buttonPlus : Button = findViewById(R.id.buttonAdd)
        val buttonMinus : Button = findViewById(R.id.buttonMinus)
        val buttonDivide : Button = findViewById(R.id.buttonDivide)


        // set in put
        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        //set input operation

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()

            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value,op)

            }catch (e : NumberFormatException){
                newNumber.setText("")
                if(operation1 == null) displayOperation.text = ""
            }
            pendingOperation = op
            if (operation1 != null)  displayOperation.text = pendingOperation

        }
        buttonEquals.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)


    }


    private fun performOperation(value: Double, op: String) {
        if (operation1 == null){
            operation1 = value
        }else{
            if (pendingOperation == "="){
                pendingOperation = op}
                when(pendingOperation){
                    "=" -> operation1 = value
                    "+" -> operation1 = operation1!! + value
                    "-" -> operation1 = operation1!! - value
                    "*" -> operation1 = operation1!! * value
                    "/" -> operation1 = if (value == 0.0) {
                        Double.NaN
                    }else {
                        operation1!! /value
                    }


            }
        }
        result.setText(operation1.toString())
        newNumber.setText("")

    }
   private fun clearAll(){
       newNumber.setText("")
       operation1 = null
       result.setText("")
       pendingOperation = "="
       displayOperation.text = ""
   }
    private fun init(){
        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)
    }
}