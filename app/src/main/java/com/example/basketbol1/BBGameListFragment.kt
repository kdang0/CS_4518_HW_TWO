package com.example.basketbol1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "BBGameListFragment"

class BBGameListFragment : Fragment() {
    /**
     * Required interface for hosting activities
     */
    interface Callbacks {
        fun onGameSelected(bbgameID: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var bbgameRecyclerView: RecyclerView
    private var adapter: BBGameAdapter? = BBGameAdapter(emptyList())
    private val bbgameViewModel: BBGameViewModel by lazy {

        ViewModelProviders.of(this).get(BBGameViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total games: ${bbgameViewModel.bbgames.size}")
    }
     */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        bbgameRecyclerView = view.findViewById(R.id.bbgame_recycler_view) as RecyclerView
        bbgameRecyclerView.layoutManager = LinearLayoutManager(context)
//        updateUI()
        bbgameRecyclerView.adapter = adapter
        return view
    }

    private fun updateUI(bbgames : List<BBGame>) {
//        val bbgames = bbgameViewModel.bbgames
        adapter = BBGameAdapter(bbgames)
        bbgameRecyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bbgameViewModel.BBGamesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                bbgames ->
                bbgames?.let {
                    Log.i(TAG, "Got games $" + bbgames.size)
                    updateUI(bbgames)
                }
            })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class BBGameHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var bbGame: BBGame
        private val teamsTextView: TextView = itemView.findViewById(R.id.game_teams)
        private val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        private val scoreTextView: TextView = itemView.findViewById(R.id.game_score)
        private val redFuego : ImageView = itemView.findViewById(R.id.red_fuego)
        private val blueFuego : ImageView = itemView.findViewById(R.id.blue_fuego)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(bbGame: BBGame) {
            this.bbGame = bbGame
            teamsTextView.text = bbGame.teamAName + " vs. " + bbGame.teamBName
            dateTextView.text = bbGame.date.toString()
            scoreTextView.text = bbGame.teamAScore.toString() + ":" + bbGame.teamBScore.toString()


            redFuego.visibility = if(bbGame.teamAScore > bbGame.teamBScore){
                View.VISIBLE
            } else {
                View.GONE
            }


        }

        override fun onClick(v: View) {
            callbacks?.onGameSelected(bbGame.id)
        }
    }

    private inner class BBGameAdapter(var bbgames: List<BBGame>) :
        RecyclerView.Adapter<BBGameHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BBGameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_bbgame, parent, false)
            return BBGameHolder(view)
        }

        override fun onBindViewHolder(holder: BBGameHolder, position: Int) {
            val bbgame = bbgames[position]
            holder.bind(bbgame)
        }

        override fun getItemCount(): Int {
            return bbgames.size
        }
    }

    companion object {
        fun newInstance(): BBGameListFragment {
            return BBGameListFragment()
        }
    }
}