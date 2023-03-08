package com.diet.model.saveable

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.diet.model.Product

data class ProductsSaveable(
    var receivedItems: MutableList<Product>
) {

    companion object{
        val HolderSaver = Saver<MutableState<ProductsSaveable>, MutableList<Product>>(
            save = {
                it.value.receivedItems
                   },
            restore = {
                mutableStateOf(ProductsSaveable(it))
            }
        )
    }


}