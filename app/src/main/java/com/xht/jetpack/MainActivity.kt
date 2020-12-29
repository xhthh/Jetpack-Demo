package com.xht.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xht.jetpack.databinding.ui.DataBindingActivity
import com.xht.jetpack.kotlin.KotlinTestActivity
import com.xht.jetpack.lifecycle.LocationActivity
import com.xht.jetpack.livedata.LiveDataActivity
import com.xht.jetpack.navigation.GuideActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * JetPack
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnKotlin.setOnClickListener {
            skip2Activity(KotlinTestActivity::class.java)
        }

        //todo as 4.1 新建kotlin项目不能直接使用xml中的控件，build.gradle 中没有引入 kotlin-android-extensions
        //test Lifecycle
        btnLifecycle.setOnClickListener {
            skip2Activity(LocationActivity::class.java)
        }

        btnNavigation.setOnClickListener {
            skip2Activity(GuideActivity::class.java)
        }

        btnDataBinding.setOnClickListener {
            skip2Activity(DataBindingActivity::class.java)
        }

        btnLiveData.setOnClickListener {
            skip2Activity(LiveDataActivity::class.java)
        }
    }

    private fun skip2Activity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }
}