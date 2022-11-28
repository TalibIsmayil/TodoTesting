package com.scrumlaunch.todotesting.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
