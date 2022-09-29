package uz.unidev.eventdetector.data.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:05
 */

@Entity(tableName = "event_table")
@Parcelize
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: Int,
    val image: Int
) : Parcelable