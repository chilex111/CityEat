package com.innovantics.cityeats.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout

import com.innovantics.cityeats.R
import com.innovantics.cityeats.activity.DashboardActivity
import com.innovantics.cityeats.viewpager.MyRestaurantAdapter
import kotlinx.android.synthetic.main.custom_header.view.*
import kotlinx.android.synthetic.main.fragment_my_restaurant.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyRestaurantFragment : Fragment(), DashboardActivity.BackPressed{
    override fun onBackPress() {
        activity?.onBackPressed()
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
        return inflater.inflate(R.layout.fragment_my_restaurant, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

      /*  relativeMy.btnCloseFilter.setImageResource(R.drawable.ic_arrow_back)
        relativeMy.btnCloseFilter.setOnClickListener {
            activity?.onBackPressed()
        }
        relativeMy.txtMsg.text = "My restaurants"
        relativeMy.txtClear.visibility = View.GONE*/
        tabLayout.addTab(tabLayout.newTab().setText("ADDED"))
        tabLayout.addTab(tabLayout.newTab().setText("REVIEWED"))
        tabLayout.addTab(tabLayout.newTab().setText("FAVOURITES"))

        val adapter = MyRestaurantAdapter(childFragmentManager, tabLayout.tabCount)
        viewPager.adapter= adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager.currentItem = p0?.position!!
            }

        })
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyRestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
