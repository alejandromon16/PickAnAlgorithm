package com.example.seriesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var  valueInput: EditText
    private lateinit var  submitBtn: Button
    private lateinit var db: FirebaseFirestore
    private lateinit var selectedItem: String

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        valueInput = view.findViewById(R.id.input_value)
        submitBtn = view.findViewById<Button>(R.id.submit_btn)
        submitBtn.setOnClickListener(this)
        db = FirebaseFirestore.getInstance()

        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)

        db = FirebaseFirestore.getInstance()
        db.collection("algorithms")
            .get()
            .addOnSuccessListener { result ->
                // Create a list of algorithm names
                val algorithmNames = result.map { document -> document.getString("name")!! }

                // Set up the adapter with the list of algorithm names
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, algorithmNames)
                autoCompleteTextView.setAdapter(adapter)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting algorithms", exception)
            }

        autoCompleteTextView.threshold = 1
        autoCompleteTextView.dropDownHeight = ViewGroup.LayoutParams.WRAP_CONTENT

        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(requireContext(), "You selected $selectedItem", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

//    override fun onClick(p0: View?) {
//        R.id.submitButton -> showAlgo()
//    }
//
//    private fun showAlgo() {
//
//        val value = valueInput.text.toString()
//
//
//        val docRef = db.collection("algorithms").document(selectedItem)
//        val doc = docRef.get().onSuccessTask { e ->
//            val = e.getString("algorithm")
//        }
//    }
}
