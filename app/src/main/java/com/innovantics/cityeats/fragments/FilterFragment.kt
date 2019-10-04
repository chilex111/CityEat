package com.innovantics.cityeats.fragments


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton

import com.innovantics.cityeats.R
import com.innovantics.cityeats.adapter.FiltersAdapter
import com.innovantics.cityeats.helper.cuisineOption
import com.innovantics.cityeats.helper.dietaryOption
import com.innovantics.cityeats.helper.establishmentOption
import com.innovantics.cityeats.listener.GenericListener
import kotlinx.android.synthetic.main.custom_header.view.*
import kotlinx.android.synthetic.main.dialog_recycler.*
import kotlinx.android.synthetic.main.fragment_fliter.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

@SuppressLint("SetTextI18n")
class FilterFragment : Fragment(), View.OnClickListener, GenericListener<String> {
    private var listener: ArrayList<String> = ArrayList()
    private var state:Int = 0
    var dialog: Dialog? = null

    override fun genericListener(genericModel: String) {
        listener.add(genericModel)
        when(state){
            1->{
                if (txtEstablish.text.toString().isNotEmpty())
                txtEstablish.text = "${txtEstablish.text},$genericModel,"
                else txtEstablish.text = genericModel
            }
            2->{
                if (!txtCuisine.text.toString().isNullOrEmpty())
                txtCuisine.text = "${txtCuisine.text},$genericModel,"
                else txtCuisine.text = genericModel
            }
            3->{
               if (txtDietary.text.isNotEmpty())
                txtDietary.text = "${txtDietary.text},$genericModel,"
                else txtDietary.text = genericModel
            }
        }
    }



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
            R.id.relativeDietary ->{
                val dietary = dietaryOption()
                state = 3
                dialog(dietary, "dietary restrictions")
            }
            R.id.btnApplyFilter ->{
                activity?.onBackPressed()
            }

            R.id.btnCloseFilter ->{
                activity?.onBackPressed()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_fliter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnApplyFilter.setOnClickListener(this)
        relativeCuisine.setOnClickListener(this)
        relativeDietary.setOnClickListener(this)
        relativeEstablish.setOnClickListener(this)
        relativeHeaderFilter.btnCloseFilter.setOnClickListener(this)
        relativeHeaderFilter.txtClear.setOnClickListener(this)
    }


    @SuppressLint("SetTextI18n")
    private fun dialog(filterList: ArrayList<String>, title: String) {
         dialog = Dialog(activity!!, R.style.Dialog)
        dialog?.setContentView(R.layout.dialog_recycler)
        dialog?.show()
        dialog?.txtTitle?.text = "Choose $title"
        dialog?.btnClose?.setOnClickListener{
           FiltersAdapter.stringListener = this
            dialog?.dismiss()
        }
        setUpRecyclerView(dialog!!.dialog_recycler, filterList)
    }
    private fun setUpRecyclerView(recyclerView: RecyclerView, filterList: ArrayList<String>) {
        val redeemAdapter = FiltersAdapter(filterList,activity!!)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = redeemAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.requestFocus()
    }

}
