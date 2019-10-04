package com.innovantics.cityeats.adapter

import android.annotation.SuppressLint
import android.app.TimePickerDialog
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
import com.innovantics.cityeats.helper.datePicker
import com.innovantics.cityeats.helper.timePicker
import com.innovantics.cityeats.listener.GenericListener
import java.text.SimpleDateFormat
import java.util.*


class OpenHoursAdapter(private var accountList: List<String>, private var context: Context)
    : RecyclerView.Adapter<OpenHoursAdapter.AcctViewHolder>() {
    private var toggle: Int =0
    companion object {
       var stringListener: GenericListener<String>?= null
    }
    class AcctViewHolder(inflate: View): RecyclerView.ViewHolder(inflate) {
         var toggleExpand: ToggleButton? = null
         var txtDay: TextView? = null
         var txtOpen: TextView? = null
         var txtClose: TextView? = null
         var relativePick: RelativeLayout? = null
        init {
            toggleExpand = itemView.findViewById(R.id.btnOpen)
            txtDay = itemView.findViewById(R.id.txtDay)
            txtOpen = itemView.findViewById(R.id.txtOpenTime)
            txtClose = itemView.findViewById(R.id.txtCloseTime)
            relativePick = itemView.findViewById(R.id.relativePick)
        }
    }

    override fun onCreateViewHolder(paramViewGroup: ViewGroup, paramInt: Int): AcctViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.custom_day_pick, paramViewGroup, false)

        return AcctViewHolder(v)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: AcctViewHolder, position: Int) {
        val accountModel = accountList[position]
        holder.txtDay?.text = accountModel


        holder.toggleExpand?.setOnClickListener {
            if (toggle ==1){
                holder.relativePick?.visibility = View.GONE
                toggle = 0
            }
            else{
                holder.relativePick?.visibility = View.VISIBLE
                toggle = 1
            }
        }

        holder.txtOpen?.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)
            val during = c.get(Calendar.AM_PM)


            val tpd = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(function = { _, h, m ->

                val am_pm = if (during == Calendar.PM)
                    "PM"
                else
                    "AM"

                holder.txtOpen?.text ="$h : $m :  $am_pm"


            }),hour,minute,false)

            tpd.show()
        }
        holder.txtClose?.setOnClickListener {


            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)
            val during = c.get(Calendar.AM)

            val tpd = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(function = { _, h, m ->

               var am_pm =""

                if (during == Calendar.PM){
                am_pm=   "PM"
               }
                else if (during == Calendar.AM){
                 am_pm=  "AM"
                }
                holder.txtClose?.text = "$h : $m :  $am_pm"


            }),hour,minute,false)

            tpd.show()

        }

    }

    override fun getItemCount(): Int {
        return accountList.size
    }



}
