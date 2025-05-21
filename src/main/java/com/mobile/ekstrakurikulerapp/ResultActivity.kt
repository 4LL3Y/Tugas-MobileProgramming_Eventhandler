package com.mobile.ekstrakurikulerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val data = intent.getStringExtra("formData")
        tvResult.text = data
    }
}