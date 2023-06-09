package com.abit8.geeksmentor.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return requireView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvHaveAccountEnter.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        binding.btnRegistrationSignup.setOnClickListener {
            val email = binding.etEmailSignup.text.toString()
            val password = binding.etPasswordSignup.text.toString()
            val confirmPassword = binding.etConfirmPasswordSignup.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                registerUser(email, password, confirmPassword)
                findNavController().navigate(R.id.scroll_home)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun registerUser(email: String, password: String, confirmPassword: String) {
        if (password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                // Пароль и его подтверждение совпадают
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Регистрация успешна
                            Toast.makeText(
                                requireContext(),
                                "Registration successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Дополнительные действия после успешной регистрации
                        } else {
                            // Регистрация не удалась
                            Toast.makeText(
                                requireContext(),
                                "Registration error: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                // Пароль и его подтверждение не совпадают
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Пароль или его подтверждение пустые
            Toast.makeText(context, "Please fill in all password fields", Toast.LENGTH_SHORT).show()
        }
    }

    //для выхода из аккаунта -> Firebase.auth.signOut()
}