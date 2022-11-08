package com.lupa.member.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Group2(
    val groupId: Int? = null,

    var groupName: String,

    var groupNote: String,

    var groupCount: Int

) : Parcelable