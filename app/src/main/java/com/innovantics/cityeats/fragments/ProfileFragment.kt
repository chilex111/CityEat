package com.innovantics.cityeats.fragments


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.innovantics.cityeats.R
import com.innovantics.cityeats.activity.DashboardActivity
import com.innovantics.cityeats.activity.SignUpPhoneActivity
import kotlinx.android.synthetic.main.custom_header.*
import kotlinx.android.synthetic.main.custom_header.view.*
import kotlinx.android.synthetic.main.dialog_feedback.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.relativeProfileHead
import kotlinx.android.synthetic.main.fragment_profile_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.cardAbout ->{
                (activity as DashboardActivity).about()
            }
            R.id.relativeProfileHead ->{
                (activity as DashboardActivity).editProfile()
            }
            R.id.cardMyRestaurant ->{
                (activity as DashboardActivity).myRestaurants()
            }
            R.id.btnFeedBack ->{
                dialogFeed()
            }
            R.id.btnLogout ->{
                val dialogBuild = AlertDialog.Builder(activity)
                dialogBuild.setMessage("Are you sure you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton("Cancel") { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    .setNegativeButton(getString(R.string.log_out)) { _, _ ->
                        startActivity(Intent(activity, SignUpPhoneActivity::class.java))
                    }
                val alert = dialogBuild.create()

                alert.show()
            }
        }
    }

    private fun dialogFeed(){
        val dialog = Dialog(activity!!, R.style.Dialog)
        dialog.setContentView(R.layout.dialog_feedback)
        dialog.relativeHeaderFeed.txtMsg.text = getString(R.string.send_feedback)
        dialog.relativeHeaderFeed.txtClear.text = getString(R.string.send)
        dialog.relativeHeaderFeed.btnCloseFilter.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btnCloseFilter.setOnClickListener {
            //send feed back()
        }
        dialog.show()
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        relativeProfileHead.setOnClickListener(this)
        cardMyRestaurant.setOnClickListener(this)
        cardAbout.setOnClickListener(this)
        btnLogout.setOnClickListener(this)
        btnFeedBack.setOnClickListener(this)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
