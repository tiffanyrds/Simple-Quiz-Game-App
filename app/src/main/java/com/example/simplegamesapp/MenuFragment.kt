package com.example.simplegamesapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
//import com.example.simplegamesapp.databinding.ActivityMainBinding
//import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val textView : TextView = view.findViewById(R.id.name)
        val args = this.arguments
        val inputData = args?.get("data")
        textView.text = inputData.toString()
        val btn: Button = view.findViewById(R.id.button_simple_quizz_game)
        btn.setOnClickListener {
            val intent = Intent(this@MenuFragment.requireContext(), SimpleQuizzActivity::class.java)
            startActivity(intent)

        }
        return view
    }
}