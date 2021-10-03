package com.example.basketbol1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

private const val TAG = "Main_Fragment"
private const val KEY_AScore = "AScore"
private const val KEY_BScore = "BScore"
private const val ARG_BBGAME_ID = "bbgame_id"
private const val KEY_AName = "TeamA"
private const val KEY_BName = "TeamB"
private const val REQUEST_CODE_CLICKED = 0
private const val REQUEST_PHOTOA = 2
private const val REQUEST_PHOTOB = 3
class main_fragment : Fragment() {
    private lateinit var photoAButton : ImageButton
    private lateinit var photoAView : ImageView
    private lateinit var photoBButton : ImageButton
    private lateinit var photoBView : ImageView
    private lateinit var photoAFile : File
    private lateinit var photoBFile : File
    private lateinit var photoAUri : Uri
    private lateinit var photoBUri : Uri
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
    private lateinit var basketbolIcon : ImageView


    private val basketbolViewModel: BasketbolViewModel by lazy {
        ViewModelProviders.of(this).get(BasketbolViewModel::class.java)
    }
    interface MainCallbacks {
        fun onDisplay(teamAWin: Boolean)
    }
    private var mainCallbacks: MainCallbacks? = null

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainCallbacks = context as MainCallbacks?
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
        basketbolIcon = view.findViewById(R.id.bbIcon) as ImageView
        photoAButton = view.findViewById(R.id.teamAUPButton) as ImageButton
        photoBButton = view.findViewById(R.id.teamBUPButton) as ImageButton
        photoAView = view.findViewById(R.id.teamAPhoto) as ImageView
        photoBView = view.findViewById(R.id.teamBPhoto) as ImageView

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
            val didAWin = (bbGame.teamAScore > bbGame.teamBScore)
            mainCallbacks?.onDisplay(didAWin)
        }

        saveButton.setOnClickListener {
            basketbolViewModel.saveBBGame(bbGame)
            !saveButton.isEnabled
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
                photoAFile = basketbolViewModel.getPhotoAFile(bbGame)
                photoAUri = FileProvider.getUriForFile(requireActivity(),
                "com.example.basketbol1.fileprovider",photoAFile)
                photoBFile = basketbolViewModel.getPhotoBFile(bbGame)
                photoBUri = FileProvider.getUriForFile(requireActivity(),
                    "com.example.basketbol1.fileprovider", photoBFile)
                updateUI()

            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().revokeUriPermission(photoAUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
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
            if(requestCode == REQUEST_PHOTOA) {
                requireActivity().revokeUriPermission(
                    photoAUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
                updateAPhotoView()
            }

            else if(requestCode == REQUEST_PHOTOB){
                requireActivity().revokeUriPermission(photoBUri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                updateBPhotoView()
            }



    }

    private fun updateUI() {
        textA.setText(bbGame.teamAName)
        textB.setText(bbGame.teamBName)
        textAPTS.setText(bbGame.teamAScore.toString())
        textBPTS.setText(bbGame.teamBScore.toString())
        //updateAPhotoView()
        //updateBPhotoView()
    }

    private fun updateAPhotoView() {
        if(photoAFile.exists()) {
            val bitmap = getScaledBitmap(photoAFile.path, requireActivity())
            val rotbitmap = orientBitmap(photoAFile.path, bitmap)
            photoAView.setImageBitmap(rotbitmap)

        } else {
            photoAView.setImageDrawable(null)
        }
    }

    private fun updateBPhotoView() {
        if(photoBFile.exists()) {
            val bitmap = getScaledBitmap(photoBFile.path, requireActivity())
            val rotbitmap = orientBitmap(photoBFile.path,bitmap)
            photoBView.setImageBitmap(rotbitmap)

        } else {
            photoBView.setImageDrawable(null)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
        val textAWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //space left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                bbGame.teamAName = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                //also blank
            }
        }
        textA.addTextChangedListener(textAWatcher)
        val textBWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //space left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                bbGame.teamBName = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                //also blank
            }
        }
        textB.addTextChangedListener(textBWatcher)

        photoAButton.apply {
            val packageManager: PackageManager =
                requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(
                    captureImage,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
            if (resolvedActivity == null) {
                isEnabled = false
            }
            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoAUri)
                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(
                        captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )
                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoAUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }

                startActivityForResult(captureImage, REQUEST_PHOTOA)
            }
        }

        photoBButton.apply {
            val packageManager: PackageManager =
                requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(
                    captureImage,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
            if (resolvedActivity == null) {
                isEnabled = false
            }
            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoBUri)
                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(
                        captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )
                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoBUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }

                startActivityForResult(captureImage, REQUEST_PHOTOB)
            }
        }
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