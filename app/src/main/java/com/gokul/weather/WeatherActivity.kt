package com.gokul.weather

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.gokul.weather.GPS.GpsTracker
import com.gokul.weather.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*
import java.lang.Math.abs
import androidx.viewpager2.widget.ViewPager2.PageTransformer as PageTransformer1


class WeatherActivity : AppCompatActivity() {
    private  var viewAdapter = WeatherAdapter()
    private lateinit var gpsTracker: GpsTracker


    var weatherViewModel: WeatherViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        checkPremission()
        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        weatherViewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)

        setData()
        listener()

    }

    private fun listener() {
        weatherViewModel!!.observeWeatherInfo().observe(this, Observer {
            setVisibility(0,1,0)

            if (it != null) {
                vpWeather.adapter = viewAdapter
                designViewPager()
                viewAdapter.setItem(it.dataseries)

                setVisibility(1,0,0)

            }
            else{
                Log.e("Data"," Data is not set")
                setVisibility(0,0,2)
            }
        })
    }

    private fun setData() {
        getLocation()

    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED)) {
//                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        Log.d("Permission", "Yes ")
                    }
                } else {
//                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    Log.d("Permission", "No ")
                    clError.visibility = View.VISIBLE
                    pbLoading.visibility = View.GONE
                    vpWeather.visibility = View.GONE
                }
                return
            }
        }
    }

    fun getLocation() {
        gpsTracker = GpsTracker(this)

        if(gpsTracker.canGetLocation()){
            val latitude = gpsTracker.getLatitude()
            val longittude = gpsTracker.getLatitude()
            weatherViewModel!!.requestWeatherInfo(longittude.toString(),latitude.toString())
//            Toast.makeText(this, "$latitude & $longittude", Toast.LENGTH_LONG).show()
            Log.d("Location info ", "$latitude & $longittude")
        }
        else{
            gpsTracker.showSettingsAlert()
            setVisibility(0,0,1)
        }

    }
    private fun setVisibility(vp: Int, pb : Int, error:Int){
        vpWeather.visibility = if(vp == 1) View.VISIBLE else View.GONE
        pbLoading.visibility = if(pb == 1) View.VISIBLE else View.GONE
        clError.visibility = if(error > 0) View.VISIBLE else View.GONE
        if(error==2){
            tvErrorMessge.text = "There are some internet issue"
            ivErrorImage.setImageResource(R.drawable.ic_network)
        }
    }

    private fun checkPremission() {
        try{
            if(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION ), 101)
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION ), 101)
            }
        }
        catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }

    private fun designViewPager(){
        vpWeather.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = PageTransformer1{ page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
             page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        vpWeather.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        vpWeather.addItemDecoration(itemDecoration)
    }

}