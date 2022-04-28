package com.example.wisielec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val applyButton: Button = findViewById(R.id.applyChangesButton)
        applyButton.setOnClickListener{
            applyChanges()
            finish()
        }
    }
    private fun applyChanges(){
        val returnIntent = Intent()
        setResult(RESULT_OK,returnIntent)
    }
}