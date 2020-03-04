package com.example.mapsinrxjava

import android.annotation.SuppressLint
import java.sql.DriverManager.println
import java.util.*
import io.reactivex.Observable

fun main() {

    println("Printing Tickets:")
//    fromExample()
    println("Filter Tickets:")
    // filterTickets()
    println("Mapping Tickets:")
    // mappingTickets()
    Thread.sleep(3000)
}

@SuppressLint("CheckResult")
fun fromExample() {

    val ticket1 = Observable.just(
            Ticket("NRT", "CAE", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
                    Airline(2, "United Airlines", "O"),
                    Price(2000f, "128", "JPY", "123", "NRT", "CAE")),
            Ticket("ATL", "SFO", "9am", "8am", "24H", "Have fun", "211", 3,
                    Airline(2, "Delta", "G"),
                    Price(2f, "24", "USD", "211", "ATL", "SFO")))
    val ticket2 = Observable.just(Ticket("NRT", "LAX", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
            Airline(2, "United Airlines", "O"),
            Price(2000f, "128", "EUR", "123", "NRT", "CAE")),
            Ticket("NRT", "CAE", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
                    Airline(2, "American", "O"),
                    Price(2000f, "128", "JPY", "123", "NRT", "CAE")))

    Observable.concat(ticket1, ticket2).filter { ticket -> ticket.airline.name == "United Airlines" }
            .subscribe { ticket -> println(ticket) }


}

fun chainTickets() {

    val ticket1 = Observable.just(
            Ticket("NRT", "CAE", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
                    Airline(2, "United Airlines", "O"),
                    Price(2000f, "128", "JPY", "123", "NRT", "CAE")),
            Ticket("ATL", "SFO", "9am", "8am", "24H", "Have fun", "211", 3,
                    Airline(2, "Delta", "G"),
                    Price(2f, "24", "USD", "211", "ATL", "SFO")))
    val ticket2 = Observable.just(Ticket("NRT", "LAX", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
            Airline(2, "United Airlines", "O"),
            Price(2000f, "128", "EUR", "123", "NRT", "CAE")),
            Ticket("NRT", "CAE", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
                    Airline(2, "American", "O"),
                    Price(2000f, "128", "JPY", "123", "NRT", "CAE")))

            Observable.concat(ticket2,ticket1)

            .filter { ticket ->
                ticket.price?.price!! >= 1000f
            }
            .subscribe { ticket ->
                println(ticket)
            }
}


fun filterTickets() {

    val ticket1 = Observable.just(
            Ticket("NRT", "CAE", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
                    Airline(2, "United Airlines", "O"),
                    Price(2000f, "128", "JPY", "123", "NRT", "CAE")),
            Ticket("ATL", "SFO", "9am", "8am", "24H", "Have fun", "211", 3,
                    Airline(2, "Delta", "G"),
                    Price(2f, "24", "USD", "211", "ATL", "SFO")))
    val ticket2 = Observable.just(Ticket("NRT", "LAX", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
            Airline(2, "United Airlines", "O"),
            Price(2000f, "128", "EUR", "123", "NRT", "CAE")),
            Ticket("NRT", "CAE", "4:00PM", "9AM", "24H", "enjoy", "123", 3,
                    Airline(2, "American", "O"),
                    Price(2000f, "128", "JPY", "123", "NRT", "CAE")))
            .filter { ticket ->
                ticket.airline.name == "Delta"
            }
            .filter { ticket ->
                ticket.price?.price!! >= 1000f
            }
            .subscribe { ticket ->
                println(ticket)
            }
}

fun printTickets() {
    Observable.just(
            Ticket("a", "b", "c", "d", "e", "j", "t", 3,
                    Airline(2, "airplane", "o"),
                    Price(8f, "x", "o", "e", "p", "o")),
            Ticket("a", "b", "c", "d", "e", "j", "t", 3,
                    Airline(2, "air", "o"),
                    Price(2f, "x", "o", "e", "p", "o")),
            Ticket("a", "b", "c", "d", "e", "j", "t", 3,
                    Airline(2, "plane", "o"),
                    Price(35f, "x", "o", "e", "p", "o")),
            Ticket("a", "b", "c", "d", "e", "j", "t", 3,
                    Airline(2, "airplane", "o"),
                    Price(0f, "x", "o", "e", "p", "o")))
            .subscribe { x ->
                println(x)
            }
}
