package com.example.simplegamesapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*


class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val btn: Button = view.findViewById(R.id.button_start)
        btn.setOnClickListener {
            val editText: EditText = view.findViewById(R.id.inputName)
            val input = editText.text.toString()
            val bundle = Bundle()
            bundle.putString("data", input)
            val fragment = MenuFragment()
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.nav_container,fragment)?.commit()
        }
        return view
    }
}

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
//        button_start.setOnClickListener {
//            if(inputName.text.toString().isEmpty())
//            {
//                Toast.makeText(this,"Enter Your Name", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                var intent = Intent( this, MenuActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//    }
//}