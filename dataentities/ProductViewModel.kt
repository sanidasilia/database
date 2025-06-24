package com.example.supermarket.dataentities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf


class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    // product list ui
    val productList = mutableStateListOf<Product>()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            val products = repository.getAll()
            productList.clear()
            productList.addAll(products)
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.insert(product)
            loadProducts()
        }
    }
}