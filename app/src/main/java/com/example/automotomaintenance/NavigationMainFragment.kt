package com.example.automotomaintenance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.automotomaintenance.databinding.FragmentNavigationMainBinding
import com.example.automotomaintenance.extensions.setupWithNavController


class NavigationMainFragment : Fragment(R.layout.fragment_navigation_main) {

    private lateinit var binding: FragmentNavigationMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(
            R.navigation.add_vehicle_nav_graph,
            R.navigation.list_nav_graph,
        )
        binding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Nav", "fragment navigation")

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
//        NavigationUI.setupWithNavController(binding.bottomNavigation, findNavController(R.id.nav_host_container))
    }

    fun getSupportActionBar () {
        binding.bottomNavigation.height
    }

}



