package com.example.yoginipatelpractical.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

data class UserResponseModel(
    val backoff: Int,
    val has_more: Boolean,
    val items: ArrayList<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)

@Entity(tableName = "Item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val account_id: Int,
    @TypeConverters(Converters::class)val badge_counts: BadgeCounts,
    val creation_date: Int,
    val display_name: String,
    val is_employee: Boolean,
    val last_access_date: Int,
    val last_modified_date: Int,
    val link: String,
    val location: String,
    val profile_image: String,
    val reputation: Int,
    val reputation_change_day: Int,
    val reputation_change_month: Int,
    val reputation_change_quarter: Int,
    val reputation_change_week: Int,
    val reputation_change_year: Int,
    val user_id: Int,
    val user_type: String
)

data class BadgeCounts(
    val bronze: Int,
    val gold: Int,
    val silver: Int
)

class Converters {

    // Convert BadgeCounts object to a JSON string
    @TypeConverter
    fun fromBadgeCounts(badgeCounts: BadgeCounts): String {
        return Gson().toJson(badgeCounts)
    }

    // Convert a JSON string back to BadgeCounts object
    @TypeConverter
    fun toBadgeCounts(json: String): BadgeCounts {
        return Gson().fromJson(json, BadgeCounts::class.java)
    }
}