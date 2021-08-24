package com.knotes.app.data.repositories.notes

import android.util.Log
import com.knotes.app.data.models.ApiResult
import com.knotes.app.data.models.CreateNoteModel
import com.knotes.app.data.models.Note
import com.knotes.app.data.api.NotesService
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnlineNotesRepository @Inject constructor(): NotesRepository {
    private var TAG = this.javaClass.name

    private val notesService = NotesService.instance()

    override fun addNote(note: CreateNoteModel): ApiResult<Note> {
        val call = notesService.create(note)
        val newNote =
            call.execute().body() ?: return ApiResult.Error(Exception("Something went wrong"))
        return ApiResult.Success(newNote)
    }

    override fun getNote(fileName: String): Note {
        TODO("Not yet implemented")
    }

    override fun deleteNote(fileName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNotes(): ApiResult<List<Note>> {

        val call = notesService.list()
        val notes = call.execute().body()
        Log.d(TAG, notes.toString())
        if (notes.isNullOrEmpty()) return ApiResult.Error(Exception("Something went wrong"))
        return ApiResult.Success(notes)

    }


}