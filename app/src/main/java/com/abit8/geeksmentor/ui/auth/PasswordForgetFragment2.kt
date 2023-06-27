package com.abit8.geeksmentor.ui.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentPasswordForget2Binding
import org.json.JSONObject
import com.abit8.geeksmentor.ui.auth.SmsConfirmationView

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
            val smsCodeView = binding.smsCodeView
            val timerTextView = binding.tvTime

            startConfirmationCodeTimer(timerTextView)

            binding.btnConfirmCodeVerification.setOnClickListener {
                val confirmationCode = smsCodeView.getConfirmationCode()
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

            private fun resetPassword(actionCode: String, confirmationCode: String) {
                // Вместо этого вы можете использовать фактический запрос к серверу или файлу JSON
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


            private fun startConfirmationCodeTimer(timerTextView: TextView) {
        confirmationCodeTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeMillis = millisUntilFinished
                val seconds = millisUntilFinished / 1000
                timerTextView.text = "Осталось времени: $seconds сек."
            }

            override fun onFinish() {
                timerTextView.text = "Отправка кода..."
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
}
