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
import com.abit8.geeksmentor.databinding.FragmentNewPasswordBinding

class NewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentNewPasswordBinding
    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPasswordBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        binding.btnConfirm.setOnClickListener {
            val newPassword = binding.etNewPassword.text.toString()
            val confirmPassword = binding.etPasswordConfirm.text.toString()

            if (newPassword.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (newPassword == confirmPassword) {
                    if (newPassword != getOldPassword()) {
                        // Новый пароль совпадает с подтверждением и не совпадает со старым паролем
                        findNavController().navigate(R.id.scroll_home)
                    } else {
                        // Новый пароль совпадает со старым паролем
                        binding.cannotBeTheSameLayout.visibility = View.VISIBLE
                    }
                } else {
                    // Новый пароль и подтверждение не совпадают
                    binding.passwordIsNotTheSame.visibility = View.VISIBLE
                }
            } else {
                // Поля для нового пароля или подтверждения пустые
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.eyeClosedOnNewPassEt.setOnClickListener {
            togglePasswordVisibility(binding.etNewPassword, binding.eyeClosedOnNewPassEt)
        }

        binding.eyeClosedOnConfirmPassEt.setOnClickListener {
            togglePasswordVisibility(binding.etPasswordConfirm, binding.eyeClosedOnConfirmPassEt)
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

    private fun getOldPassword(): String {
        // Получите старый пароль из вашего источника данных (например, SharedPreferences)
        return "old_password"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.cannotBeTheSameLayout.visibility = View.GONE
        binding.noInternetLayout.visibility = View.GONE
    }
}