package com.example.greenroom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    private lateinit var buttonPasswordReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        init()

    }

    private fun init(){

        editTextEmail = findViewById(R.id.emailLoginEditText)
        editTextPassword = findViewById(R.id.passwordLoginEditText)
        buttonLogin = findViewById(R.id.loginButton)
        buttonRegister = findViewById(R.id.RegistrationLoginButton)
        buttonPasswordReset = findViewById(R.id.resetPasswordLoginButton)

        listeners()
    }

    private fun listeners(){

        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Empty!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        goToProfile()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }

                }
        }

        buttonRegister.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }

        buttonPasswordReset.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

    }

    private fun goToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

}