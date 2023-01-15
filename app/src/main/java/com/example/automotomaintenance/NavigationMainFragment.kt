package com.example.automotomaintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.automotomaintenance.databinding.FragmentNavigationMainBinding

class NavigationMainFragment : Fragment() {

    private lateinit var binding: FragmentNavigationMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        NavigationUI.setupWithNavController(
//            binding.bottomNavigation,
//            R.id.containerNavigationMain
//        )

    }
}



