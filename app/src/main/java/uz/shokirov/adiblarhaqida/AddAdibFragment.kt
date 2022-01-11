package uz.shokirov.adiblarhaqida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_add_adib.*
import uz.shokirov.adapter.TypeSpinnerAdapter
import uz.shokirov.adiblarhaqida.databinding.FragmentAddAdibBinding
import uz.shokirov.models.Adib


class AddAdibFragment : Fragment() {
    lateinit var binding: FragmentAddAdibBinding
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    lateinit var typeSpinnerAdapter: TypeSpinnerAdapter
    lateinit var list: ArrayList<String>
    var imageUrl = " "
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddAdibBinding.inflate(layoutInflater)
        list = ArrayList()
        list.add("Tur")
        list.add("Mumtoz")
        list.add("O'zbek")
        list.add("Jahon")
        typeSpinnerAdapter = TypeSpinnerAdapter(list)
        binding.spinner.adapter = typeSpinnerAdapter

        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("images")

        binding.cardAdibImage.setOnClickListener {
            getImageContent.launch("image/*")
            binding.progress.visibility = View.VISIBLE
        }

        binding.cardSave.setOnClickListener {
            var name = binding.edtAdib.text.toString().trim()
            var bith = binding.edtBirth.text.toString().trim()
            var dead = binding.edtDead.text.toString().trim()
            var type = binding.spinner.selectedItemPosition
            var description = binding.edtInfo.text.toString().trim()
            if (imageUrl.isNotEmpty() && name.isNotEmpty() && bith.isNotEmpty() && dead.isNotEmpty() && type != 0 && description.isNotEmpty()) {
                var adib = Adib(name, bith, dead, type, description, imageUrl)
                firebaseFirestore.collection("Adiblar")
                        .add(adib)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                        }
            } else {
                Toast.makeText(context, "Ma'lumot to'liq emas", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private var getImageContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                val m = System.currentTimeMillis()
                val uploadTask = reference.child(m.toString()).putFile(uri)
                uploadTask.addOnSuccessListener {
                    if (it.task.isSuccessful) {
                        val downloadUrl = it.metadata?.reference?.downloadUrl
                        downloadUrl?.addOnSuccessListener { imageUri ->
                            imageUrl = imageUri.toString()
                            binding.progress.visibility = View.GONE
                            binding.adibImage.setImageURI(uri)
                        }
                    }
                }.addOnFailureListener {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
}