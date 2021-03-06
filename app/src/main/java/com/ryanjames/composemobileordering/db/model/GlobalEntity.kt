package com.ryanjames.composemobileordering.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GlobalEntity(
    @PrimaryKey()
    val id: Int,
    val currentOrderId: String?,
    val currentVenue: String?,
    val deliveryAddress: String?
)