package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val PRICE_CAPCAKE = 100.00
private const val ADDITION_PRICE = 500.00

class OrderViewModel : ViewModel() {

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavour = MutableLiveData<String>()
    val flavour: LiveData<String> = _flavour

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    val dateList: List<String> = getPickupDates()

    fun setQuantity(numberCapcakes: Int) {
        _quantity.value = numberCapcakes
        calcPrice()
    }

    fun calcPrice() {
        var total = (_quantity.value ?: 0) * PRICE_CAPCAKE
        if(dateList[0] == _date.value) {
            total += ADDITION_PRICE
        }
        _price.value = total
    }

    fun setFlavour(f: String) {
        _flavour.value = f
    }

    fun getPickupDates() : List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun setDate(d: String) {
        _date.value = d
        calcPrice()
    }
}