package com.innovantics.cityeats.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.innovantics.cityeats.R
import com.innovantics.cityeats.activity.DashboardActivity
import com.innovantics.cityeats.adapter.ResturantAdapter
import com.innovantics.cityeats.listener.GenericListener
import kotlinx.android.synthetic.main.resturants_fragment.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class ResturantsFragment : Fragment(), GenericListener<String> {

    private var param1: String? = null
    private var param2: String? = null

    override fun genericListener(genericModel: String) {
        (activity as DashboardActivity).restaurantDetail()
    }

    private lateinit var viewModel: ResturantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        ResturantAdapter.genericListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.resturants_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResturantsViewModel::class.java)
        // TODO: Use the ViewModel

        if (param1 =="my")
            linearBtn.visibility = View.GONE
        btnFilter.setOnClickListener{
            (activity as DashboardActivity).filters()
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val redeemAdapter = ResturantAdapter(activity!!)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = redeemAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.requestFocus()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String?) =
            ResturantsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
