package com.knotes.app.ui.notes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.knotes.app.BR
import com.knotes.app.R
import com.knotes.app.ui.commons.base.BaseActivity
import com.knotes.app.databinding.ActivityNotesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesActivity : BaseActivity<ActivityNotesBinding, NotesViewModel>() {

    @Inject
    lateinit var vm: NotesViewModel

    private lateinit var adapter: NoteListAdapter

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            // val data: Intent? = result.data
            Toast.makeText(this, "Returned from create notes", Toast.LENGTH_LONG).show()
        }
    }

    override fun setViewConfiguration(): Triple<Int, Int, NotesViewModel> {
        return Triple(R.layout.activity_notes, BR.viewModel, vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureView()
        configureBindings()
    }

    override fun onResume() {
        super.onResume()
        viewModel.initialize()
    }

    private fun configureBindings() {
        viewModel.notes.observe(this, { _notes ->
            adapter.notes = _notes
            adapter.notifyDataSetChanged()
        })
    }

    private fun configureView() {
        // Set up note list
        adapter = NoteListAdapter(viewModel.notes.value ?: listOf())
        binding.notesList.layoutManager = LinearLayoutManager(this)
        binding.notesList.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, CreateNotesActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

}