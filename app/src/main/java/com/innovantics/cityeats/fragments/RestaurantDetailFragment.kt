package com.innovantics.cityeats.fragments


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import com.innovantics.cityeats.R
import com.innovantics.cityeats.activity.DashboardActivity
import com.innovantics.cityeats.adapter.PhotoAdapter
import com.innovantics.cityeats.adapter.ReviewAdapter
import com.innovantics.cityeats.adapter.TimesAdapter
import com.innovantics.cityeats.viewpager.ImageModel
import com.innovantics.cityeats.viewpager.SlidingImage_Adapter
import kotlinx.android.synthetic.main.dialog_viewpager.*
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RestaurantDetailFragment : Fragment(), View.OnClickListener {

    private var currentPage = 0
    private var NUM_PAGES = 0

   private var layouts: IntArray? = null
    private var imageModelArrayList: ArrayList<ImageModel>? = null

    private val myImageList = intArrayOf(R.mipmap.food,R.mipmap.resturant, R.mipmap.img, R.mipmap.food, R.mipmap.food )


    override fun onClick(p0: View?) {
        when(p0?.id){
         R.id.btnOpen ->{
             if (toggle ==1){
                 linearOpens.visibility = View.GONE
                 toggle = 0
             }
             else{
                 linearOpens.visibility = View.VISIBLE
                 toggle = 1
             }
         }
            R.id.txtShow ->{
                viewPager()
            }
            R.id.txtShowReviews ->{
                createFragment(ReviewFragment())
            }
            R.id.txtWriteReview ->{
                (activity as DashboardActivity).writeReview()
            }
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var toggle: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        btnOpen.setOnClickListener(this)
        txtShowReviews.setOnClickListener(this)
        txtShow.setOnClickListener(this)
        txtWriteReview.setOnClickListener(this)

        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()



        setUpRecyclerView()
    }


    private fun setUpRecyclerView() {
        val openTimeAdapter = TimesAdapter(activity!!)
        recyclerOpen.layoutManager = LinearLayoutManager(activity)
        recyclerOpen.adapter = openTimeAdapter
        recyclerOpen.setHasFixedSize(true)
        recyclerOpen.requestFocus()

        val photoAdapter = PhotoAdapter(activity!!)
        recyclerPhoto.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, true)
        recyclerPhoto.adapter = photoAdapter
        recyclerPhoto.setHasFixedSize(true)
        recyclerPhoto.requestFocus()

        val reviewAdapter = ReviewAdapter(activity!!, 0)
        recyclerReview.layoutManager = LinearLayoutManager(activity)
        recyclerReview.adapter = reviewAdapter
        recyclerReview.setHasFixedSize(true)
        recyclerReview.requestFocus()
    }

    private fun createFragment(fragmentClass: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentClass)
            .addToBackStack(null)
            .commit()
    }
    private fun populateList(): ArrayList<ImageModel> {

        val list = ArrayList<ImageModel>()

        for (i in 0..4) {
            val imageModel = ImageModel()
            imageModel.setImage_drawables(myImageList[i])
            list.add(imageModel)
        }

        return list
    }
    private fun viewPager(){
        val dialog = Dialog(activity!!, R.style.Dialog_Half)
        dialog.setContentView(R.layout.dialog_viewpager)
        dialog.viewPager.adapter = SlidingImage_Adapter(activity!!, imageModelArrayList!!)
       dialog.indicator.setViewPager(dialog.viewPager)
        dialog.show()
        val density = resources.displayMetrics.density

        //Set circle indicator radius
        dialog.indicator.radius = 5 * density

        NUM_PAGES = imageModelArrayList!!.size
        // Auto start of viewpager
        val handler = Handler()
        val update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            dialog.viewPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        dialog.indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    inner class MyViewPagerAdapter : PagerAdapter() {

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        @SuppressLint("SetTextI18n")
        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val layoutInflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(layouts!![position], container, false)

            // }

            container.addView(view)
            return view
        }


        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }
}
