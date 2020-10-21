package com.codexflow.datastore

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //  lateinit var userManager: StoreLayer


    private lateinit var viewModel: StoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(StoreViewModel::class.java)


        // userManager = StoreLayer(this)

        var textview = findViewById<TextView>(R.id.textView)
        var textview2 = findViewById<TextView>(R.id.textView2)
        var nameedittext = findViewById<TextView>(R.id.editText)
        var ageedittext = findViewById<TextView>(R.id.editText2)
        var buton = findViewById<TextView>(R.id.buton)

        viewModel.Name.observe(this, {
            textview.text = it
        })

        viewModel.Age.observe(this, {
            textview2.text = it.toString()
        })


        buton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if (nameedittext.text.length > 0) {
                    GlobalScope.launch {
                        viewModel.saveText(nameedittext.text.toString())
                    }
                }
                if (ageedittext.text.length > 0) {
                    GlobalScope.launch {
                        viewModel.saveNumber(ageedittext.text.toString().toInt())
                    }
                }
            }
        })


    }
}