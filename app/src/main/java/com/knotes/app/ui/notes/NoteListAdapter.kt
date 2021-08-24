package com.knotes.app.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knotes.app.R
import com.knotes.app.data.models.Note
import com.knotes.app.databinding.ItemNoteBinding
import com.knotes.app.utils.toSentenceCase
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(var notes: List<Note>) :
    RecyclerView.Adapter<NoteListAdapter.NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes[position]
        holder.bindNote(note)
    }

    override fun getItemCount() = notes.size

    class NoteHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var note: Note? = null
        private val binding = ItemNoteBinding.bind(v)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        fun bindNote(note: Note) {
            this.note = note
            binding.titleTvw.text = note.title.toSentenceCase()
            binding.contentTvw.text = note.content
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            val date: Date
            val dateLabel: String
//            if (note.lastModified.after(note.dateCreated)) {
//                date = note.lastModified
//                dateLabel = "Last modified: "
//            } else {
//                date = note.dateCreated
//                dateLabel = "Created: "
//            }

            //binding.dateTvw.text = "%s %s".format(dateLabel, sdf.format(date))
        }

    }
}