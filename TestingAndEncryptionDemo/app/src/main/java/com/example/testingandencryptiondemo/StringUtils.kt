package com.example.testingandencryptiondemo

class StringUtils {


    fun reverseString(stringToReverse : String) : String {
        return StringBuilder(stringToReverse).reverse().toString()
    }

    fun isStringProperLength(passedValue : String) : Boolean{
        return (passedValue.length > 8)
    }
}