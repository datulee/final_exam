package com.example.data_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var resetPassword: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        email = findViewById(R.id.editText_forgotpasswordEmail)
        resetPassword = findViewById(R.id.resetPasswordButton2)

        resetPassword.setOnClickListener {
                val userEmail = email.text.toString()

            if (userEmail.isEmpty()) {
                Toast.makeText(this, "Enter Your Email!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(userEmail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, ChangePasswordActivity::class.java))
                    } else {
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
    }