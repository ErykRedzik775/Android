package com.example.lab2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.iterator
import com.example.lab2poprawione.R
import java.lang.NumberFormatException

class SettingsActivity : AppCompatActivity() {

    lateinit var numberFormat: String
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        numberFormat = intent.getStringExtra(getString(R.string.numberFormat)) ?: "Float"

        when(numberFormat){
            "Float" -> findViewById<RadioButton>(R.id.floatRadioButton).isChecked = true
            "Integer" -> findViewById<RadioButton>(R.id.integerRadioButton).isChecked = true
            else -> findViewById<RadioButton>(R.id.floatRadioButton).isChecked = true
        }
        val switch = findViewById<SwitchCompat>(R.id.enableSwitch)
        switch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener{compoundButton, isChecked -> toggleEnable(isChecked) })

        val applyButton: Button = findViewById(R.id.applyButton)
        applyButton.setOnClickListener{
            applyChanges()
            finish()
        }

    }

    private fun toggleEnable(checked: Boolean) {
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        for(item in radioGroup){
            item.isEnabled = checked
        }
        radioGroup.setOnCheckedChangeListener{ radioGroup, id ->
            numberFormat = findViewById<RadioButton>(id).text.toString()
        }
    }
    private fun applyChanges(){
        val returnIntent = Intent()
        returnIntent.putExtra(getString(R.string.numberFormat), numberFormat)
        setResult(RESULT_OK,returnIntent)
    }
}