package com.xht.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xht.jetpack.lifecycle.LocationActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * JetPack
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo as 4.1 新建kotlin项目不能直接使用xml中的控件，build.gradle 中没有引入 kotlin-android-extensions
        //test Lifecycle
        btnLifecycle.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }
    }
}