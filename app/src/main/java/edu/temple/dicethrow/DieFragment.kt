package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    private val DIESIDE = "sidenumber"
    private var dieVal = 0
    private val dieValTag = "dieVal"

    private lateinit var dieTextView: TextView

    private var dieSides: Int = 20

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
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            throwDie()
        else {
            dieVal = savedInstanceState.getInt(dieValTag)
            dieTextView.text = dieVal.toString()
        }

        view.setOnClickListener{
            throwDie()
        }
    }

    fun throwDie() {
        dieVal = Random.nextInt(dieSides)+1
        dieTextView.text = dieVal.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(dieValTag, dieVal)
    }

    companion object {
        fun newInstance(dieSides: Int) : DieFragment {
            return DieFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIESIDE, dieSides)
                }
            }
        }
    }
}