package com.batdemir.github.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Stared")
data class StartedModel(
    @PrimaryKey
    val id: Long
)