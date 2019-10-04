package com.innovantics.cityeats.fragments


import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.places.PlaceBuffer
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.compat.GeoDataClient
import com.innovantics.cityeats.R
import com.innovantics.cityeats.adapter.PlaceArrayAdapter
import com.innovantics.cityeats.adapter.RecentViewedAdapter
import com.innovantics.cityeats.helper.MyLocation
import com.innovantics.cityeats.helper.toBounds
import com.innovantics.cityeats.listener.GenericListener
import kotlinx.android.synthetic.main.custom_recently_viewed.*
import kotlinx.android.synthetic.main.fragment_review_search.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ReviewSearchFragment : Fragment(), GenericListener<LatLng> {
    override fun genericListener(genericModel: LatLng) {
        currentPoint = genericModel
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var LOG_TAG: String= "REVIEW_SEARCH"
    private lateinit var geoDataClient: GeoDataClient
    private var currentPoint: LatLng?= null
    private val mGoogleApiClient: GoogleApiClient? = null
    private var mPlaceArrayAdapter: PlaceArrayAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val location = MyLocation()
        location.latLngGenericListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpRecyclerView()

        editSearch.onItemClickListener = mAutocompleteClickListener
        mPlaceArrayAdapter = PlaceArrayAdapter(
            activity, R.layout.custom_recently_viewed,
            currentPoint?.toBounds(10000.0), null
        )
        editSearch.setAdapter(mPlaceArrayAdapter)
    }

    private val mAutocompleteClickListener = OnItemClickListener { parent, view, position, id ->
        val item = mPlaceArrayAdapter?.getItem(position)
        val placeId = item?.placeId.toString()
        Log.i(LOG_TAG, "Selected: " + item?.description)
        val placeResult = Places.GeoDataApi
            .getPlaceById(mGoogleApiClient!!, placeId)
        placeResult.setResultCallback(mUpdatePlaceDetailsCallback)
        Log.i(LOG_TAG, "Fetching details for ID: " + item?.placeId)
    }

    private val mUpdatePlaceDetailsCallback = object : ResultCallback<PlaceBuffer> {
        override fun onResult(places: PlaceBuffer) {
            if (!places.status.isSuccess) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " + places.status.toString())
                return
            }
            // Selecting the first object buffer.
            val place = places.get(0)
            val attributions = places.attributions

            txtName.text = Html.fromHtml("${place.name} + ")
            txtStreet.text = Html.fromHtml("${place.address} + ")
         /*   mIdTextView.setText(Html.fromHtml(place.getId() + ""))
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""))
            mWebTextView.setText(place.getWebsiteUri() + "")
            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions!!.toString()))
            }*/
        }
    }
    private fun setUpRecyclerView() {
        val redeemAdapter = RecentViewedAdapter(activity!!)
        recyclerRecent.layoutManager = LinearLayoutManager(activity)
        recyclerRecent.adapter = redeemAdapter
        recyclerRecent.setHasFixedSize(true)
        recyclerRecent.requestFocus()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
