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


class ResturantAdapter(/*private var accountList: List<AcctListModel>,*/ private var context: Context)
    : RecyclerView.Adapter<ResturantAdapter.AcctViewHolder>() {

    companion object {
       var genericListener: GenericListener<String>?= null
    }
    class AcctViewHolder(inflate: View): RecyclerView.ViewHolder(inflate) {
        var btnOpen: Button? = null
        var img: ImageView? = null
        var txtName: TextView?= null
        var txtRating: TextView?= null
        var txtRateCount: TextView?= null
        var txtCuisine: TextView?= null
        var txtDistance: TextView?= null
        var btnFav: ToggleButton?= null


        init {
            btnOpen = itemView.findViewById(R.id.btnOpen)
            txtRateCount = itemView.findViewById(R.id.txtRatingCount)
            txtRating = itemView.findViewById(R.id.txtRating)
            txtCuisine = itemView.findViewById(R.id.txtCuisine)
            txtDistance = itemView.findViewById(R.id.txtDistance)
            img = itemView.findViewById(R.id.imgRestaurant)
            txtName = itemView.findViewById(R.id.txtName)
            btnFav = itemView.findViewById(R.id.btnFav)
        }
    }

    override fun onCreateViewHolder(paramViewGroup: ViewGroup, paramInt: Int): AcctViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.custom_restaurant, paramViewGroup, false)

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

        val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF,
            0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator

        holder.btnOpen?.setOnClickListener {
            genericListener?.genericListener("click")
        }
        holder.btnFav?.setOnCheckedChangeListener(object:View.OnClickListener, CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                p0?.startAnimation(scaleAnimation)
                Log.d("fav", "am i here") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onClick(p0: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }



    override fun getItemCount(): Int {
        return 10
    }



}
