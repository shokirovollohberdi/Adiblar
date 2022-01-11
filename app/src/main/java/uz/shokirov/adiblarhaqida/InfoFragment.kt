package uz.shokirov.adiblarhaqida

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.shokirov.adiblarhaqida.databinding.FragmentInfoBinding
import uz.shokirov.models.Adib

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)

        var adib = arguments?.getSerializable("key") as Adib

        binding.tvAdib.text = "${adib.name}"

        binding.tvDescription.text = adib.description
        Picasso.get().load(adib.imageUrl).into(binding.adibImage)
        binding.cardBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            @SuppressLint("ResourceAsColor")
            override fun onQueryTextChange(p0: String?): Boolean {
                binding.tvDescription.setTextToHighlight(p0)
                binding.tvDescription.highlightColor = resources.getColor(R.color.barColor)
                binding.tvDescription.setTextHighlightColor("#00B238")
                binding.tvDescription.setCaseInsensitive(true)
                binding.tvDescription.highlight()

                return true
            }
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                InfoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}