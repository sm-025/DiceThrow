package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null ||
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(20))
                .add(R.id.fragmentContainerView2, DieFragment.newInstance(20))
                .commit()
        }

        findViewById<Button>(R.id.rollDiceButton).setOnClickListener{
            val fragManager = supportFragmentManager
            (fragManager.findFragmentById(R.id.fragmentContainerView2) as DieFragment).throwDie()
            (fragManager.findFragmentById(R.id.fragmentContainerView) as DieFragment).throwDie()
        }
    }
}