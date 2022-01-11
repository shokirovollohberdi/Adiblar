package uz.shokirov.adiblarhaqida

import android.annotation.SuppressLint
import android.content.AbstractThreadedSyncAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import uz.shokirov.adapter.RvAdapter
import uz.shokirov.adiblarhaqida.databinding.FragmentSearchBinding
import uz.shokirov.models.Adib
import uz.shokirov.utils.SearchList
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var rvAdapter: RvAdapter
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.searchAdib.setQuery("",true);
        binding.searchAdib.setFocusable(true);
        binding.searchAdib.setIconified(false);
        binding.searchAdib.requestFocusFromTouch();

        rvAdapter = RvAdapter(context!!,SearchList.list)
        binding.rvSearch.adapter = rvAdapter

        binding.searchAdib.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var list = ArrayList<Adib>()
                for (adib in SearchList.list) {
                 for (i in 0 until adib.name?.length!!){
                     if (adib.name?.subSequence(0,i).toString().equals(p0, ignoreCase = true)){
                         list.add(adib)
                     }
                 }
                }

                rvAdapter = RvAdapter(context!!,list)
                binding.rvSearch.adapter = rvAdapter
                return true
            }
        })

        return binding.root
    }

}