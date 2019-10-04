package com.innovantics.cityeats.activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.innovantics.cityeats.R
import com.innovantics.cityeats.helper.checkLength
import com.innovantics.cityeats.helper.datePicker
import com.innovantics.cityeats.helper.showSnack
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btnFacebook
import kotlinx.android.synthetic.main.activity_sign_up.btnTwitter
import java.util.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnTwitter.setOnClickListener(this)
        btnFacebook.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
        txtDate.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnSignUp ->{
                if (infoCheck()){
                    showSnack("Welcome ${editFirstName.text}")
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
            }
            R.id.btnFacebook ->{
                showSnack("Still under development")
            }
            R.id.btnTwitter ->{
                showSnack("Still under development")
            }
            R.id.txtDate ->{
                txtDate.text =datePicker()
            }
        }
    }

    private fun infoCheck(): Boolean{
        return when {
            editFirstName.text.toString().isEmpty() -> {
                text_input_first_name.error = "Please enter your first name"
                false
            }
            editLastName.text.toString().isEmpty() -> {
                text_input_last_name.error = "Please enter your last name"
                false
            }
            editEmail.text.toString().isEmpty() -> {
                text_input_email.error = "Please enter your email"
                false
            }txtDate.text.toString().isEmpty() -> {
                text_input_email.error = "Please enter your email"
                false
            }
            else -> true
        }
    }
}
