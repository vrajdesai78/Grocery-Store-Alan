package com.vrajdesai78.GroceryStore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import com.vrajdesai78.GroceryStore.R
import com.vrajdesai78.GroceryStore.data.DummyDataSource
import com.vrajdesai78.GroceryStore.data.Repository
import com.vrajdesai78.GroceryStore.model.DataBase
import com.vrajdesai78.GroceryStore.ui.account.AccountFragment
import com.vrajdesai78.GroceryStore.ui.cart.CartFragment
import com.vrajdesai78.GroceryStore.ui.explore.ExploreFragment
import com.vrajdesai78.GroceryStore.ui.favorite.FavoriteFragment
import com.vrajdesai78.GroceryStore.ui.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private var alanButton: AlanButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopFragment()
        initBottomNavigation()

        // Set up the Alan button
        val config = AlanConfig.builder().setProjectId("7dafdbf268565bd74abfdffd7c3138202e956eca572e1d8b807a3e2338fdd0dc/stage").build()
        alanButton = findViewById(R.id.alan_button)
        alanButton?.initWithConfig(config)

        val alanCallback: AlanCallback = object : AlanCallback() {
            /// Handle commands from Alan Studio
            override fun onCommand(eventCommand: EventCommand) {
                try {
                    val command = eventCommand.data
                    val commandName = command.getJSONObject("data").getString("command")
                    when(commandName) {
                        "showFavorite" -> {
                            favoriteFragment()
                            bottom_nav_bar.selectedItemId = R.id.favorite
                        }
                        "showCart" -> {
                            cartFragment()
                            bottom_nav_bar.selectedItemId = R.id.cart
                        }
                        "addItem" -> {
                            val itemName = command.getJSONObject("data").getString("item")
                            val count = command.getJSONObject("data").getString("count")
                            val dummyDataSource = DummyDataSource()
                            val productEntity = dummyDataSource.getProductEntity(itemName)
                            val repository = Repository(dummyDataSource, DataBase.getInstance()!!)
                            if (productEntity != null) {
                                repository.addToCart(productEntity, count.toInt())
                            }
                            Log.d("testing", productEntity.toString())
                            Log.d("testing", "$itemName and $count")
                        }
                    }
                } catch (e: JSONException) {
                    e.message?.let { Log.e("AlanButton", it) }
                }
            }
        };

        // Register callbacks
        alanButton?.registerCallback(alanCallback);
    }

    private fun initBottomNavigation() {
        bottom_nav_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.shop -> shopFragment()
                R.id.explore -> exploreFragment()
                R.id.cart -> cartFragment()
                R.id.favorite -> favoriteFragment()
                R.id.account -> accountFragment()
            }
            true
        }
    }

    private fun shopFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, ShopFragment()).commit()
    }
    private fun exploreFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, ExploreFragment()).commit()
    }
    private fun cartFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, CartFragment()).commit()
    }
    private fun favoriteFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, FavoriteFragment()).commit()
    }
    private fun accountFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, AccountFragment()).commit()
    }

    fun navigateExplore(){
        bottom_nav_bar.selectedItemId = R.id.explore
    }

}