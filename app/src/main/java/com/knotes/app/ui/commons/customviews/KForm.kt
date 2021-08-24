package com.knotes.app.ui.commons.customviews

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.view.children

class KForm : LinearLayout {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }


    /**
     * @param [editTexts] An arbitrary number of KEditTexts
     * with validation logic
     * Runs the validation method for each editText provided
     * @return true if all form fields are valid
     * @author Kenneth Ngedo
     */
    fun formIsValid(): Boolean {
        var allFieldsAreValid = true
        val editTexts = this.getFormFields()
        for (editText in editTexts) {
            val errorMessage = editText.validation?.invoke(editText.text?.trim().toString()) ?: ""
            // set error in text field
            if (errorMessage.isNotEmpty()) {
                editText.error = errorMessage
                allFieldsAreValid = false
            }
        }
        return allFieldsAreValid
    }

    private fun ViewGroup.getFormFields(
        viewsFound: ArrayList<KEditText> = arrayListOf<KEditText>(),
        discoveredViewGroups: ArrayList<ViewGroup> = arrayListOf()
    ): ArrayList<KEditText> {

        this.children.forEach { v ->
            (v as? KEditText)?.let {
                viewsFound.add(v)
            } ?: run {
                if (v is ViewGroup) {
                    discoveredViewGroups.add(v)
                }
            }
        }

        if (discoveredViewGroups.isNotEmpty()) {
            val nextViewGroup = discoveredViewGroups[0]
            discoveredViewGroups.removeAt(0)
            nextViewGroup.getFormFields(viewsFound, discoveredViewGroups)
        }

        return viewsFound
    }
}