package com.xht.jetpack.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.xht.jetpack.R
import com.xht.jetpack.livedata.data.NameViewModel
import kotlinx.android.synthetic.main.activity_live_data.*

/**
 * ViewModel 负责在系统配置更改时保存和恢复 LiveData，而 LiveData 则负责在生命周期状态发生改变的时候，对数据的变化进行监听。
 */
class LiveDataActivity : AppCompatActivity() {

//    private val model: NameViewModel by viewModels()
    private var model: NameViewModel? = null

    private var numberLiveData: MutableLiveData<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        userLiveDataWithViewModel()

        //userLiveDataAlone()
    }

    /**
     * LiveData + ViewModel
     */
    private fun userLiveDataWithViewModel() {
        //viewModel 的一般获取方式
        /*
            ViewModel 是一种用来存储和管理UI相关数据的类。
            在系统配置发生改变时，比如横竖屏切换，Activity会销毁重建，但是viewModel的实例还之前创建的那个
         */
        model = ViewModelProvider(this).get(NameViewModel::class.java)

        val nameObserver = Observer<String> { newName ->
            nameTextView.text = newName
        }

        model?.currentName?.observe(this, nameObserver)

        btnUpdate.setOnClickListener {
            val anotherName = "浅仓 南"
            model?.currentName?.setValue(anotherName)
        }
    }


    /**
     * 单独使用LiveData
     */
    private fun userLiveDataAlone() {
        numberLiveData = MutableLiveData()
//        numberLiveData!!.observe(this, Observer<Int>() {
//            nameTextView.text = it.toString()
//            Log.i("xht", "onChanged()---it = $it")
//        })

        //转换LiveData，比如 Int--->String，和 RxJava 中的 map 差不多
        Transformations.map(numberLiveData!!) {
            it.toString()
        }.observe(this, Observer<String> {
            nameTextView.text = it
            Log.i("xht", "转换---onChanged()---it = $it")
        })

        btnUpdate.setOnClickListener {
            object : Thread() {
                override fun run() {
                    super.run()
                    var number = 0
                    while (number < 10) {
                        try {
                            sleep(1000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        number++
                        numberLiveData!!.postValue(number)
                    }
                }
            }.start()
        }
    }
}