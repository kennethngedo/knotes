package com.knotes.app.data.models

import java.io.Serializable

data class Note(
    var id: Int? = null,
    var title: String,
    var content: String,
    var checked: Boolean
) : Serializable

data class CreateNoteModel(var title: String? = null, var content: String? = null)