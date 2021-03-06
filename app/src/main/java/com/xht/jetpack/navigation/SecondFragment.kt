package com.xht.jetpack.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xht.jetpack.R
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //title = it.getString("title")

            title = SecondFragmentArgs.fromBundle(it).title
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSecondTitle.text = title

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnToThird.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_to_main)
        }
    }
}