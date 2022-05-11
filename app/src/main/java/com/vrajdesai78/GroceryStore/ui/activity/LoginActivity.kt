package com.vrajdesai78.GroceryStore.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vrajdesai78.GroceryStore.R
import com.vrajdesai78.GroceryStore.helper.PreferencesHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val sharedPref: PreferencesHelper by lazy {
        PreferencesHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginOrSignIn()
        navigationToRegistrasi()

    }

    private fun loginOrSignIn() {
        btn_sign_in.setOnClickListener {
            when {
                et_email_login.text.isEmpty() -> {
                    et_email_login.error = null
                    et_email_login.requestFocus()
                    Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show()
                }
                et_pass_login.text.isEmpty() -> {
                    et_pass_login.error = null
                    et_pass_login.requestFocus()
                    Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            }
        }
    }

    private fun navigationToRegistrasi() {
        tv_sign_up.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
}





