package com.lupa.member.ui.insert

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lupa.member.database.Member
import com.lupa.member.databinding.ItemListRowBinding
import com.lupa.member.databinding.ItemMemberRowBinding
import com.lupa.member.helper.MemberDiffCallback

class AddMemberAdapter : RecyclerView.Adapter<AddMemberAdapter.AddMemberViewHolder>() {

    private val listMember = ArrayList<Member>()

    fun setListMember(listMember: List<Member>) {
        val diffCallback = MemberDiffCallback(this.listMember, listMember)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listMember.clear()
        this.listMember.addAll(listMember)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddMemberViewHolder {
        val binding =
            ItemMemberRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddMemberViewHolder, position: Int) {
        holder.bind(listMember[position])
    }

    override fun getItemCount(): Int {
        return listMember.size
    }

    class AddMemberViewHolder(private val binding: ItemMemberRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(member: Member) {
            binding.tvNameMember.text = member.name
        }

    }
}