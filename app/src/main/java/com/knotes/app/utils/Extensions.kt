package com.knotes.app.utils

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity

/**
 * A String extension method to convert the given
 * string to sentence case
 * @return a new string formatted to sentence case
 * @author Kenneth Ngedo
 */
internal fun String.toSentenceCase(): String {
    return "${this.first().uppercase()}${this.slice(1 until this.length).lowercase()}"
}

/**
 * An EditText extension method to set a text change listener
 * @return a new string formatted to sentence case
 * @author Kenneth Ngedo
 */
internal fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            onTextChanged.invoke(editable.toString())
        }
    })
}

/**
 * intent to launch the activity
 * onResult Will be invoked after receiving some result from
 * @author Kenneth Ngedo
 */
internal fun startKActivityForResult(
    activity: ComponentActivity,
    intent: Intent,
    onResult: (Intent?) -> Unit
) {
    activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            onResult.invoke(data)
        }
    }.launch(intent)
}

/**
 * intent to launch the activity
 * onResult Will be invoked after receiving some result from
 * @author Kenneth Ngedo
 */
internal fun startKActivityForResult(
    activity: FragmentActivity,
    intent: Intent,
    onResult: (Intent?) -> Unit
) {
    activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            onResult.invoke(data)
        }
    }.launch(intent)
}


