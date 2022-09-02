package com.example.ems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var emailBox: EditText
    private lateinit var passwordBox: EditText
    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button
    private lateinit var db:Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailBox =findViewById(R.id.emailBox)
        passwordBox =findViewById(R.id.passwordBox)
        loginBtn=findViewById(R.id.logIn)
        signUpBtn=findViewById(R.id.signUp)
        db=Database(this)
        loginBtn.setOnClickListener {
            var email= this.emailBox.text.toString()
            val password=this.passwordBox.text.toString()
            if(!db.isExist(email)) {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show()
            }else{
                if (db.checkPassword(email, password))
                {
                    Toast.makeText(this, "Login was successful!", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG).show()
                }
            }
        }
        signUpBtn.setOnClickListener {
            val act=Intent(this,SignUp::class.java)
            startActivity(act)
        }

    }
}