package com.ryanjames.composemobileordering.features.bag

import com.google.android.gms.maps.model.LatLng
import com.ryanjames.composemobileordering.ui.core.AlertDialogState

data class BagScreenState(
    val bagItems: List<BagItemRowDisplayModel>,
    val venueId: String?,
    val venueName: String?,
    val btnRemoveState: ButtonState,
    val btnCancelState: ButtonState,
    val btnRemoveSelectedState: ButtonState,
    val isRemoving: Boolean,
    val alertDialog: AlertDialogState?,
    val subtotal: String = "",
    val tax: String = "",
    val total: String = "",
    val restaurantPosition: LatLng = LatLng(0.0, 0.0),
    val venueAddress: String = "",
    val isPickupSelected: Boolean = true,
    val deliveryAddress: String? = null,
    val isBagEmpty: Boolean = false,
    val isLoading: Boolean = true
)

data class ButtonState(val enabled: Boolean, val visible: Boolean)

data class BagItemRowDisplayModel(
    val lineItemId: String,
    val qty: String,
    val itemName: String,
    val itemModifier: String,
    val price: String,
    val forRemoval: Boolean
)