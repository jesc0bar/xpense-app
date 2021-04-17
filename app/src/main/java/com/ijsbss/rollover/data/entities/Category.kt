package com.ijsbss.rollover.data.entities

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "category_id")
        val id: Int,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "expectation")
        val expectation: Float,

        @ColumnInfo(name = "total_spent")
        val totalSpent: Float,

        @ColumnInfo(name = "rollover_period")
        val rolloverPeriod: Byte,

        @ColumnInfo(name = "color")
        val color: Int,

        @ColumnInfo(name = "threshold")
        val threshold: Float,

        @ColumnInfo(name = "expenses_id")
        val expensesId: Int,

        @ColumnInfo(name = "view_order")
        val viewOrder: Byte
)
