package com.example.data_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    lateinit var editTextSignUpEmailAddress: EditText
    lateinit var editTextSignUpPassword: EditText
    lateinit var buttonRegister: Button
    lateinit var buttonToGoLogIn: Button

    val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        init()
        listeners()
    }

    private fun init() {
        editTextSignUpEmailAddress = findViewById(R.id.editTextSignUpEmail)
        editTextSignUpPassword = findViewById(R.id.editTextSignUpPassword)
        buttonRegister = findViewById(R.id.button_Register)
        buttonToGoLogIn = findViewById(R.id.button_ToGoLogIn)
    }

    private fun listeners() {
        buttonRegister.setOnClickListener {
            val email = editTextSignUpEmailAddress.text.toString()
            val password = editTextSignUpPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "It can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "congrats! now login!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "something's wrong, try again", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
        }
        buttonToGoLogIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }}

