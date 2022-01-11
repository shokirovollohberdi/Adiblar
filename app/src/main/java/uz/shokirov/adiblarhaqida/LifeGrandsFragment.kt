package uz.shokirov.adiblarhaqida

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_tab.view.*
import kotlinx.android.synthetic.main.type_item_tab.view.*
import uz.shokirov.adapter.TypeFragmentAdapter
import uz.shokirov.adiblarhaqida.databinding.FragmentLifeGrandsBinding

class LifeGrandsFragment : Fragment() {
    lateinit var binding: FragmentLifeGrandsBinding
    lateinit var typeFragmentAdapter: TypeFragmentAdapter
    var tabSelected = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLifeGrandsBinding.inflate(layoutInflater)

        binding.searchImage.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabSelected = tab?.position!!
                when (tab?.position) {
                    0 -> {
                        tab?.customView?.tvTypeTab?.setTextColor(Color.WHITE)
                        tab?.customView?.cardRoot?.setBackgroundResource(R.drawable.tab_backgound)
                        tab?.customView?.tvTypeTab?.text = "Mumtoz"
                    }
                    1 -> {
                        tab?.customView?.tvTypeTab?.setTextColor(Color.WHITE)
                        tab?.customView?.cardRoot?.setBackgroundResource(R.drawable.tab_backgound)
                        tab?.customView?.tvTypeTab?.text = "Uzbek"
                    }
                    2 -> {
                        tab?.customView?.tvTypeTab?.setTextColor(Color.WHITE)
                        tab?.customView?.cardRoot?.setBackgroundResource(R.drawable.tab_backgound)
                        tab?.customView?.tvTypeTab?.text = "Jahon"
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        tab?.customView?.tvTypeTab?.setTextColor(Color.GRAY)
                        tab?.customView?.cardRoot?.setBackgroundResource(android.R.color.transparent)
                        tab?.customView?.tvTypeTab?.text = "Mumtoz"
                    }
                    1 -> {
                        tab?.customView?.tvTypeTab?.setTextColor(Color.GRAY)
                        tab?.customView?.cardRoot?.setBackgroundResource(android.R.color.transparent)
                        tab?.customView?.tvTypeTab?.text = "Uzbek"
                    }
                    2 -> {
                        tab?.customView?.tvTypeTab?.setTextColor(Color.GRAY)
                        tab?.customView?.cardRoot?.setBackgroundResource(android.R.color.transparent)
                        tab?.customView?.tvTypeTab?.text = "Jahon"

                    }
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        typeFragmentAdapter = TypeFragmentAdapter(childFragmentManager, lifecycle)
        binding.vp.adapter = typeFragmentAdapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.vp) { tab, position ->
            val inflate = LayoutInflater.from(context).inflate(R.layout.type_item_tab, null, false)
            tab.customView = inflate
            when (position) {
                0 -> {
                    inflate.tvTypeTab.setTextColor(Color.GRAY)
                    inflate.tvTypeTab.text = "Mumtoz"
                    inflate.cardRoot.setBackgroundResource(android.R.color.transparent)
                }
                1 -> {
                    inflate.tvTypeTab.setTextColor(Color.GRAY)
                    inflate.tvTypeTab.text = "Uzbek"
                    inflate.cardRoot.setBackgroundResource(android.R.color.transparent)
                }
                2 -> {
                    inflate.tvTypeTab.setTextColor(Color.GRAY)
                    inflate.tvTypeTab.text = "Jahon"
                    inflate.cardRoot.setBackgroundResource(android.R.color.transparent)
                }

            }
            if (tabSelected == position) {
                inflate.tvTypeTab.setTextColor(Color.WHITE)
            }
        }.attach()
    }


}