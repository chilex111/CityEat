package com.innovantics.cityeats.fragments


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.innovantics.cityeats.R
import com.innovantics.cityeats.activity.DashboardActivity
import kotlinx.android.synthetic.main.custom_header.*
import kotlinx.android.synthetic.main.custom_header.view.*
import kotlinx.android.synthetic.main.custom_header.view.btnCloseFilter
import kotlinx.android.synthetic.main.custom_header.view.txtClear
import kotlinx.android.synthetic.main.custom_header.view.txtMsg
import kotlinx.android.synthetic.main.dialog_about.*
import kotlinx.android.synthetic.main.fragment_about.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AboutFragment : Fragment(), View.OnClickListener, DashboardActivity.BackPressed {
    override fun onBackPress() {
        activity?.onBackPressed()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnPrivacy ->{
                dialog(R.layout.dialog_about, getString(R.string.privacy_policy))
            }
            R.id.btnTerms ->{
                dialog(R.layout.dialog_about, getString(R.string.terms_of_use))
            }
            R.id.btnCloseFilter->{

            }
        }
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
        DashboardActivity.btnPressed = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnPrivacy.setOnClickListener (this)
        btnTerms.setOnClickListener (this)


    }

    fun dialog(dialog_about: Int, header: String) {
        val dialog = Dialog(activity)
        dialog.setContentView(dialog_about)
        dialog.relativeHeaderAbout.btnCloseFilter.setImageResource(R.drawable.ic_arrow_back)
        dialog.relativeHeaderAbout.txtMsg.text ="$header"
        dialog.txtClear.visibility = View.GONE

        dialog.show()
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
