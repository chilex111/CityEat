package com.innovantics.cityeats.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.*
import com.innovantics.cityeats.R
import com.innovantics.cityeats.listener.GenericListener
import com.innovantics.cityeats.views.CircleImageView


class ReviewAdapter(/*private var accountList: List<String>,*/ private var context: Context,
                                                               private var state: Int
)
    : RecyclerView.Adapter<ReviewAdapter.AcctViewHolder>() {

    companion object {
        var stringListener: GenericListener<String>?= null
    }
    class AcctViewHolder(inflate: View): RecyclerView.ViewHolder(inflate) {
        var txtName: TextView? = null
        private var imgPhoto: CircleImageView? = null
        private var txtRating: TextView? = null
        private var txtDate: TextView? = null
        private var txtMsg: TextView? = null
        var txtSee: TextView? = null
        init {
            txtName = itemView.findViewById(R.id.checkFilter)
            imgPhoto = itemView.findViewById(R.id.imgReviewer)
            txtRating = itemView.findViewById(R.id.txtReviewerRate)
            txtDate = itemView.findViewById(R.id.txtReviewDate)
            txtMsg = itemView.findViewById(R.id.txtReviewMsg)
            txtSee = itemView.findViewById(R.id.txtShow)
        }
    }

    override fun onCreateViewHolder(paramViewGroup: ViewGroup, paramInt: Int): AcctViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.dialog_review, paramViewGroup, false)

        return AcctViewHolder(v)
    }

    override fun onBindViewHolder(holder: AcctViewHolder, position: Int) {
        // val accountModel = accountList[position]
        //  holder.txtName?.text = accountModel

        val scaleAnimation = ScaleAnimation(
            0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF,
            0.7f, Animation.RELATIVE_TO_SELF, 0.7f
        )
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator

        holder.txtSee?.setOnClickListener {
            it.startAnimation(scaleAnimation)
            stringListener?.genericListener(holder.txtName?.text.toString())
        }
    }


    override fun getItemCount(): Int {
        return if (state ==0) 3 //accountList.size
        else 10


    }

}
