package com.example.exp_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        view.findViewById<MaterialButton>(R.id.btn_contact).setOnClickListener {
            Toast.makeText(requireContext(), "Contacting ExploreHub...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<MaterialButton>(R.id.btn_back_about).setOnClickListener {
            (activity as? MainActivity)?.switchToHome()
        }

        return view
    }
}