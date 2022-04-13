package com.example.greenroom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextEmailRegistration: EditText
    private lateinit var editTextPasswordRegistration: EditText
    private lateinit var buttonRegistration: Button
    private lateinit var buttonAlreadyRegistered: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        init()

    }

    private fun init(){

        editTextEmailRegistration = findViewById(R.id.emailRegistrationEditText)
        editTextPasswordRegistration = findViewById(R.id.passwordRegistrationEditText)
        buttonRegistration = findViewById(R.id.registrationButton)
        buttonAlreadyRegistered = findViewById(R.id.alreadyRegisteredButton)

        listeners()
    }

    private fun listeners(){

        buttonRegistration.setOnClickListener {
            val email = editTextEmailRegistration.text.toString()
            val password = editTextPasswordRegistration.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Empty!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        buttonAlreadyRegistered.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }


}