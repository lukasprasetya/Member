package com.lupa.member.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_member")
data class Member(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "member_id")
    var memberId: Int? = null,

    @ColumnInfo(name = "group_id")
    val groupId: Int? = null,

    @ColumnInfo(name = "member_name")
    var memberName: String

) : Parcelable
