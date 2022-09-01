package com.example.ems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUp : AppCompatActivity() {
    private lateinit var emailSignUp: EditText
    private lateinit var passwordSignUp: EditText
    private lateinit var registerBtn: Button
    private lateinit var db:Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        emailSignUp=findViewById(R.id.emailSignUp)
        passwordSignUp=findViewById(R.id.passwordSignUp)
        registerBtn=findViewById(R.id.register)
        db=Database(this)
        registerBtn.setOnClickListener { addUser(emailSignUp.text.toString(),passwordSignUp.text.toString()) }
    }
    private fun addUser(email:String,password:String){
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "cannot save blank data!", Toast.LENGTH_SHORT).show()
        }else {
            val obj = Model(email, password)
            db.put(obj)
        }
    }
}