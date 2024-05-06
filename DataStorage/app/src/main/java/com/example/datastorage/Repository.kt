package com.example.datastorage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.FileInputStream
import java.io.IOException

private const val PREFERENCE_NAME = "preference_name"
private const val SHARED_PREFS_KEY = "shared_prefs_key"
private const val FILE_NAME = "fileName.txt"

class Repository {
    private var localVariable: String? = null

    /*
    будет доставать значение из источников:
    сначала попытается взять значение локальной переменной; если оно null,
    то попытаемся взять значение из SharedPreference.
     */
    fun getText(context: Context): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!
            loadFromFile(context) != null -> loadFromFile(context)!!
            else -> ""
        }
    }

    private fun loadFromFile(context: Context): String? {
        var fin: FileInputStream? = null
        return try {
            fin = context.openFileInput(FILE_NAME)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            String(bytes)
        } catch (ex: IOException) {
            null
        } finally {
            fin?.close()
        }
    }

    /*
   будет доставать значение из SharedPreference
   */
    private fun getDataFromSharedPreference(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return prefs.getString(SHARED_PREFS_KEY, null)
    }

    /*
    будет доставать значение, возвращать значение локальной переменной
     */
    private fun getDataFromLocalVariable(): String? {
        return localVariable
    }


    /*
   будет записывать значения и в SharedPreference, и в локальную переменную
    */
    fun saveText(context: Context, text: String) {
        localVariable = text
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        with(prefs.edit()) {
            putString(SHARED_PREFS_KEY, text)
            apply()
        }
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(text.toByteArray())
        }
    }

    /*
    будет очищать значение и в SharedPreference, и в локальной переменной
     */
    fun clearText(context: Context) {
        localVariable = null
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        with(prefs.edit()) {
            remove(SHARED_PREFS_KEY)
            apply()
        }
        context.deleteFile(FILE_NAME)
    }
}