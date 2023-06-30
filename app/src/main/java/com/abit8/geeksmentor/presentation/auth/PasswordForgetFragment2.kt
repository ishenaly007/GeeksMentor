package com.abit8.geeksmentor.presentation.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentPasswordForget2Binding
import org.json.JSONObject
import com.abit8.geeksmentor.presentation.auth.SmsConfirmationView

class PasswordForgetFragment2 : Fragment() {

    private lateinit var binding: FragmentPasswordForget2Binding
    private var confirmationCodeTimer: CountDownTimer? = null
    private var timerRunning = false
    private var remainingTimeMillis: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordForget2Binding.inflate(inflater, container, false)

        val actionCode = arguments?.getString("actionCode")
        if (actionCode != null) {
            startConfirmationCodeTimer()

            binding.btnConfirmCodeVerification.setOnClickListener {
                val confirmationCode = binding.smsCodeView.getConfirmationCode()
                if (confirmationCode.isNotEmpty()) {
                    resetPassword(actionCode, confirmationCode)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Введите код подтверждения",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        return binding.root
    }

    private fun onBackPressed() {
        // Если таймер работы кода подтверждения активен, отобразите сообщение о предупреждении
        if (timerRunning) {
            Toast.makeText(
                requireContext(),
                "Подтверждение кода активно. Вы уверены, что хотите покинуть этот экран?",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // В противном случае, вызовите супер-метод для стандартного поведения "Назад"
            findNavController().popBackStack()
        }
    }


    private fun resetPassword(actionCode: String, confirmationCode: String) {
        // Здесь должен быть фактический запрос к серверу или файлу JSON для сброса пароля
        val json = """
            {
                "success": true
            }
        """.trimIndent()

        // Разбор JSON и проверка успешности сброса пароля
        val jsonObject = JSONObject(json)
        val success = jsonObject.getBoolean("success")

        if (success) {
            // Сброс пароля успешно подтвержден
            Toast.makeText(
                requireContext(),
                "Сброс пароля успешно подтвержден",
                Toast.LENGTH_SHORT
            ).show()

            // Переход в другой фрагмент
            findNavController().navigate(R.id.new_password_fragment)
        } else {
            // Ошибка подтверждения сброса пароля
            Toast.makeText(
                requireContext(),
                "Ошибка подтверждения сброса пароля",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun startConfirmationCodeTimer() {
        confirmationCodeTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeMillis = millisUntilFinished
                val seconds = millisUntilFinished / 1000
                binding.tvTime.text = "Осталось времени: $seconds сек."
            }

            override fun onFinish() {
                binding.tvTime.text = "Отправка кода..."
                // Здесь выполняйте логику повторной отправки кода
                val actionCode = arguments?.getString("actionCode")
                val confirmationCode = binding.smsCodeView.getConfirmationCode()
                if (actionCode != null && confirmationCode.isNotEmpty()) {
                    resetPassword(actionCode, confirmationCode)
                }
            }
        }

        confirmationCodeTimer?.start()
        timerRunning = true
    }

    private fun stopConfirmationCodeTimer() {
        confirmationCodeTimer?.cancel()
        timerRunning = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopConfirmationCodeTimer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
    }
}
