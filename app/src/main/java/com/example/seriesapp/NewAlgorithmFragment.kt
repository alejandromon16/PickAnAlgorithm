package com.example.seriesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
        submitButton = view.findViewById<Button>(R.id.button)
        submitButton.setOnClickListener(this)
        db = FirebaseFirestore.getInstance()

        return view;
    }

    override fun onClick(v: item){
        when(v?.id){
            
        }
    }
}