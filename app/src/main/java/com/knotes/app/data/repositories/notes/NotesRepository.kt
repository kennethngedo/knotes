package com.knotes.app.data.repositories.notes

import com.knotes.app.data.models.ApiResult
import com.knotes.app.data.models.CreateNoteModel
import com.knotes.app.data.models.Note
import java.util.concurrent.Future

interface NotesRepository {
    fun addNote(note: CreateNoteModel): ApiResult<Note>
    fun getNote(fileName: String): Note
    fun deleteNote(fileName: String): Boolean
    suspend fun getAllNotes(): ApiResult<List<Note>>
}