package com.example.rxjava_maps

import android.annotation.SuppressLint
import io.reactivex.Observable
import java.util.*

fun main() {
    concatExample()
    Thread.sleep(3000)
}

fun concatExample() {
    val obs1 = Observable.just(1, 1, 1)
    val obs2 = Observable.just(2, 2)
    Observable.concat(obs1, obs2)
        .subscribe { x ->
            println("item: $x")
        }
}