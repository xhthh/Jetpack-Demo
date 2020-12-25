package com.xht.jetpack.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xht.jetpack.R
import kotlinx.android.synthetic.main.fragment_third.*


class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //返回首页FirstFragment，<action/> 中使用 popUpTo
        btnBackToFirst.setOnClickListener {
            findNavController().navigate(R.id.thirdFragment_exit)
        }

        btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_to_main)
        }
    }
}