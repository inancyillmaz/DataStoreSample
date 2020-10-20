package com.codexflow.datastore

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var userManager: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserModel(this)

        var textview = findViewById<TextView>(R.id.textView)
        var textview2 = findViewById<TextView>(R.id.textView2)
        var nameedittext = findViewById<TextView>(R.id.editText)
        var ageedittext = findViewById<TextView>(R.id.editText2)
        var buton = findViewById<TextView>(R.id.buton)


        buton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if (nameedittext.text.length > 0) {
                    GlobalScope.launch {
                        userManager.storeUserInfoName(nameedittext.text.toString())
                    }
                }
                if (ageedittext.text.length > 0) {
                    GlobalScope.launch {
                        userManager.storeUserInfoAge(ageedittext.text.toString().toInt())
                    }
                }
            }
        })


        userManager.userNameFlow.asLiveData().observe(this, {
            textview.text = it
        })
        userManager.userAgeFlow.asLiveData().observe(this, {
            textview2.text = it.toString()

        })
    }
}