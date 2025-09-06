package se.linerotech.module201.guide

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import se.linerotech.module201.guide.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        configureToolbar()

        setClickListener()

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

    private fun setClickListener() {
        binding.buttonCreateAccount.setOnClickListener {
            validateCredentials()
        }
    }

    private fun validateCredentials() {
        val username = binding.editTextUsername.text.toString() ?: ""
        val email = binding.editTextEmail.text?.toString() ?: ""
        val password = binding.editTextPassword.text?.toString() ?: ""

        var isValid = true

        if (username.isEmpty()) {
            binding.editTextUsername.error = getString(R.string.username_cannot_be_empty)
            isValid = false
        }

        if (email.isEmpty()) {
            binding.editTextEmail.error = getString(R.string.invalid_email)
            isValid = false
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = getString(R.string.password_field_cannot_be_empty)
            isValid = false
        }

        if (isValid) {
            Toast.makeText(this, R.string.account_created, Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
