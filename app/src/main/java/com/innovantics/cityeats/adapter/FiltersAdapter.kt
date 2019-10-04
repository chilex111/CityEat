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


class FiltersAdapter(private var accountList: List<String>, private var context: Context)
    : RecyclerView.Adapter<FiltersAdapter.AcctViewHolder>() {

    companion object {
       var stringListener: GenericListener<String>?= null
    }
    class AcctViewHolder(inflate: View): RecyclerView.ViewHolder(inflate) {
        var checkFilter: CheckBox? = null
        init {
            checkFilter = itemView.findViewById(R.id.checkFilter)
        }
    }

    override fun onCreateViewHolder(paramViewGroup: ViewGroup, paramInt: Int): AcctViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.dialog_check, paramViewGroup, false)

        return AcctViewHolder(v)
    }

    override fun onBindViewHolder(holder: AcctViewHolder, position: Int) {
        val accountModel = accountList[position]
        holder.checkFilter?.text = accountModel

        val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF,
            0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator

        holder.checkFilter?.setOnCheckedChangeListener(object:View.OnClickListener, CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                p0?.startAnimation(scaleAnimation)
                stringListener?.genericListener(holder.checkFilter?.text.toString())
                Log.d("picked::", "${holder.checkFilter?.text}") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onClick(p0: View?) {
            }
        })
    }

    override fun getItemCount(): Int {
        return accountList.size
    }



}
