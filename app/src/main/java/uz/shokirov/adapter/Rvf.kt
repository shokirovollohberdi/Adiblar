package uz.shokirov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.shokirov.adiblarhaqida.R
import uz.shokirov.adiblarhaqida.databinding.ItemRvBinding
import uz.shokirov.models.Adib

class RvAdapter(var context: Context, var list: ArrayList<Adib>) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(adib: Adib, position: Int) {
            itemRv.itemLife.text = "(${adib.birthYear}-${adib.deadYear})"
            itemRv.itemAdibName.text = adib.name
            Picasso.get().load(adib.imageUrl).into(itemRv.itemImage)
            itemRv.root.setOnClickListener {
                itemRv.root.findNavController().navigate(R.id.infoFragment, bundleOf("key" to adib))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }


    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}