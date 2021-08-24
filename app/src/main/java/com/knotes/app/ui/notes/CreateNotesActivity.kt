package com.knotes.app.ui.notes

import android.content.Intent
import android.os.Bundle
import com.knotes.app.BR
import com.knotes.app.R
import com.knotes.app.ui.commons.base.BaseActivity
import com.knotes.app.databinding.ActivityCreateNotesBinding
import com.knotes.app.utils.onTextChanged
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateNotesActivity : BaseActivity<ActivityCreateNotesBinding, NotesViewModel>() {
    @Inject
    lateinit var vm : NotesViewModel

    override fun setViewConfiguration(): Triple<Int, Int, NotesViewModel> {
        return Triple(R.layout.activity_create_notes, BR.viewModel, vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureView()
        configureBindings()
    }

    private fun configureBindings() {
        viewModel.formIsValid.observe(this){
            binding.saveBtn.isEnabled = it
        }
        viewModel.newNote.observe(this) { note ->
            //Todo show success dialog before close
            if(note != null) {
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    private fun configureView() {
        // Set note creation fields
        binding.noteTitleEdt.onTextChanged { value -> binding.viewModel?.setNoteTitle(value) }
        binding.noteContentEdt.onTextChanged { value -> binding.viewModel?.setNoteContent(value) }
        // Set up save note button
        binding.saveBtn.setOnClickListener {
            binding.viewModel?.addNote()
        }
    }


}