package com.abit8.geeksmentor.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.tvRegisterAccount.setOnClickListener {
            findNavController().navigate(R.id.logInFragment)
        }

        binding.btnEnterMain.setOnClickListener {
            val email = binding.etLoginMain.text.toString()
            val password = binding.etPasswordMain.text.toString()
            signInButtonClicked(email, password)
        }

        return binding.root
    }

    private fun signInButtonClicked(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Вход выполнен успешно
                            findNavController().navigate(R.id.scroll_home)
                        } else {
                            // Вход не удался
                            val message = task.exception?.message
                            if (task.exception is FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(
                                    requireContext(),
                                    "Неправильный формат электронной почты",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Ошибка входа: $message",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Обработка исключений
                        Toast.makeText(
                            requireContext(),
                            "Ошибка входа: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Неправильный формат электронной почты",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

