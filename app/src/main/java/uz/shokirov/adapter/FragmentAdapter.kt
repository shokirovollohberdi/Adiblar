package uz.shokirov.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.shokirov.adiblarhaqida.HomeFragment
import uz.shokirov.adiblarhaqida.LifeGrandsFragment
import uz.shokirov.adiblarhaqida.SavedFragment
import uz.shokirov.adiblarhaqida.SettingsFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return LifeGrandsFragment()
            }
            1 -> {
                return SavedFragment()
            }
            2 -> {
                return SettingsFragment()
            }
            else -> LifeGrandsFragment()
        }
    }
}