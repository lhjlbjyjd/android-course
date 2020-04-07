package com.learning.helloworld

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_login.*
import java.math.BigInteger
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        registerButton.setOnClickListener {
            preferences.edit {
                val passwordMD5 = String.format("%032x", BigInteger(1, MessageDigest.getInstance("MD5")
                    .digest("${passwordEditText.text}".toByteArray(Charsets.UTF_8)))
                )
                putString(KEY_LOGIN, loginEditText.text.toString())
                putString(KEY_PASSWORD, passwordMD5)
            }
        }

        confirmButton.setOnClickListener {
            val realLogin = preferences.getString(KEY_LOGIN, "") ?: ""
            val realPasswordMD5 = preferences.getString(KEY_PASSWORD, "") ?: ""
            val login = loginEditText.text.toString()
            val passwordMD5 = String.format("%032x", BigInteger(1, MessageDigest.getInstance("MD5")
                .digest("${passwordEditText.text}".toByteArray(Charsets.UTF_8)))
            )

            if (login == realLogin) {
                if (passwordMD5 == realPasswordMD5) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(KEY_LOGIN, login)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong password", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val PREF_NAME = "Preferences"
        const val KEY_LOGIN = "login"
        const val KEY_PASSWORD = "password"
    }
}
