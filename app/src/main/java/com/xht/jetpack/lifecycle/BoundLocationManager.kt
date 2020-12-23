package com.xht.jetpack.lifecycle

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class BoundLocationManager {

    /**
     * 静态方法
     */
    companion object {
        fun bindLocationListenerIn(
            lifecycleOwner: LifecycleOwner, listener: LocationListener, context: Context
        ) {
            BoundLocationListener(lifecycleOwner, listener, context)
        }
    }


    /**
     * 构造函数中的参数 lifecycleOwner 只能在 init 中使用？
     * 如 mListner 要在类内部使用，需要声明为 private val/var
     *
     * ?.   !!.
     *
     * 强制转换--->as xxx
     */
    @SuppressLint("MissingPermission")
    internal class BoundLocationListener(
        lifecycleOwner: LifecycleOwner,
        private val mListener: LocationListener,
        private val mContext: Context
    ) : LifecycleObserver {
        private var mLocationManager: LocationManager? = null

        //todo init
        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            //as xxx === java 强转
            mLocationManager =
                mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            mLocationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0F,
                mListener
            )
            Log.d("BoundLocationMgr", "Listener added")

            // Force an update with the last location, if available.
            var lastLocation = mLocationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                mListener.onLocationChanged(lastLocation)
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            if (mLocationManager == null) {
                return
            }
            mLocationManager!!.removeUpdates(mListener)
            mLocationManager = null
            Log.d("BoundLocationMgr", "Listener removed")
        }
    }

}