package com.example.testingandencryptiondemo

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class StringUtilsTest  {

    // 2 annotations to start
    // @Before - runs before every test
    // @BeforeClass - runs once for the whole test suite

    lateinit var testStringUtil : StringUtils
    @Before
    fun setup(){
        testStringUtil = StringUtils()

    }

    // Every test function gets annotated using the @Test Annotation
    @Test
    fun testReverseStringReturnsReversedString(){
        val expectedResult = "olleH"
        val valueToTest = "Hello"
        Assert.assertEquals(expectedResult, testStringUtil.reverseString(valueToTest))
    }

    @Test
    fun testIsStringProperLengthReturnsTrue(){

        val testValue = "Welcome2EIT"
        Assert.assertTrue(testStringUtil.isStringProperLength(testValue))
    }

    @Test
    fun testIsStringProperLengthReturnsFalse(){

        val testValue = "abc"
        Assert.assertFalse(testStringUtil.isStringProperLength(testValue))

    }
}