package com.example.testingandencryptiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyPair


const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"
const val ALIAS = "master"


class MainActivity : AppCompatActivity() {

    // RoboElectric / Espresso
    // JUnit Mockito

    var cipherWrapper = CipherWrapper(TRANSFORMATION)
    lateinit var keyStoreWrapper : KeyStoreWrapper
    lateinit var keyPair : KeyPair


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keyStoreWrapper = KeyStoreWrapper(applicationContext).apply { createKeyPair(ALIAS) }
        keyPair = keyStoreWrapper.getKeyPair(ALIAS)

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.encryptBtn -> {
                encryptData()
            }
            R.id.decryptBtn -> {
                decryptData()
            }
        }
    }

    private fun encryptData() {

        val stringToEncrypt = etEnterString.text.toString()
        val encryptedString = cipherWrapper.ecryptData(stringToEncrypt, keyPair.public)
        encrTxt.text = encryptedString

    }

    private fun decryptData() {

        val stringToDecrypt = encrTxt.text.toString()
        if (stringToDecrypt.isNotBlank()) {
            val decryptedString = cipherWrapper.decryptData(stringToDecrypt, keyPair.private)
            dencrTxt.text = decryptedString
        }

    }
}
