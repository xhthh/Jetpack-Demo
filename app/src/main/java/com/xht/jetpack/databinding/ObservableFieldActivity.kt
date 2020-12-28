package com.xht.jetpack.databinding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import com.xht.jetpack.R
import com.xht.jetpack.databinding.data.ObservableFieldProfile

/*
    D:\WorkSpace\Xht\JetpackDemo\app\build\generated\source\kapt\debug\com\xht\jetpack\DataBinderMapperImpl.java:10: 错误: 找不到符号
    import com.xht.jetpack.databinding.ObservableFieldProfileBindingImpl;
                                  ^
    符号:   类 ObservableFieldProfileBindingImpl
    位置: 程序包 com.xht.jetpack.databinding


    因为xml布局文件中写法有问题，会导致xxxBindingImpl生成失败
 */
class ObservableFieldActivity : AppCompatActivity() {

    private val observableFieldProfile = ObservableFieldProfile("南", "浅仓", ObservableInt(0))

    private var binding: ObservableFieldProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.observable_field_profile)
        binding?.user = observableFieldProfile

    }

    fun onLike(view: View) {
        observableFieldProfile.likes.set(observableFieldProfile.likes.get() + 1)

        if (observableFieldProfile.likes.get() > 4)
            binding?.imageView?.setImageResource(R.drawable.ic_whatshot_black_96dp)
        else binding?.imageView?.setImageResource(
            R.drawable.ic_person_black_96dp)
    }
}