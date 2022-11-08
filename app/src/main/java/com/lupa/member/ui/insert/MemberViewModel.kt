package com.lupa.member.ui.insert

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lupa.member.database.Member
import com.lupa.member.repository.Repository

class MemberViewModel(application: Application) : ViewModel() {

    private val mMemberRepository: Repository = Repository(application)

    fun getAllListMember(groupId : Int): LiveData<List<Member>> = mMemberRepository.getAllMembers(groupId)
    fun getAllCountMember(groupId : Int): LiveData<Int> = mMemberRepository.getAllCounts(groupId)

    fun insertMember(member: Member) {
        mMemberRepository.insertMember(member)
    }

    fun delete(member: Member) {
        mMemberRepository.delete(member)
    }
}