package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    var previousRoll = 0
    var currentRoll = 0

    lateinit var dieTextView: TextView

    var dieSides: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.run {
            currentRoll
        }

        if (savedInstanceState == null)
            throwDie()
        else {
            previousRoll = savedInstanceState.getInt("dieVal")
            dieTextView.text = previousRoll.toString()
        }

        view.setOnClickListener{
            throwDie()
        }
    }

    fun throwDie() {
        currentRoll = Random.nextInt(dieSides)+1
        dieTextView.text = currentRoll.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("dieVal", currentRoll)
    }

//    companion object {
//        fun newInstance(sides: Int) = DieFragment().apply {
//            arguments = Bundle().apply{
//                putInt(DIESIDE, dieSides)
//            }
//        }
//    }
}