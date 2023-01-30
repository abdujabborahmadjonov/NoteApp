package dev.abdujabbor.homenazorat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.abdujabbor.homenazorat.databinding.MyRvItemBinding
import dev.abdujabbor.homenazorat.models.MyData

class RvAdapter(var list: ArrayList<MyData>, var rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.VH>() {
    inner class VH(var itemViewBinding: MyRvItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(list: MyData, position: Int) {
            itemViewBinding.text.text = list.note
            itemViewBinding.date.text = list.date
            itemViewBinding.chekbox.isChecked = false

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(MyRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface RvClick {
    fun click(moview: MyData, position: Int)
}