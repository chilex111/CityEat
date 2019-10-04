package com.innovantics.cityeats.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.innovantics.cityeats.R
import com.innovantics.cityeats.helper.checkLength
import com.innovantics.cityeats.helper.showSnack
import kotlinx.android.synthetic.main.activity_signup_phone.*

class SignUpPhoneActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_phone)

        btnContinue.setOnClickListener(this)
        linearSign.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
           R.id.btnContinue ->{
               if (checkLength(editPhone, text_input_phone)) {
                   showSnack("Welcome to City Eats")
                   startActivity(Intent(this, SignUpActivity::class.java))
               }
           }
            R.id.linearSign ->{
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }
    }


}
