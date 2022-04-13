package com.example.greenroom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    private lateinit var buttonLogout: Button
    private lateinit var buttonPasswordChange: Button
    private lateinit var profileTextView: TextView

    // II  nawili
    private lateinit var profileImageView: ImageView
    private lateinit var usernameEditText: EditText
    private lateinit var urlEditText: EditText
    private lateinit var changeButton: Button

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("userinfo")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        init()

        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(UserInfo::class.java) ?: return

//                if (userInfo == null){
//                    return
//                }

                profileTextView.text = userInfo.username

                Glide.with(this@ProfileActivity).load(userInfo.image).into(profileImageView)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })





    }

    private fun init(){

        buttonLogout = findViewById(R.id.logoutButton)
        buttonPasswordChange = findViewById(R.id.changePasswordProfileButton)
        profileTextView = findViewById(R.id.profileTextView)

        profileImageView = findViewById(R.id.profileImageView)
        usernameEditText = findViewById(R.id.usernameEditText)
        urlEditText = findViewById(R.id.urlEditText)
        changeButton = findViewById(R.id.changeButton)


        listeners()
    }

    private fun listeners() {

        changeButton.setOnClickListener {
            val url = urlEditText.text.toString()
            val username = usernameEditText.text.toString()

            val userInfo = UserInfo(username, url)

            db.child(auth.currentUser?.uid!!).setValue(userInfo)
        }

        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        buttonPasswordChange.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

    }
}