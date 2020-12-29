package com.xht.jetpack.databinding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.xht.jetpack.R
import com.xht.jetpack.databinding.ActivityViewmodelProfileBinding
import com.xht.jetpack.databinding.data.ProfileLiveDataViewModel

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ProfileLiveDataViewModel::class.java)

        val binding: ActivityViewmodelProfileBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_viewmodel_profile)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

}