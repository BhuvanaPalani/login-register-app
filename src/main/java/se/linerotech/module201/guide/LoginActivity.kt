package se.linerotech.module201.guide

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import se.linerotech.module201.guide.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        configureToolbar()
        setClickListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configureToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    private fun setClickListeners() {
        binding.buttonFacebook.setOnClickListener {
            sendMessage(message = R.string.facebook_login)
        }

        binding.buttonGoogle.setOnClickListener {
            sendMessage(message = R.string.google_login)
        }

        binding.buttonTwitter.setOnClickListener {
            sendMessage(message = R.string.twitter_login)
        }

        binding.textViewForgotPassword.setOnClickListener {
            sendMessage(message = R.string.forgot_password)
        }

        binding.buttonLogin.setOnClickListener {
            validateCredentials()
        }
    }

    private fun validateCredentials() {
        if (!isEmailValid()) {
            notifyInvalidEmail()
        }
        if (!isPasswordValid()) {
            notifyInvalidPassword()
        }
        if (isEmailValid() && isPasswordValid()) {
            sendMessage(message = R.string.successful_login)
        }
    }

    private fun notifyInvalidEmail() {
        binding.editTextEmail.error = getString(R.string.invalid_email)
    }

    private fun notifyInvalidPassword() {
        binding.editTextPassword.error = getString(R.string.password_field_cannot_be_empty)
    }

    private fun isEmailValid(): Boolean {
        val email = binding.editTextEmail.text.toString().trim()
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(): Boolean {
        val password = binding.editTextPassword.text.toString().trim()
        return password.isNotEmpty()
    }

    private fun sendMessage(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
