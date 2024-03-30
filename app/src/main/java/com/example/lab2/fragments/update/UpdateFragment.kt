package com.example.lab2.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lab2.R
import com.example.lab2.data.entities.Note
import com.example.lab2.data.vm.NoteViewModel

class UpdateFragment : Fragment() {
    private  val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        view.findViewById<TextView>(R.id.updateNote).text = args.currentNote.note

        val datePicker = view.findViewById<DatePicker>(R.id.updateDate)
        val dateParts = args.currentNote.date.split("/")
        val day = dateParts[0].toInt()
        val month = dateParts[1].toInt() - 1
        val year = dateParts[2].toInt()

        datePicker.updateDate(year, month, day)

        val updateButton = view.findViewById<Button>(R.id.update)
        updateButton.setOnClickListener {
            updateNote()
        }

        val deleteButton = view.findViewById<Button>(R.id.delete)
        deleteButton.setOnClickListener {
            deleteNote()
        }

        val backButton = view.findViewById<Button>(R.id.backToListFromUpdate)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        return  view
    }

    private  fun updateNote(){
        val noteText = view?.findViewById<EditText>(R.id.updateNote)?.text.toString()
        val datePicker = view?.findViewById<DatePicker>(R.id.updateDate)

        if(noteText.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.noUpdate), Toast.LENGTH_LONG).show()
        } else if (noteText.length != 0 && noteText.length < 5){
            Toast.makeText(view?.context,  getString(R.string.minNote), Toast.LENGTH_LONG).show()
        } else if (datePicker == null){
            Toast.makeText(requireContext(),  getString(R.string.selectDate), Toast.LENGTH_LONG).show()
        }
        else {
            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year

            val selectedDate = "$day/${month + 1}/$year"

            val note = Note(args.currentNote.id, noteText, selectedDate)

            mNoteViewModel.updateNote(note)

            makeText(requireContext(),  getString(R.string.updated), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            makeText(
                requireContext(),
                getString(R.string.deleted),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(getString(R.string.delete))
        builder.setMessage(getString(R.string.sureDelete))
        builder.create().show()
    }
}