package com.example.testingandencryptiondemo

import android.util.Base64
import java.security.Key
import javax.crypto.Cipher

// This will tell what encryption algorithm to use
class CipherWrapper(transformation: String) {


    val cipher = Cipher.getInstance(transformation)


    fun ecryptData(stringToEncrypt: String, key: Key): String {

        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedBytes = cipher.doFinal(stringToEncrypt.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)

    }

    fun decryptData(stringToDecrypt: String, key: Key): String {
        cipher.init(Cipher.DECRYPT_MODE, key)
        val encryptedBytes = Base64.decode(stringToDecrypt, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)

    }


}