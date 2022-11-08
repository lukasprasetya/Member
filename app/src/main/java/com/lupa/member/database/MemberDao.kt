package com.lupa.member.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemberDao {
    @Query("SELECT * FROM tb_member ORDER BY id ASC")
    fun getAllMembers(): LiveData<List<Member>>

    @Query("SELECT * FROM tb_member WHERE id == :id")
    suspend fun getAllMembersById(id: Int): Member

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMember(member: Member): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMembers(member: List<Member>)

    @Delete
    fun deleteMember(member: Member): Int

    @Update
    suspend fun updateMember(member: Member): Int
}
