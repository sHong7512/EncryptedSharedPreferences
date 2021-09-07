package com.shong.encryptedsharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val encryptedSharedPrefsFile: String = "FILE_NAME"
        val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            encryptedSharedPrefsFile,
            mainKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        with(encryptedSharedPreferences.edit()) {
            // Edit the user's shared preferences...
            putString("id", "sHong")
            apply()
        }

        Log.d(
            "${this::class.java.simpleName}_sHong",
            "id : ${encryptedSharedPreferences.getString("id", "default")}"
        )


    }
}