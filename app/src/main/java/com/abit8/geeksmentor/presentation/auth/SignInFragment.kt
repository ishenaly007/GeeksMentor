package com.abit8.geeksmentor.presentation.auth

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
import org.json.JSONObject

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
                // Вместо этого вы можете использовать фактический запрос к серверу или файлу JSON
                val json = """
                {
                    "success": true
                }
            """.trimIndent()

                // Разбор JSON и проверка успешности входа
                val jsonObject = JSONObject(json)
                val success = jsonObject.getBoolean("success")

                if (success) {
                    // Вход выполнен успешно
                    findNavController().navigate(R.id.scroll_home)
                } else {
                    // Вход не удался
                    Toast.makeText(
                        requireContext(),
                        "Ошибка входа: неверный адрес электронной почты или пароль",
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