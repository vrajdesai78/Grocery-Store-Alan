package com.vrajdesai78.GroceryStore.di

import com.vrajdesai78.GroceryStore.ui.cart.CartViewModel
import com.vrajdesai78.GroceryStore.ui.detailproduct.DetailProductViewModel
import com.vrajdesai78.GroceryStore.ui.explore.ExploreViewModel
import com.vrajdesai78.GroceryStore.ui.favorite.FavoriteViewModel
import com.vrajdesai78.GroceryStore.ui.product.ProductViewModel
import com.vrajdesai78.GroceryStore.ui.shop.ShopViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShopViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { DetailProductViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
    viewModel { CartViewModel(get()) }
}