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
import org.json.JSONObject

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHaveAccountEnter.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        binding.btnRegistrationSignup.setOnClickListener {
            val email = binding.etEmailSignup.text.toString()
            val password = binding.etPasswordSignup.text.toString()
            val confirmPassword = binding.etConfirmPasswordSignup.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                signUpButtonClicked(email, password, confirmPassword)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun signUpButtonClicked(email: String, password: String, confirmPassword: String) {
        if (password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                // Вместо этого вы можете использовать фактический запрос к серверу или файлу JSON
                val json = """
                {
                    "success": true
                }
            """.trimIndent()

                // Разбор JSON и проверка успешности регистрации
                val jsonObject = JSONObject(json)
                val success = jsonObject.getBoolean("success")

                if (success) {
                    // Регистрация успешна
                    Toast.makeText(
                        requireContext(),
                        "Registration successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Дополнительные действия после успешной регистрации
                    findNavController().navigate(R.id.scroll_home)
                } else {
                    // Регистрация не удалась
                    Toast.makeText(
                        requireContext(),
                        "Registration error: Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //для выхода из аккаунта -> Firebase.auth.signOut()
}
