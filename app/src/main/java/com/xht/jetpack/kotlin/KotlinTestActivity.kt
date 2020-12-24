package com.xht.jetpack.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xht.jetpack.R
import com.xht.jetpack.kotlin.bean.Bean
import kotlinx.android.synthetic.main.activity_kotlin_test.*

class KotlinTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)
        btnNullTest.setOnClickListener {
            val bean = Bean()
            bean.desc = null

            //java.lang.IllegalStateException: bean.desc must not be null
            //不try catch 的话 回到首页，异常一闪而过
            try {
                setMessage(bean.desc)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            setMessage(bean.desc)

        }
    }

    var data: String? = null

    fun setMessage(data: String) {
        this.data = data
    }

}