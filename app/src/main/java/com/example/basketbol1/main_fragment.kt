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
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import java.util.*

private const val TAG = "Main_Fragment"
private const val KEY_AScore = "AScore"
private const val KEY_BScore = "BScore"
private const val ARG_BBGAME_ID = "bbgame_id"
private const val KEY_AName = "TeamA"
private const val KEY_BName = "TeamB"
private const val REQUEST_CODE_CLICKED = 0

class main_fragment : Fragment() {
    private lateinit var bbGame: BBGame
    private lateinit var textA: EditText
    private lateinit var textAPTS: TextView
    private lateinit var textB: EditText
    private lateinit var textBPTS: TextView
    private lateinit var buttonAPT3: Button
    private lateinit var buttonAPT2: Button
    private lateinit var buttonAPT1: Button
    private lateinit var buttonBPT3: Button
    private lateinit var buttonBPT2: Button
    private lateinit var buttonBPT1: Button
    private lateinit var buttonReset: Button
    private lateinit var displayButton: Button
    private lateinit var saveButton: Button
    private lateinit var mContext: Context
    private val basketbolViewModel: BasketbolViewModel by lazy {
        ViewModelProviders.of(this).get(BasketbolViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        Log.d(TAG, "Got a BasketbolViewModel: $basketbolViewModel")
        val bbgameID: UUID = arguments?.getSerializable(ARG_BBGAME_ID) as UUID
        Log.d(TAG, "args bundle bbgame ID: $bbgameID")
        basketbolViewModel.loadBBGame(bbgameID)
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
        saveButton = view.findViewById(R.id.saveButton) as Button
        textA = view.findViewById(R.id.textA) as EditText
        textAPTS = view.findViewById(R.id.textAPTS) as TextView
        textB = view.findViewById(R.id.textB) as EditText
        textBPTS = view.findViewById(R.id.textBPTS) as TextView
        val aScore = 0
        basketbolViewModel.teams[0].score = aScore
        textAPTS.text = basketbolViewModel.teamAPoints.toString()
        val bScore = 0
        basketbolViewModel.teams[1].score = bScore
        textBPTS.text = basketbolViewModel.teamBPoints.toString()
        buttonAPT3.setOnClickListener {
            basketbolViewModel.updatePts("A", 3)
            bbGame.teamAScore += 3
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
        }
        buttonAPT2.setOnClickListener {
            basketbolViewModel.updatePts("A", 2)
            bbGame.teamAScore += 2
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
        }
        buttonAPT1.setOnClickListener {
            basketbolViewModel.updatePts("A", 1)
            bbGame.teamAScore += 1
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
        }
        buttonBPT3.setOnClickListener {
            basketbolViewModel.updatePts("B", 3)
            bbGame.teamBScore += 3
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
        }
        buttonBPT2.setOnClickListener {
            basketbolViewModel.updatePts("B", 2)
            bbGame.teamBScore += 2
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
        }
        buttonBPT1.setOnClickListener {
            basketbolViewModel.updatePts("B", 1)
            bbGame.teamBScore += 1
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
        }

        buttonReset.setOnClickListener {
            basketbolViewModel.reset()
            textAPTS.text = basketbolViewModel.teamAPoints.toString()
            textBPTS.text = basketbolViewModel.teamBPoints.toString()
            bbGame.teamAScore = 0
            bbGame.teamBScore = 0
            Log.d(TAG, "Reset done.")
        }

        displayButton.setOnClickListener {
            val isClicked = basketbolViewModel.butIsClicked
            val intent =
                context?.let { it1 -> BASKETBOL2.newIntent(it1, basketbolViewModel.butIsClicked) }
            startActivityForResult(intent, REQUEST_CODE_CLICKED)
        }

        saveButton.setOnClickListener {
            basketbolViewModel.saveBBGame(bbGame)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basketbolViewModel.bbGameLiveData.observe(viewLifecycleOwner, Observer {
            bbGame -> bbGame?.let {
                this.bbGame = bbGame
                basketbolViewModel.teamAPoints = bbGame.teamAScore
                basketbolViewModel.teamBPoints = bbGame.teamBScore
                updateUI()
            }
        })
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

    private fun updateUI() {
        textA.setText(bbGame.teamAName)
        textB.setText(bbGame.teamBName)
        textAPTS.setText(bbGame.teamAScore.toString())
        textBPTS.setText(bbGame.teamBScore.toString())
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
        basketbolViewModel.saveBBGame(bbGame)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSavedInstanceState")
        savedInstanceState.putInt(KEY_AScore, basketbolViewModel.teams[0].score)
        savedInstanceState.putInt(KEY_BScore, basketbolViewModel.teams[1].score)
        savedInstanceState.putString(KEY_AName, basketbolViewModel.teams[0].teamName)
        savedInstanceState.putString(KEY_BName, basketbolViewModel.teams[1].teamName)

    }




    companion object {
        fun newInstance(bbgameID: UUID): main_fragment {
            val args = Bundle().apply {
                putSerializable(ARG_BBGAME_ID, bbgameID)
            }
            return main_fragment().apply {
                arguments = args
            }
        }
    }
}