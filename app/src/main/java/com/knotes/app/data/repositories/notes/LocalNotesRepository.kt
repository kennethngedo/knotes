package com.knotes.app.data.repositories.notes

import com.knotes.app.data.models.ApiResult
import com.knotes.app.data.models.CreateNoteModel
import com.knotes.app.data.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalNotesRepository @Inject constructor(): NotesRepository {

    companion object {
        private val notes = mutableListOf<Note>()
    }

    override fun addNote(note: CreateNoteModel): ApiResult<Note> {
        val newNote = Note(notes.size + 1, note.title!!, note.content!!, false)
        notes.add(newNote)
        return ApiResult.Success(newNote)
    }

    override fun getNote(fileName: String): Note {
        TODO("Not yet implemented")
    }

    override fun deleteNote(fileName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNotes(): ApiResult<List<Note>> {
        return withContext(Dispatchers.IO) {
            ApiResult.Success(notes)
        }
    }


}