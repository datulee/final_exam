package com.example.data_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var editTextEmailAddress: EditText
    lateinit var editTextPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var buttonToGoRegister: Button
    lateinit var buttonToGoForgotPassword: TextView

    val firebaseAuth = Firebase.auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        listeners()
    }
    private fun init() {
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.button_login)
        buttonToGoRegister = findViewById(R.id.button_ToGoRegister)
        buttonToGoForgotPassword = findViewById(R.id.button_ToGoForgotPassword)

    }

    private fun listeners() {
        buttonLogin.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()
            if (email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "it can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                startActivity(Intent(this, ProfileActivity::class.java))

            } else {
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
            }
            }
        }
        buttonToGoRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        buttonToGoForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            finish()
        }
    }

}