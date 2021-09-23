package com.example.basketbol1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_AScore = "AScore"
private const val KEY_BScore = "BScore"
private const val REQUEST_CODE_CLICKED = 0

class main_fragment : Fragment() {
    private lateinit var textA: TextView
    private lateinit var textAPTS: TextView
    private lateinit var textB: TextView
    private lateinit var textBPTS: TextView
    private lateinit var buttonAPT3: Button
    private lateinit var buttonAPT2: Button
    private lateinit var buttonAPT1: Button
    private lateinit var buttonBPT3: Button
    private lateinit var buttonBPT2: Button
    private lateinit var buttonBPT1: Button
    private lateinit var buttonReset: Button
    private lateinit var displayButton: Button
    private lateinit var mContext: Context
    private val basketbolViewModel: BasketbolViewModel by lazy {
        ViewModelProviders.of(this).get(BasketbolViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        Log.d(TAG, "Got a BasketbolViewModel: $basketbolViewModel")
//        val aScore = savedInstanceState?.getInt(KEY_AScore, 0) ?: 0
//        basketbolViewModel.teams[0].score = aScore
//        textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        val bScore = savedInstanceState?.getInt(KEY_BScore, 0) ?: 0
//        basketbolViewModel.teams[1].score = bScore
//        textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        buttonAPT3.setOnClickListener {
//            basketbolViewModel.updatePts("A", 3)
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        }
//        buttonAPT2.setOnClickListener {
//            basketbolViewModel.updatePts("A", 2)
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        }
//        buttonAPT1.setOnClickListener {
//            basketbolViewModel.updatePts("A", 1)
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        }
//        buttonBPT3.setOnClickListener {
//            basketbolViewModel.updatePts("B", 3)
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }
//        buttonBPT2.setOnClickListener {
//            basketbolViewModel.updatePts("B", 2)
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }
//        buttonBPT1.setOnClickListener {
//            basketbolViewModel.updatePts("B", 1)
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }
//
//        buttonReset.setOnClickListener {
//            basketbolViewModel.reset()
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }


//        displayButton.setOnClickListener {
////            val isClicked = basketbolViewModel.butIsClicked
//            val intent = context?.let { it1 -> BASKETBOL2.newIntent(it1,basketbolViewModel.butIsClicked) }
//            startActivityForResult(intent, REQUEST_CODE_CLICKED)
//        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basketbol, container, false)
        buttonAPT3 = view.findViewById(R.id.buttonAPT3) as Button
        buttonAPT2 = view.findViewById(R.id.buttonAPT2) as Button
        buttonAPT1 = view.findViewById(R.id.buttonAPT1) as Button
        buttonBPT3 = view.findViewById(R.id.buttonBPT3) as Button
        buttonBPT2 = view.findViewById(R.id.buttonBPT2) as Button
        buttonBPT1 = view.findViewById(R.id.buttonBPT1) as Button
        buttonReset = view.findViewById(R.id.buttonReset) as Button
        displayButton = view.findViewById(R.id.displayButton) as Button
        textA = view.findViewById(R.id.textA) as TextView
        textAPTS = view.findViewById(R.id.textAPTS) as TextView
        textB = view.findViewById(R.id.textB) as TextView
        textBPTS = view.findViewById(R.id.textBPTS) as TextView
        val aScore = savedInstanceState?.getInt(KEY_AScore, 0) ?: 0
        basketbolViewModel.teams[0].score = aScore
        textAPTS.text = basketbolViewModel.teamAPoints.toString()
        val bScore = savedInstanceState?.getInt(KEY_BScore, 0) ?: 0
        basketbolViewModel.teams[1].score = bScore
        textBPTS.text = basketbolViewModel.teamBPoints.toString()
        buttonAPT3.setOnClickListener {
            basketbolViewModel.updatePts("A", 3)
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
        }
        buttonAPT2.setOnClickListener {
            basketbolViewModel.updatePts("A", 2)
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
        }
        buttonAPT1.setOnClickListener {
            basketbolViewModel.updatePts("A", 1)
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
        }
        buttonBPT3.setOnClickListener {
            basketbolViewModel.updatePts("B", 3)
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
        }
        buttonBPT2.setOnClickListener {
            basketbolViewModel.updatePts("B", 2)
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
        }
        buttonBPT1.setOnClickListener {
            basketbolViewModel.updatePts("B", 1)
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
        }

        buttonReset.setOnClickListener {
            basketbolViewModel.reset()
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
            Log.d(TAG, "Reset done.")
        }

        displayButton.setOnClickListener {
            val isClicked = basketbolViewModel.butIsClicked
            val intent =
                context?.let { it1 -> BASKETBOL2.newIntent(it1, basketbolViewModel.butIsClicked) }
            startActivityForResult(intent, REQUEST_CODE_CLICKED)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CLICKED) {
            basketbolViewModel.butIsClicked = data?.getBooleanExtra(IS_CLICKED_TWO, false) ?: false
            val resultClick = when {
                basketbolViewModel.butIsClicked -> R.string.clickB
                else -> R.string.clickA
            }
            displayButton.setText(resultClick)
            Log.d(TAG, "updated button on homepage")

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSavedInstanceState")
        savedInstanceState.putInt(KEY_AScore, basketbolViewModel.teams[0].score)
        savedInstanceState.putInt(KEY_BScore, basketbolViewModel.teams[1].score)

    }

    companion object {
        fun newInstance(): main_fragment {
            return main_fragment()
        }
    }
}