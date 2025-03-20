package com.vedatakcan.switchnavigationsample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vedatakcan.switchnavigationsample.R
import com.vedatakcan.switchnavigationsample.databinding.FragmentHomeBinding
import com.vedatakcan.switchnavigationsample.viewmodel.AppViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: AppViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[AppViewModel::class.java]

        // ViewModel'daki LiveData'ları gözlemle
        observeViewModel()

        // Switch'lerin tıklama olaylarını ayarla
        setupSwitchListeners()


    }

    private fun observeViewModel() {
        viewModel.isEgoSwitchChecked.observe(viewLifecycleOwner) { isChecked ->
            binding.switchEgo.isChecked = isChecked
        }

        viewModel.isSettingsSwitchChecked.observe(viewLifecycleOwner) { isChecked ->
            binding.switchSettings.isChecked = isChecked
            // Settings switch'i açıldığında Bottom Navigation'a ekle
            if (isChecked) {
                addMenuItemToBottomNav(R.id.settingsFragment, "Settings", R.drawable.ic_settings)
            } else {
                removeMenuItemFromBottomNav(R.id.settingsFragment)
            }
        }

        viewModel.isHelpSwitchChecked.observe(viewLifecycleOwner) { isChecked ->
            binding.switchHelp.isChecked = isChecked
            if (isChecked) {
                addMenuItemToBottomNav(R.id.helpFragment, "Help", R.drawable.ic_help)
            } else {
                removeMenuItemFromBottomNav(R.id.helpFragment)
            }
        }


        viewModel.isNotificationsSwitchChecked.observe(viewLifecycleOwner) { isChecked ->
            binding.switchNotifications.isChecked = isChecked
            if (isChecked) {
                addMenuItemToBottomNav(R.id.notificationsFragment, "Notifications", R.drawable.ic_notifications)
            } else {
                removeMenuItemFromBottomNav(R.id.notificationsFragment)
            }
        }

        viewModel.isProfileSwitchChecked.observe(viewLifecycleOwner) { isChecked ->
            binding.switchProfile.isChecked = isChecked
            if (isChecked) {
                addMenuItemToBottomNav(R.id.profileFragment, "Profile", R.drawable.ic_profile)
            } else {
                removeMenuItemFromBottomNav(R.id.profileFragment)
            }
        }

        viewModel.isSearchSwitchChecked.observe(viewLifecycleOwner) { isChecked ->
            binding.switchSearch.isChecked = isChecked
            if (isChecked) {
                addMenuItemToBottomNav(R.id.searchFragment, "Search", R.drawable.ic_search)
            } else {
                removeMenuItemFromBottomNav(R.id.searchFragment)
            }
        }

        viewModel.isBottomNavVisible.observe(viewLifecycleOwner) { isVisible ->
            val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            bottomNavView.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    private fun setupSwitchListeners() {
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setEgoSwitchChecked(isChecked)
        }

        binding.switchSettings.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSettingsSwitchChecked(isChecked)
        }

        binding.switchHelp.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setHelpSwitchChecked(isChecked)
        }

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNotificationsSwitchChecked(isChecked)
        }

        binding.switchProfile.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setProfileSwitchChecked(isChecked)
        }

        binding.switchSearch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSearchSwitchChecked(isChecked)
        }
    }


    private fun addMenuItemToBottomNav(fragmentId: Int, title: String, iconResId: Int) {
        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        if (bottomNavView.menu.findItem(fragmentId) == null) {
            bottomNavView.menu.add(0, fragmentId, 0, title)
                .setIcon(iconResId) // Doğru ikonu ayarla
        }
    }

    private fun removeMenuItemFromBottomNav(fragmentId: Int) {
        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavView.menu.removeItem(fragmentId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
