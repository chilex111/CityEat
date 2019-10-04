package com.innovantics.cityeats.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.innovantics.cityeats.fragments.ResturantsFragment

class MyRestaurantAdapter(fm: FragmentManager, private val total: Int): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return total
    }

    override fun getItem(position: Int): Fragment? {
        when(position){
            0->{

                return ResturantsFragment.newInstance("my",null)
            }
            1 ->{
                return ResturantsFragment.newInstance("my",null)
            }
            2 ->{
                return ResturantsFragment.newInstance("my",null)
            }
            else -> return null
        }

    }
}