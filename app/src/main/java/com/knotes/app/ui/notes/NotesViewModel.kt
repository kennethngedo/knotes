package com.knotes.app.ui.notes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.knotes.app.data.models.ApiResult
import com.knotes.app.data.models.CreateNoteModel
import com.knotes.app.data.models.Note
import com.knotes.app.data.repositories.notes.NotesRepository
import com.knotes.app.ui.commons.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository) : BaseViewModel() {
     override var TAG = this::class.java.name

    private val createNoteModel = CreateNoteModel()

    val notes = MutableLiveData<List<Note>>()

    val formIsValid = MutableLiveData(false)

    val newNote = MutableLiveData<Note>(null)

    override fun initialize() {
        getNotes()
    }

    override fun destroy() {

    }

    private fun getNotes() = runBlocking {
        showLoader()

        launch {
            val result = withContext(Dispatchers.Default) { notesRepository.getAllNotes() }
            hideLoader()
            when (result) {
                is ApiResult.Success -> {
                    notes.value = result.data
                }
                else -> {
                    Log.d(TAG, "Error: $result")
                }
            }
        }

    }

    fun addNote() = runBlocking {
        showLoader()

        launch {
            val result = withContext(Dispatchers.Default) {
                notesRepository.addNote(createNoteModel)
            }
            hideLoader()
            when (result) {
                is ApiResult.Success -> {
                    newNote.value = result.data
                }
                else -> {
                    Log.d(TAG, "Error: $result")
                }
            }
        }

    }

    fun setNoteTitle(value: String) {
        createNoteModel.title = value
        checkFormValidity()
    }

    fun setNoteContent(value: String) {
        createNoteModel.content = value
        checkFormValidity()
    }

    private fun checkFormValidity() {
        formIsValid.value =
            !createNoteModel.title.isNullOrEmpty() && !createNoteModel.content.isNullOrEmpty()
    }


}