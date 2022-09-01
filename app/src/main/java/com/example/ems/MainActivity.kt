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
            val email=emailBox.text.toString()
            //val password=passwordBox.text.toString()
            val bl=db.isExist(email)
            if(!bl)
            Toast.makeText(this,"not exist",Toast.LENGTH_LONG).show()
            /*if (!isValidEmail(email)) {
                println("Not a valid email")
            }
            if (!db.isExist(email)) {
                println("User does not exist")
            }
            if (checkPassword(email, password))
            fun isValidEmail(email:String):Boolean{
                return Regex("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+").matches(email)
            }
            fun checkPassword(email:String,password:String):Boolean{
                return database[email].equals(password)
            }*/
        }
        signUpBtn.setOnClickListener {
            val act=Intent(this,SignUp::class.java)
            startActivity(act) }

    }
    private fun isValidEmail(email:String):Boolean{
        return Regex("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+").matches(email)
    }
}