package com.lupa.member.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.lupa.member.database.Member
import com.lupa.member.database.MemberDao
import com.lupa.member.database.MemberRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MemberRepository(application: Application) {
    private val mMemberDao: MemberDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = MemberRoomDatabase.getDatabase(application)
        mMemberDao = db.memberDao()
    }

    fun getAllMembers(): LiveData<List<Member>> = mMemberDao.getAllMembers()

    fun insert(member: Member) {
        executorService.execute { mMemberDao.insertMember(member) }
    }

    fun delete(member: Member) {
        executorService.execute { mMemberDao.deleteMember(member) }
    }

}