package com.example.data_auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var newPassword: EditText
    private lateinit var newPassword2: EditText
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        newPassword = findViewById(R.id.newchange_Password)
        newPassword2 = findViewById(R.id.newchange_Password2)
        button = findViewById(R.id.button_tochangepassword)

        button.setOnClickListener {

            val userNewPassword = newPassword.text.toString()
            val userNewPassword2 = newPassword2.text.toString()

            if (userNewPassword.isEmpty() || userNewPassword2.isEmpty() || userNewPassword != userNewPassword2){
                Toast.makeText(this, "make sure they are same", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .currentUser?.updatePassword(userNewPassword)
                ?.addOnCompleteListener {  task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
                    } }

        }
    }
}