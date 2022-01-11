package uz.shokirov.adiblarhaqida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.shokirov.adiblarhaqida.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)


        binding.cardAddAdib.setOnClickListener {
            findNavController().navigate(R.id.addAdibFragment)
            try {
            } catch (e: Exception) {
                Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }


            return binding.root
        }

    }