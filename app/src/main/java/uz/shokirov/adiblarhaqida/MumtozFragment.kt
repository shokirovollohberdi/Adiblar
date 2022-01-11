package uz.shokirov.adiblarhaqida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import uz.shokirov.adapter.RvAdapter
import uz.shokirov.adiblarhaqida.databinding.FragmentMumtozBinding
import uz.shokirov.models.Adib
import uz.shokirov.utils.SearchList

class MumtozFragment : Fragment() {
    lateinit var binding: FragmentMumtozBinding
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var rvAdapter: RvAdapter
    var list = ArrayList<Adib>()
    var mumtoz = ArrayList<Adib>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMumtozBinding.inflate(layoutInflater)
        firebaseFirestore = FirebaseFirestore.getInstance()

        firebaseFirestore.collection("Adiblar").get().addOnCompleteListener {
            if (it.isSuccessful) {
                list = ArrayList()
                val result = it.result
                SearchList.list.clear()
                result?.forEach { queryDocumentSnapshot ->
                    val user = queryDocumentSnapshot.toObject(Adib::class.java)
                    SearchList.list.add(user)
                    list.add(user)
                }
                mumtoz = ArrayList()
                for (adib in 0 until list.size) {
                    if (list[adib].type == 1) {
                        mumtoz.add(list[adib])
                    }
                }
                rvAdapter = RvAdapter(requireContext(),mumtoz)
                binding.rv.adapter = rvAdapter
            }


        }
        return binding.root
    }
}