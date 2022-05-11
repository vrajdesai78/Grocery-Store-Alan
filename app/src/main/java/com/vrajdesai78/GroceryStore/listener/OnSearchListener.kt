package com.vrajdesai78.GroceryStore.listener

import com.vrajdesai78.GroceryStore.model.product.ProductEntity

interface OnSearchListener {
    fun onSearch(productEntity: ProductEntity)
}