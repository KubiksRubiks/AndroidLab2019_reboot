package com.lab.android.androidlab_6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.FontsContract.Columns.RESULT_CODE
import android.support.v7.app.AppCompatActivity
import com.lab.android.androidlab_6.Utils.REQUEST_CODE
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //send data with intent (message-result)
        button.setOnClickListener {
            val message = intent.getCharSequenceExtra("message")
            textView.setText(message)
        }

        //Send and Recieve data via intent
       /* button.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }*/
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                textView.setText("You are welcome")
            }
        }
    }*/
}
