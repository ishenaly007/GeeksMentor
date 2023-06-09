package com.abit8.geeksmentor.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentPasswordForgetBinding
import com.google.firebase.auth.FirebaseAuth

class PasswordForgetFragment : Fragment() {

    private lateinit var binding: FragmentPasswordForgetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordForgetBinding.inflate(inflater, container, false)

        binding.btnConfirmEmailVerification.setOnClickListener {
            val email = binding.etEmailEmailVerification.text.toString().trim()
            if (email.isNotEmpty()) {
                resetPassword(email)
            } else {
                Toast.makeText(requireContext(), EMPTY_EMAIL_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun resetPassword(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                val context = requireContext()
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.password_reset_success),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val message = task.exception?.message
                    Toast.makeText(
                        context,
                        context.getString(R.string.password_reset_failure, message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private companion object {
        const val EMPTY_EMAIL_MESSAGE = "Введите адрес электронной почты"
    }
}
