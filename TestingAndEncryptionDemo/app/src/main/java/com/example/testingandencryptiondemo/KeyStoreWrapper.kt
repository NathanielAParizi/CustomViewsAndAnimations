package com.example.testingandencryptiondemo

import android.content.Context
import android.icu.util.Calendar
import android.security.KeyPairGeneratorSpec
import java.math.BigInteger
import java.nio.channels.NetworkChannel
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.interfaces.RSAKey
import javax.security.auth.x500.X500Principal

const val PROVIDER_KEYSTORE = "AndroidKeyStore"

class KeyStoreWrapper (val context : Context){

    val keyStore = KeyStore.getInstance(PROVIDER_KEYSTORE).apply({load(null)})

    //public and private keys
    fun createKeyPair(alias : String) : KeyPair {



        val generator = KeyPairGenerator.getInstance("RSA", PROVIDER_KEYSTORE)
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance().apply {startDate.add(Calendar.YEAR, 1)}

        val generatedSpecs = KeyPairGeneratorSpec.Builder(context)
            .setAlias(alias)
            .setSerialNumber(BigInteger.ONE)
            .setStartDate(startDate.time)
            .setEndDate(endDate.time)
            .setSubject(X500Principal("CN = ${alias} CA Certificate"))
            .build()

        generator.initialize(generatedSpecs)
        return generator.generateKeyPair()
    }

    fun getKeyPair(alias : String) : KeyPair {

        val privateKey = keyStore.getKey(alias,null) as PrivateKey
        val publicKey = keyStore.getCertificate(alias).publicKey
        return KeyPair (publicKey,privateKey)

    }

    fun removeKeys(alias : String){
        keyStore.deleteEntry(alias)
    }

}