package com.example.basketbol1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "BBGameListFragment"

class BBGameListFragment : Fragment() {
    private lateinit var bbgameRecyclerView: RecyclerView
    private var adapter: BBGameAdapter? = null
    private val bbgameViewModel: BBGameViewModel by lazy {

        ViewModelProviders.of(this).get(BBGameViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total games: ${bbgameViewModel.bbgames.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        bbgameRecyclerView = view.findViewById(R.id.bbgame_recycler_view) as RecyclerView
        bbgameRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }

    private fun updateUI() {
        val bbgames = bbgameViewModel.bbgames
        adapter = BBGameAdapter(bbgames)
        bbgameRecyclerView.adapter = adapter
    }

    private inner class BBGameHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var bbGame: BBGame
        private val teamsTextView: TextView = itemView.findViewById(R.id.game_teams)
        private val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        private val scoreTextView: TextView = itemView.findViewById(R.id.game_score)
        private val redFuego : ImageView = itemView.findViewById(R.id.red_fuego)
        private val blueFuego : ImageView = itemView.findViewById(R.id.blue_fuego)

        fun bind(bbGame: BBGame) {
            this.bbGame = bbGame
            teamsTextView.text = bbGame.team1.teamName + " vs. " + bbGame.team2.teamName
            dateTextView.text = bbGame.date.toString()
            scoreTextView.text = bbGame.team1.score.toString() + ":" + bbGame.team2.score.toString()


            redFuego.visibility = if(bbGame.team1.score > bbGame.team2.score){
                View.VISIBLE
            } else {
                View.GONE
            }


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