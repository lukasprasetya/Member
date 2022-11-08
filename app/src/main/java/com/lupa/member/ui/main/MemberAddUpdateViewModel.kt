package com.lupa.member.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lupa.member.database.Member
import com.lupa.member.repository.MemberRepository

class MemberAddUpdateViewModel(application: Application) : ViewModel() {

    private val mMemberRepository: MemberRepository = MemberRepository(application)

    fun getAllGroupMemberList(): LiveData<List<Member>> = mMemberRepository.getAllMembers()

    fun insert(member: Member) {
        mMemberRepository.insert(member)
    }

    fun delete(member: Member) {
        mMemberRepository.delete(member)
    }
}