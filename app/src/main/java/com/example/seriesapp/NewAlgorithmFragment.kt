package com.example.seriesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

// TODO: Rename parameter arguments, choose names that match

class NewAlgorithmFragment : Fragment(), View.OnClickListener  {

    private lateinit var  nameInput: EditText
    private lateinit var algorithmInput: EditText
    private lateinit var submitButton: Button
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_algorithm, container, false)

        nameInput = view.findViewById(R.id.inputName)
        algorithmInput = view.findViewById(R.id.inputAlgorithm)
        submitButton = view.findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener(this)
        db = FirebaseFirestore.getInstance()

        return view;
    }

    override fun onClick(v: View?){
        when(v?.id){
            R.id.submitButton -> saveData()
        }
    }

    private fun saveData() {

        val name = nameInput.text.toString()
        val algorithm = algorithmInput.text.toString()

        val data = hashMapOf(
            "name" to name,
            "algorithm" to algorithm
        )

        db.collection("algorithms")
            .add(data)
            .addOnSuccessListener { documentReference ->
                    nameInput.setText("")
                    algorithmInput.setText("")

                    Toast.makeText(context, "Algorithm saved successfully!", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener { e ->
                // There was an error saving the data
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}