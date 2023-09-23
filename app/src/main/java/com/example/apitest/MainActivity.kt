package com.example.apitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.apitest.databinding.ActivityMainBinding
import java.io.DataOutput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var testButton: Button
    private lateinit var input: EditText
    private lateinit var output: TextView
    private var isButtonClickable = true
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // UI elements linking
        testButton = binding.button
        input = binding.editText
        output = binding.textView
        val apiClient = ApiClient()
        val conversationId = "Volej0qEBbjN"
        val accessToken = "JdaIIsNvdvWNwwYEz5D9vTqau9t9r0GZmCoGjgJT"

        val coroutineScope = CoroutineScope(Dispatchers.Main)


        var reply = abc()

        testButton.setOnClickListener {
            if (isButtonClickable) {
                val prompt = input.text.toString()
                testButton.isEnabled = false
                output.text = "Loading..."
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        val response = apiClient.generateMessage(prompt, conversationId, accessToken)
                        output.text = response
                    } catch (e: Exception) {
                        output.text = "An error occurred: ${e.message}"
                    } finally {
                        testButton.isEnabled = true
                    }
                }
            }
        }
    }
}