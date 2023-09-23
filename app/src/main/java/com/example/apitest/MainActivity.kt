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
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // UI elements linking
        testButton = binding.button
        input = binding.editText
        output = binding.textView
        val apiClient = ApiClient()
        val conversationId = "JX7ax64wrdyv"
        val accessToken = "DigQJqpk291KOjiwKZLWpkflVupf5KfZaQ3DqFdi"

        val coroutineScope = CoroutineScope(Dispatchers.Main)


//        println("Response: $response")

        var reply = abc()

        testButton.setOnClickListener {
            val prompt = input.text.toString()
            val conversationId = "JX7ax64wrdyv"
            val accessToken = "DigQJqpk291KOjiwKZLWpkflVupf5KfZaQ3DqFdi"

            // Launch a coroutine within the coroutineScope
            coroutineScope.launch {
                val response = apiClient.generateMessage(prompt, conversationId, accessToken)
                output.text = response
            }
        }
    }
}