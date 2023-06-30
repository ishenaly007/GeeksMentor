package com.abit8.geeksmentor.presentation.auth

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentLogInBinding
import org.json.JSONObject

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private var userLoggedIn = false // Флаг состояния аутентификации пользователя
    private var isPasswordVisible = false // Флаг видимости пароля

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
                    "Пожалуйста, заполните все поля",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.eyeIcon.setOnClickListener {
            togglePasswordVisibility(binding.etPasswordSignup, binding.eyeIcon)
        }

        binding.eyeIconConfirm.setOnClickListener {
            togglePasswordVisibility(binding.etConfirmPasswordSignup, binding.eyeIconConfirm)
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
                        "Регистрация успешна",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Устанавливаем флаг состояния аутентификации пользователя в true
                    userLoggedIn = true

                    // Дополнительные действия после успешной регистрации
                    findNavController().navigate(R.id.scroll_home)
                } else {
                    // Регистрация не удалась
                    Toast.makeText(
                        requireContext(),
                        "Ошибка регистрации: что-то пошло не так",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                // Пароль и его подтверждение не совпадают
                Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Пароль или его подтверждение пустые
            Toast.makeText(context, "Пожалуйста, заполните все поля пароля", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun togglePasswordVisibility(inputField: EditText, eyeIcon: ImageView) {
        if (isPasswordVisible) {
            // Пароль видимый - скрываем его
            inputField.transformationMethod = PasswordTransformationMethod.getInstance()
            eyeIcon.setImageResource(R.drawable.eye_vector)
            isPasswordVisible = false
        } else {
            // Пароль скрытый - показываем его
            inputField.transformationMethod = HideReturnsTransformationMethod.getInstance()
            eyeIcon.setImageResource(R.drawable.baseline_remove_red_eye_24)
            isPasswordVisible = true
        }

        // Перемещаем курсор в конец поля ввода
        inputField.setSelection(inputField.text?.length ?: 0)
    }

    override fun onResume() {
        super.onResume()

        // Проверяем состояние аутентификации пользователя
        if (!userLoggedIn) {
            // Пользователь не аутентифицирован, блокируем доступ к следующему фрагменту
            binding.btnRegistrationSignup.isEnabled = false
            binding.btnRegistrationSignup.alpha = 0.5f
        } else {
            // Пользователь аутентифицирован, разрешаем доступ к следующему фрагменту
            binding.btnRegistrationSignup.isEnabled = true
            binding.btnRegistrationSignup.alpha = 1.0f
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}