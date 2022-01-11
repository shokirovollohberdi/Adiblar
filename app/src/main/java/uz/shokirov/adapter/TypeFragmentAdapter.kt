package uz.shokirov.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.shokirov.adiblarhaqida.*

class TypeFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return MumtozFragment()
            }
            1 -> {
                return UzbekFragment()
            }
            2 -> {
                return JahonFragment()
            }
            else -> MumtozFragment()
        }
    }
}