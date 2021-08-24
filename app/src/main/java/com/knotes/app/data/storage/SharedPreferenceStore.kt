package com.knotes.app.data.storage

import android.content.Context
import com.google.gson.Gson
import com.knotes.app.App

class SharedPreferenceStore private constructor(){

    companion object {
        private var ourInstance: SharedPreferenceStore? = null

        fun getInstance(context: Context): SharedPreferenceStore {
            return ourInstance ?: synchronized(this){
                if (ourInstance == null) {
                    ourInstance = SharedPreferenceStore()
                }
                return ourInstance!!
            }
        }

    }

    inline fun <reified T: Any> getPref(key: String): T? {
        val context = App.getContext()
        val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return Gson().fromJson(prefs.getString(key, ""), T::class.java)
    }

    inline fun <reified T: Any> setPref(key: String, obj: T)  {
        val context = App.getContext()
        val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        prefs.edit().putString(key, Gson().toJson(obj).toString()).apply()
    }

}