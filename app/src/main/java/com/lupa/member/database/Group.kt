package com.lupa.member.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_group")
data class Group(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "group_id")
    val groupId: Int? = null,

    @ColumnInfo(name = "group_name")
    var groupName: String,

    @ColumnInfo(name = "group_note")
    var groupNote: String

) : Parcelable