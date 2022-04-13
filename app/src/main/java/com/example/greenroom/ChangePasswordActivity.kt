package com.example.greenroom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonChangePassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password_activity)

        init()

    }

    private fun init(){

        editTextNewPassword = findViewById(R.id.newPasswordChangePasswordEditText)
        buttonChangePassword = findViewById(R.id.changePasswordButton)

        listeners()
    }

    private fun listeners() {
        buttonChangePassword.setOnClickListener {
            val newPassword = editTextNewPassword.text.toString()

            if (newPassword.isEmpty() || newPassword.length < 7){
                Toast.makeText(this, "try other password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "password updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}