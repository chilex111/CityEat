package com.innovantics.cityeats.fragments


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.innovantics.cityeats.R
import com.innovantics.cityeats.activity.DashboardActivity
import com.innovantics.cityeats.adapter.FiltersAdapter
import com.innovantics.cityeats.adapter.OpenHoursAdapter
import com.innovantics.cityeats.helper.cuisineOption
import com.innovantics.cityeats.helper.daysOption
import com.innovantics.cityeats.helper.establishmentOption
import com.innovantics.cityeats.helper.showAlert
import com.innovantics.cityeats.listener.GenericListener
import kotlinx.android.synthetic.main.custom_header.view.*
import kotlinx.android.synthetic.main.dialog_recycler.*
import kotlinx.android.synthetic.main.fragment_add_restaurant.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
@SuppressLint("SetTextI18n")
class AddRestaurantFragment : Fragment(), View.OnClickListener, GenericListener<String>, DashboardActivity.BackPressed {
    override fun onBackPress() {
        activity?.onBackPressed()
    }

    private var state:Int = 0


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.relativeEstablish ->{
                val establish = establishmentOption()
                state = 1
                dialog(establish, "establishment type")
            }
            R.id.relativeCuisine ->{
                val cuisine = cuisineOption()
                state= 2
                dialog(cuisine,"cuisine type")
            }
            R.id.chooseLocation ->{
                createChildFragment(MapLocationFragment())
            }
            R.id.txtClear ->{
                activity?.showAlert("${editRestaurantName.text} has been added and will be")
                Toast.makeText(activity,"this feature is under development", Toast.LENGTH_LONG).show()
            }
            R.id.btnCloseFilter ->{
                activity?.onBackPressed()
            }
            R.id.relativeOpens ->{
                val time = daysOption()
                state = 3
                dialogDate(time)

            }
        }

    }

    override fun genericListener(genericModel: String) {

        when(state){
            1->{
                if (txtEstablish.text.toString().isNotEmpty())
                    txtEstablish.text = "${txtEstablish.text},$genericModel,"
                else txtEstablish.text = genericModel
            }
            2->{
                if (txtCuisine.text.toString().isNotEmpty())
                    txtCuisine.text = "${txtCuisine.text},$genericModel,"
                else txtCuisine.text = genericModel
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
        return inflater.inflate(R.layout.fragment_add_restaurant, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        relativeCuisine.setOnClickListener(this)
        relativeEstablish.setOnClickListener(this)
        chooseLocation.setOnClickListener(this)/*
        toolbarAdd.btnCloseFilter.setOnClickListener(this)
        toolbarAdd.txtClear.setOnClickListener(this)
        toolbarAdd.txtClear.text = "Save"
        toolbarAdd.txtMsg.text = "Add a Restaurant"*/

        relativeOpens.setOnClickListener(this)
    }


    private fun dialog(filterList: ArrayList<String>, title: String) {
       val  dialog = Dialog(activity!!, R.style.Dialog)
        dialog.setContentView(R.layout.dialog_recycler)
        dialog.show()
        dialog.txtTitle?.text = "Choose $title"
        dialog.btnClose?.setOnClickListener{
            FiltersAdapter.stringListener = this
            dialog.dismiss()
        }
        setUpRecyclerView(dialog.dialog_recycler, filterList)
    }
    private fun dialogDate(filterList: ArrayList<String>) {
       val  dialog = Dialog(activity!!, R.style.Dialog)
        dialog.setContentView(R.layout.dialog_recycler)
        dialog.show()
        dialog.txtTitle?.text = "Open hours"
        dialog.btnClose?.setOnClickListener{
            FiltersAdapter.stringListener = this
            dialog.dismiss()
        }
        setUpDateRecyclerView(dialog.dialog_recycler, filterList)
    }

    private fun setUpDateRecyclerView(recyclerView: RecyclerView, filterList: ArrayList<String>) {
        val redeemAdapter = OpenHoursAdapter(filterList,activity!!)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = redeemAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.requestFocus()
    }
    private fun setUpRecyclerView(recyclerView: RecyclerView, filterList: ArrayList<String>) {
        val redeemAdapter = FiltersAdapter(filterList,activity!!)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = redeemAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.requestFocus()
    }

    private fun createChildFragment(fragmentClass: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentClass)
            .addToBackStack(null)
            .commit()
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddRestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
