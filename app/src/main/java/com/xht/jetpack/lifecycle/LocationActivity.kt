package com.xht.jetpack.lifecycle

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.xht.jetpack.R
import kotlinx.android.synthetic.main.activity_location.*

/**
 * Lifecycle
 * 通过框架绑定 Activity 生命周期，在 onResume() 中将获取到的位置进行回调、设置
 *
 * 观察者模式
 *
 * 内部通过反射处理，在 addObserver() 中通过反射获取观察者用 OnLifecycleEvent 注解标注的方法
 * Activity 内部 add 了一个 ReportFragment，通过 Fragment 绑定 Activity 的生命周期，然后进行分发处理
 * 最终通过反射执行相应的被 OnLifecycleEvent 注解标注的方法
 *
 *
 * kotlin
 * 静态常量声明、静态方法声明 companion
 * 内部类的声明
 *
 */
class LocationActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }

    private val mGpsListener: LocationListener = MyLocationListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_LOCATION_PERMISSION_CODE
            )
        } else {
            bindLocationListener()
        }
    }

    private fun bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, mGpsListener, applicationContext)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            bindLocationListener()
        } else {
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }
    }


    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            tvLocation.text = location.latitude.toString() + ", " + location.longitude
        }

        override fun onProviderEnabled(provider: String) {
            super.onProviderEnabled(provider)
            //todo 内部类使用外部类Activity的引用，报错：Unresolved reference: @LocationActivity
            //将 MyLocationListener 声明为 inner class
            Toast.makeText(
                this@LocationActivity,
                "Provider enabled: $provider", Toast.LENGTH_SHORT
            ).show()
        }
    }
}