package com.im.bin.appUtils

import android.content.Context
import com.google.gson.GsonBuilder

class SharedPreferencesUtil constructor(context: Context) {

    val mPreferences =
        context.getSharedPreferences(
            "WHATS_APP_DELETED_STORE_SHARED_PREFERENCES",
            Context.MODE_PRIVATE
        )

    private val mEditor = mPreferences.edit()
    private val mGson = GsonBuilder().create()

    /**
     * Saves object into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/
    fun <T> putObject(key: String, y: T) {
        //Convert object to JSON String.
        val inString = mGson.toJson(y)
        //Save that String in SharedPreferences
        mEditor.putString(key, inString).apply()
    }

    /**
     * Saves collection of objects into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/

    fun <T> getObject(key: String, c: Class<T>): T? {
        //We read JSON String which was saved.
        val value = mPreferences.getString(key, null)
        if (value != null) {
            //JSON String was found which means object can be read.
            //We convert this JSON String to model object. Parameter "context" (of
            //type Class < T >" is used to cast.
            return mGson.fromJson(value, c)
        } else {
            //No JSON String with this key was found which means key is invalid or object was not saved.
            return null
        }
    }

    /**
     * Save the String Value in SharedPreferences.
     */
    fun setPreferenceValue(key: String, value: String) {
        mEditor.putString(key, value).apply()
    }

    /**
     * get the String Value from Preferences.
     */
    fun getIntegerValue(key: String) = mPreferences.getInt(key, -1)

    /**
     * Save the String Value in SharedPreferences.
     */
    fun setIntegerValue(key: String, value: Int) {
        mEditor.putInt(key, value).apply()
    }

    /**
     * get the String Value from Preferences.
     */
    fun getPreferenceValue(key: String) = mPreferences.getString(key, "")


    /**
     * Save the Bool Value in SharedPreferences.
     */
    fun setBoolPref(key: String, pref: Boolean) {
        mEditor.putBoolean(key, pref).apply()
    }

    /**
     * get the Bool Value from Preferences.
     */
    fun getBoolPref(key: String) = mPreferences.getBoolean(key, false)
}