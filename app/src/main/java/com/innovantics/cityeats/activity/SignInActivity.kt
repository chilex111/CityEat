package com.innovantics.cityeats.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.innovantics.cityeats.R
import com.innovantics.cityeats.helper.checkLength
import com.innovantics.cityeats.helper.showSnack
import kotlinx.android.synthetic.main.activity_sign_in.*
import android.widget.Toast
import com.hbb20.CountryCodePicker



class SignInActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnFacebook.setOnClickListener(this)
        btnTwitter.setOnClickListener(this)
        btnSignIn.setOnClickListener(this)
        linearSign.setOnClickListener(this)

        codePicker.setOnCountryChangeListener {
            Toast.makeText(this, "Updated " + codePicker.selectedCountryName, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.linearSign ->{
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            R.id.btnSignIn ->{
                if (checkLength(editPhone, text_input_phone)){
                    showSnack("Welcome back")
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
            }
            R.id.btnFacebook ->{
                showSnack("Still under development")
            }
            R.id.btnTwitter ->{
                showSnack("Still under development")
            }
        }
    }
}
