package com.innovantics.cityeats.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.innovantics.cityeats.R
import com.innovantics.cityeats.fragments.*
import com.innovantics.cityeats.helper.setTextViewDrawableColor
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_dashboard.*


class DashboardActivity : AppCompatActivity(), View.OnClickListener {
    var sheetBehavior: BottomSheetBehavior<*>? = null
    companion object{
        var btnPressed: BackPressed?= null
    }
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnSearch ->{


            } R.id.btnAddRestaurant ->{
            status =1
            createFragment(AddRestaurantFragment())
            header(2, null)
        }
            R.id.txtProfile ->{
                txt(R.color.dark, R.color.dark, R.color.colorAccent)
                createFragment(ProfileFragment())
                header(1, getString(R.string.my_profile))

            }
            R.id.btnAddReview ->{
                status = 1
               // createFragment(AddReviewFragment())
                createFragment(ReviewSearchFragment())
               header(2, null)
            }
            R.id.btnAdd ->{
                txt( R.color.dark, R.color.colorAccent, R.color.dark)

                if (sheetBehavior?.state !=  BottomSheetBehavior.STATE_EXPANDED){
                    sheetBehavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
                }
                else{
                    sheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
                }
            }
            R.id.txtRestaurants ->{
                txt( R.color.colorAccent, R.color.dark, R.color.dark)
                dashboard()
            }
        }
    }

    var status: Int = 0

    fun txt(first:Int , second:Int, third: Int){
        setTextViewDrawableColor(txtRestaurants,first)
        setTextViewDrawableColor(txtReview, second)
        setTextViewDrawableColor(txtProfile, third)
        txtRestaurants.setTextColor(first)
        txtRestaurants.setTextColor(second)
        txtProfile.setTextColor(third)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dashboard()

        btnSearch.setOnSearchClickListener {txt.visibility = View.GONE  }
        txt.visibility = View.VISIBLE

        btnAdd.setOnClickListener(this)
        btnAddRestaurant.setOnClickListener(this)
        txtProfile.setOnClickListener(this)
        txtReview.setOnClickListener(this)
        btnAddReview.setOnClickListener(this)
        txtRestaurants.setOnClickListener(this)

        btnSearch.setOnCloseListener {
            txt.visibility = View.VISIBLE
            false
        }

        sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        sheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(p0: View, p1: Float) {
            }

            override fun onStateChanged(p0: View, p1: Int) {
                when (p1) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        sheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)

                    }
                    BottomSheetBehavior.STATE_EXPANDED ->{}
                    // btBottomSheet.text = "Close Bottom Sheet"
                    BottomSheetBehavior.STATE_COLLAPSED ->{}
                    //  btBottomSheet.text = "Expand Bottom Sheet"
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        sheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING->{}
                }
            }

        })
        btnBack.setOnClickListener {
            btnPressed?.onBackPress()
            if (status == 1){
                dashboard()
            }

        }

    }
    fun header(head: Int, string: String?) {
        when(head){
            1->{
                linearIcon.visibility = View.GONE
                txt.text = string
                parentHeader.visibility =View.VISIBLE
            }
            2 ->{
                linearIcon.visibility = View.GONE
                btnBack.visibility = View.VISIBLE
                parentHeader.visibility =View.VISIBLE
                sheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            3->{
                linearIcon.visibility = View.VISIBLE
                btnBack.visibility = View.GONE
                parentHeader.visibility =View.VISIBLE
            }
            4->{
                parentHeader.visibility =View.GONE
            }
        }

    }

    fun dashboard(){
        txt( R.color.colorAccent, R.color.dark, R.color.dark)

        createFragment(ResturantsFragment())
        header(3, null)

    }
    fun writeReview(){
        createFragment1(AddReviewFragment())
        header(4, null)
    }
    fun editProfile(){
        createFragment(ProfileDetailFragment())
        header(4, null)
    }
    fun myRestaurants(){
        createFragment(MyRestaurantFragment())
       header(2, null)
    }
    fun about(){
        createFragment(AboutFragment())
        header(2, null)
    }

    fun filters(){
        createFragment1(FilterFragment())
    }
    fun restaurantDetail(){
        createFragment(RestaurantDetailFragment())
    }
    private fun createFragment1(fragmentClass: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container1, fragmentClass)
            .addToBackStack(null)
            .commit()
    }
    /*   private fun createFragment2(fragmentClass: Fragment) {
           supportFragmentManager.beginTransaction()
               .replace(R.id.container2, fragmentClass)
               .addToBackStack(null)
               .commit()
       }*/
    private fun createFragment(fragmentClass: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentClass)
            .addToBackStack(null)
            .commit()
    }
    private fun showBottom(fragmentClass: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container3, fragmentClass)
            .addToBackStack(null)
            .commit()
    }

    interface BackPressed{
        fun onBackPress()
    }
}
