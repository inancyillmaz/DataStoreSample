package com.codexflow.datastore.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codexflow.datastore.model.StoreViewModel


import com.codexflow.datastore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //  lateinit var userManager: StoreLayer


    private lateinit var viewModel: StoreViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(StoreViewModel::class.java)


        // userManager = StoreLayer(this)


        viewModel.name.observe(this, {
            binding.textView.text = it

        })

        viewModel.age.observe(this, {
            binding.textView2.text = it.toString()
        })


        binding.buton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if (binding.editText.text.length > 0) {
                    viewModel.saveText(binding.editText.text.toString())
                }
                if (binding.editText2.text.length > 0) {
                    viewModel.saveNumber(binding.editText2.text.toString().toInt())
                }
            }
        })


    }
}