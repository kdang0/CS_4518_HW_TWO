package com.example.basketbol1

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

private const val IS_CLICKED = "com.example.basketbol1.isClicked"
private const val TAG = "BASKETBOL2"
const val IS_CLICKED_TWO = "com.example.basketbol1.isClickedTwo"

class BASKETBOL2 : AppCompatActivity() {
    private lateinit var displayButtonOne: Button
    private var isClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basketbol2)
        isClicked = intent.getBooleanExtra(IS_CLICKED, false)
        displayButtonOne = findViewById(R.id.displayButtonOne)
        val initialClick = when {
            isClicked -> R.string.clickB
            else -> R.string.clickA
        }
        displayButtonOne.setText(initialClick)
        displayButtonOne.setOnClickListener {
            isClicked = !isClicked
            val resultClick = when {
                isClicked -> R.string.clickB
                else -> R.string.clickA
            }
            displayButtonOne.setText(resultClick)
            setIsClicked(isClicked)
        }

    }

    private fun setIsClicked(buttonIsClicked: Boolean) {
        val data = Intent().apply {
            putExtra(IS_CLICKED_TWO, buttonIsClicked)

        }
        setResult(Activity.RESULT_OK, data)
        Log.d(TAG, "updated button")
    }

    companion object {
        fun newIntent(packageContext: Context, isClicked: Boolean): Intent {
            return Intent(packageContext, BASKETBOL2::class.java).apply {
                putExtra(IS_CLICKED, isClicked)
            }
        }
    }
}