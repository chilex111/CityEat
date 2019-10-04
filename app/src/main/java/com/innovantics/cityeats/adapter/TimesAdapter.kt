package com.innovantics.cityeats.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.*
import com.innovantics.cityeats.R
import com.innovantics.cityeats.listener.GenericListener


class TimesAdapter(/*private var accountList: List<String>,*/ private var context: Context)
    : RecyclerView.Adapter<TimesAdapter.AcctViewHolder>() {

    class AcctViewHolder(inflate: View): RecyclerView.ViewHolder(inflate) {
        private var txtDay: TextView? = null
        private var txtTime: TextView? = null
        init {
            txtDay = itemView.findViewById(R.id.txtDay)
            txtTime = itemView.findViewById(R.id.txtTime)
        }
    }

    override fun onCreateViewHolder(paramViewGroup: ViewGroup, paramInt: Int): AcctViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.dialog_open, paramViewGroup, false)

        return AcctViewHolder(v)
    }

    override fun onBindViewHolder(holder: AcctViewHolder, position: Int) {
        //val accountModel = accountList[position]

    }

    override fun getItemCount(): Int {
        return 7 //accountList.size
    }



}
