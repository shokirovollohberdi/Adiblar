package uz.shokirov.adiblarhaqida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_tab.view.*
import uz.shokirov.adapter.FragmentAdapter
import uz.shokirov.adiblarhaqida.databinding.FragmentHomeBinding
import uz.shokirov.adiblarhaqida.databinding.ItemTabBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var fragmentAdapter: FragmentAdapter
    var tabSelected = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabSelected = tab?.position!!
                when (tab?.position) {
                    0 -> {
                        tab?.customView?.image_tab_image?.setImageResource(R.drawable.ic_home_select)
                    }
                    1 -> {
                        tab?.customView?.image_tab_image?.setImageResource(R.drawable.ic_saved_select)
                    }
                    2 -> {
                        tab?.customView?.image_tab_image?.setImageResource(R.drawable.ic_setting_select)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        tab?.customView?.image_tab_image?.setImageResource(R.drawable.ic_home_unselect)
                    }
                    1 -> {
                        tab?.customView?.image_tab_image?.setImageResource(R.drawable.ic_saved_unselect)
                    }
                    2 -> {
                        tab?.customView?.image_tab_image?.setImageResource(R.drawable.ic_setting_unselect)
                    }
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        fragmentAdapter = FragmentAdapter(childFragmentManager, lifecycle)
        binding.vp2.adapter = fragmentAdapter
        binding.vp2.isUserInputEnabled = false


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.vp2) { tab, position ->
            val inflate = LayoutInflater.from(context).inflate(R.layout.item_tab, null, false)
            tab.customView = inflate
            when (position) {
                0 -> {
                    inflate.image_tab_image.setImageResource(R.drawable.ic_home_unselect)
                }
                1 -> {
                    inflate.image_tab_image.setImageResource(R.drawable.ic_saved_unselect)
                }
                2 -> {
                    inflate.image_tab_image.setImageResource(R.drawable.ic_setting_unselect)
                }

            }
            if (tabSelected == position) {
                inflate.image_tab_image.setImageResource(R.drawable.ic_home_select)
            }
        }.attach()
    }
}