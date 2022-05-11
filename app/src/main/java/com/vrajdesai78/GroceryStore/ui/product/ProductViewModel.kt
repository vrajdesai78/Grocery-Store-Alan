package com.vrajdesai78.GroceryStore.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrajdesai78.GroceryStore.data.Repository
import com.vrajdesai78.GroceryStore.model.product.ProductEntity
import com.vrajdesai78.GroceryStore.utill.ProductSavedType
import io.reactivex.disposables.CompositeDisposable

class ProductViewModel(val repository : Repository): ViewModel() {

    private val _beverages = MutableLiveData<ArrayList<ProductEntity>>()
    val beverages: LiveData<ArrayList<ProductEntity>> = _beverages

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun showDataBeverages() {
        val beveragesDisposable = repository.getBeverages()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _beverages.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(beveragesDisposable)
    }

    fun addToCahar(productEntity: ProductEntity) {
        repository.addToCart(productEntity)
    }

    fun removeProduct(productEntity: ProductEntity) {
        repository.removeProductCart(productEntity.id, ProductSavedType.CART)
    }
}