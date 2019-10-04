package com.innovantics.cityeats.fragments


import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.innovantics.cityeats.R
import kotlinx.android.synthetic.main.custom_header.*
import kotlinx.android.synthetic.main.custom_header.view.*
import kotlinx.android.synthetic.main.dialog_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile_detail.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var aDay: Int = 0
    private var aMonth: Int = 0
    private var aYear: Int = 0
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
        return inflater.inflate(R.layout.fragment_profile_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        btnEdit.setOnClickListener {
            dialogEdit()
        }
    }

   private fun dialogEdit(){
        val dialog = Dialog(activity!!, R.style.Dialog)
       dialog.setContentView(R.layout.dialog_edit_profile)
       dialog.relativeHeaderEdit.txtMsg.text = getString(R.string.edit_profile_info)
       dialog.relativeHeaderEdit.txtClear.text = getString(R.string.save)
       dialog.relativeHeaderEdit.btnCloseFilter.setOnClickListener {
           dialog.dismiss()
       }
       dialog.btnCloseFilter.setOnClickListener {
           editProfile()
       }
       dialog.txtDate.setOnClickListener {
          date(dialog.txtDate)
       }
       dialog.show()
    }
    private fun editProfile(){

    }
    fun date(txtDate: TextView) {
        val calendar = Calendar.getInstance()
        aDay = calendar.get(Calendar.DAY_OF_MONTH)
        aMonth = calendar.get(Calendar.MONTH)
        aYear = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(activity!!,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val startDates = year.toString() + "-" + String.format("%02d",month + 1) + "-" + String.format("%02d",dayOfMonth)
                txtDate.text =startDates
            }, aYear, aMonth, aDay

        )

        datePickerDialog.show()
    }
    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
