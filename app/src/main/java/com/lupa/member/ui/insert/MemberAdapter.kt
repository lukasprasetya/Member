package com.lupa.member.ui.insert

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lupa.member.database.Group
import com.lupa.member.database.Member
import com.lupa.member.databinding.ItemMemberRowBinding
import com.lupa.member.helper.MemberDiffCallback

class MemberAdapter(private val listener: OnCLickListenerMember) :
    RecyclerView.Adapter<MemberAdapter.AddMemberViewHolder>() {

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
        return AddMemberViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: AddMemberViewHolder, position: Int) {
        holder.bind(listMember[position])
    }

    override fun getItemCount(): Int {
        return listMember.size
    }

    class AddMemberViewHolder(
        private val binding: ItemMemberRowBinding,
        private val listener: OnCLickListenerMember
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(member: Member) {
            binding.tvNameMember.text = member.memberName
            binding.ivDeleteMember.setOnClickListener {
                listener.onDeleteClickListenerMember(member)
            }
        }
    }

    interface OnCLickListenerMember {
        fun onDeleteClickListenerMember(member: Member)
    }
}