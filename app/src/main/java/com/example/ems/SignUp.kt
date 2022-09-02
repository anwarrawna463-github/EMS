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
        registerBtn.setOnClickListener {
            addUser(emailSignUp.text.toString(), passwordSignUp.text.toString())
        }
    }
    private fun addUser(email:String,password:String){
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "cannot save blank data!", Toast.LENGTH_SHORT).show()
        }
        else {
            if (isValidEmail(email)) {
                val obj = Model(email, password)
                db.put(obj)
                Toast.makeText(this,"User added successfully!",Toast.LENGTH_LONG).show()
                emailSignUp.text.clear()
                passwordSignUp.text.clear()
            }
            else{
                Toast.makeText(this,"Not a valid email,try again!",Toast.LENGTH_LONG).show()
                emailSignUp.text.clear()
                passwordSignUp.text.clear()
            }
        }
    }
    private fun isValidEmail(email:String):Boolean{
        return Regex("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+").matches(email)
    }
}