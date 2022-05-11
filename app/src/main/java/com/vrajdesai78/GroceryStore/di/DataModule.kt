package com.vrajdesai78.GroceryStore.di

import com.vrajdesai78.GroceryStore.data.DummyDataSource
import com.vrajdesai78.GroceryStore.data.Repository
import com.vrajdesai78.GroceryStore.model.DataBase
import org.koin.dsl.module

val dataModule = module {

    single { DataBase.getInstance() }
    factory { DummyDataSource() }
    single { Repository(get(), get()) }

}