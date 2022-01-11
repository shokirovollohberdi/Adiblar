package uz.shokirov.adiblarhaqida

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import uz.shokirov.adapter.RvAdapter
import uz.shokirov.adiblarhaqida.databinding.FragmentJahonBinding
import uz.shokirov.models.Adib

class JahonFragment : Fragment() {
    lateinit var binding: FragmentJahonBinding
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var rvAdapter: RvAdapter
    var list = ArrayList<Adib>()
    var jahon = ArrayList<Adib>()
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJahonBinding.inflate(layoutInflater)

        firebaseFirestore = FirebaseFirestore.getInstance()

        firebaseFirestore.collection("Adiblar").get().addOnCompleteListener {
            if (it.isSuccessful) {
                list = ArrayList()
                val result = it.result
                result?.forEach { queryDocumentSnapshot ->
                    val user = queryDocumentSnapshot.toObject(Adib::class.java)
                    list.add(user)
                }
                jahon = ArrayList()
                for (adib in 0 until list.size) {
                    if (list[adib].type == 3) {
                        jahon.add(list[adib])
                    }
                }
                rvAdapter = RvAdapter(context!!,jahon)
                binding.rv.adapter = rvAdapter
            }


        }
        return binding.root
    }

}