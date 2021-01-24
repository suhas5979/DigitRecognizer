package com.scube.digitrecognizer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var mClassifier: Classifier? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setResultInvisible()
        try {
            mClassifier = Classifier(this)
        } catch (e: IOException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }

        btn_detect.setOnClickListener {
            val bitmap =
                fpv_paint.exportToBitmap(Classifier.IMG_WIDTH, Classifier.IMG_HEIGHT)
            val res = mClassifier!!.classify(bitmap)
            setResultVisible()
            probability.text = "${resources.getString(R.string.probability)}: ${res.probability *100}%"
            prediction.text = "${resources.getString(R.string.prediction)}: ${res.number}"
            timecost.text = "${resources.getString(R.string.timeCost)}:${ res.timeCost}"
        }
        btn_clear.setOnClickListener {

            fpv_paint.clear()
            prediction.text = ""
            probability.text = ""
            timecost.text = ""
            setResultInvisible()
        }
    }
    private fun setResultVisible(){
        prediction.visibility = View.VISIBLE
        probability.visibility = View.VISIBLE
        timecost.visibility = View.VISIBLE
    }
    private fun setResultInvisible(){
        prediction.visibility = View.INVISIBLE
        probability.visibility = View.INVISIBLE
        timecost.visibility = View.INVISIBLE
    }
}