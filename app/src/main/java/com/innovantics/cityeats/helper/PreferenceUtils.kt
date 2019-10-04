package com.innovantics.cityeats.helper

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import android.widget.EditText
import android.widget.Toast
import com.innovantics.cityeats.R
import java.util.*
import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.widget.TextView



fun Activity.showSnack(text: String) = Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()

fun checkLength(editText: EditText, textInput: TextInputLayout):Boolean{
    val phone= editText.text.toString()
    return when {
        phone.isEmpty() -> {
            //button.isEnabled = false
            textInput.error = "please enter your phone number"
            false
        } phone.length >10 -> {
            //button.isEnabled = false
            textInput.error = "Invalid phone number"
            false
        }
        phone.length<10 -> {
            textInput.error = "Phone number must be 10 digits"
            false
        }
        else -> true
    }
}

fun setTextViewDrawableColor(textView: TextView, color: Int) {
    textView.setTextColor(color)
    for (drawable in textView.compoundDrawables) {
        if (drawable != null) {
            drawable.colorFilter =
                PorterDuffColorFilter(ContextCompat.getColor(textView.context, color), PorterDuff.Mode.SRC_IN)
        }
    }

}
fun Context.showAlert(paramString: String) {
    val localBuilder = AlertDialog.Builder(this)
    localBuilder.setMessage(paramString)
    localBuilder.setNeutralButton(R.string.ok) { _, _ -> }
    localBuilder.create().show()
}

fun Context.datePicker(): String {
    var date = ""
    val calendar = Calendar.getInstance()
    val aDay = calendar.get(Calendar.DAY_OF_MONTH)
    val aMonth = calendar.get(Calendar.MONTH)
    val aYear = calendar.get(Calendar.YEAR)

    val datePickerDialog = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val startDates = year.toString() + "-" + String.format("%02d",month + 1) + "-" + String.format("%02d",dayOfMonth)
            date =startDates

        }, aYear, aMonth, aDay

    )
    datePickerDialog.show()
    return date
}

fun Context.timePicker():String{
    var time =""
    val c = Calendar.getInstance()
    val hour = c.get(Calendar.HOUR)
    val minute = c.get(Calendar.MINUTE)
    val during = c.get(Calendar.AM_PM)

    val tpd = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener(function = { _, h, m ->
        time = "$h : $m :  $during"


    }),hour,minute,false)

    tpd.show()

    return time
}