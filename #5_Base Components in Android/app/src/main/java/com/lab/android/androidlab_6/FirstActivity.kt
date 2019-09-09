package com.lab.android.androidlab_6

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lab.android.androidlab_6.Utils.REQUEST_CODE
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //send data with intent (message-result)
        button.setOnClickListener {
            val intent: Intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("message", editText.text)
            startActivity(intent)
        }

        //Send and Recieve data via intent
        /*button.setOnClickListener {
            val intent: Intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("message", editText.text)
            startActivityForResult(intent, REQUEST_CODE)
        }*/
    }
}
