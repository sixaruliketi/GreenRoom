package com.example.greenroom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var editTextEmailReset: EditText
    private lateinit var buttonResetPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password_activity)

        init()
    }

    private fun init(){
        editTextEmailReset = findViewById(R.id.EmailResetEditText)
        buttonResetPassword = findViewById(R.id.resetPasswordButton)

        listeners()

    }

    private fun listeners(){

        buttonResetPassword.setOnClickListener {
            val email = editTextEmailReset.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "Empty!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }
}