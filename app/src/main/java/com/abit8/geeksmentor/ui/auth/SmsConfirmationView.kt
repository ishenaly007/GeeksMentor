package com.abit8.geeksmentor.ui.auth

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class SmsConfirmationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        // Установка атрибутов для текстового поля
        inputType = InputType.TYPE_CLASS_NUMBER // Установка типа ввода как числовой
        maxLines = 1 // Установка максимального количества строк в текстовом поле
        // Дополнительные настройки текстового поля, если необходимо
    }

    fun getConfirmationCode(): String {
        return text.toString().trim() // Получение введенного кода подтверждения без лишних пробелов
    }
}
