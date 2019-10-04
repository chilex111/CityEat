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


class RecentViewedAdapter(/*private var accountList: List<AcctListModel>,*/ private var context: Context)
    : RecyclerView.Adapter<RecentViewedAdapter.AcctViewHolder>() {

    companion object {
       var genericListener: GenericListener<String>?= null
    }
    class AcctViewHolder(inflate: View): RecyclerView.ViewHolder(inflate) {
        var btnReview: Button? = null
        var img: ImageView? = null
        var txtName: TextView?= null
        var txtStreet: TextView?= null


        init {
            btnReview = itemView.findViewById(R.id.btnReview)
            txtStreet = itemView.findViewById(R.id.txtDistance)
            img = itemView.findViewById(R.id.imgRestaurant)
            txtName = itemView.findViewById(R.id.txtName)
        }
    }

    override fun onCreateViewHolder(paramViewGroup: ViewGroup, paramInt: Int): AcctViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.custom_recently_viewed, paramViewGroup, false)

        return AcctViewHolder(v)
    }

    override fun onBindViewHolder(holder: AcctViewHolder, position: Int) {

     /*   val accountModel = accountList[position]
        val name =  accountModel.firstName+" "+accountModel.surname+"'s Account"
        if (accountList.size >1){
            holder.txtName!!.visibility = View.GONE
        }
        if (holder.adapterPosition ==0){
            holder.txtAcctType!!.text = context.getString(R.string.main_acct)
        }
        else if(holder.adapterPosition ==1) {
            holder.txtAcctType!!.text = context.getString(R.string.sub_acct)
            holder.checkFilter!!.background = context.getDrawable(R.drawable.rectangel_red)

        }
            holder.checkFilter!!.text = name
            holder.checkFilter!!.setOnClickListener {
                genericListener!!.genericListener(accountModel)

        }*/

        holder.btnReview?.setOnClickListener {
            genericListener?.genericListener("click")
        }

    }



    override fun getItemCount(): Int {
        return 5
    }



}
