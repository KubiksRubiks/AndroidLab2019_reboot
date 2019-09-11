package com.astudio.fragmentsandactivity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
/**
 * A simple [Activity] subclass.
 * This class must implement the
 * [AFragment.OnClickListener] interface
 * to handle interaction events.
 * Activity contains [AFragment] and [BFragment]
 */
class MainActivity : AppCompatActivity(), OnClickListener {

    var counter = 0
    private val isPortrait
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counter = savedInstanceState?.getInt(COUNTER_KEY, 0) ?: 0

        if (isPortrait) {
            makePortraitView()
        } else {
            makeLandView()
        }
    }

    private fun makePortraitView(){
        val fragementB = supportFragmentManager.findFragmentByTag(FRB_TAG) as? BFragment
        supportFragmentManager.popBackStack()
        if (fragementB != null && counter != 0) {
            fragementB.arguments?.putInt(COUNTER_KEY, counter)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BFragment.newInstance(counter), FRB_TAG)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, AFragment())
                .commit()
        }
    }

    private fun makeLandView(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_a_container, AFragment())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_b_container, BFragment())
            .commit()

    }
    override fun onClickCounter(){
        counter++

        //remove last saved in backstack fragment to replace it with the new one
        val fragmentHolder = if (isPortrait) {
            R.id.fragment_container
        } else {
            R.id.fragment_b_container
        }
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(fragmentHolder, BFragment.newInstance(counter))
            .addToBackStack(null)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNTER_KEY, counter)
        super.onSaveInstanceState(outState)
    }

    companion object{
        const val COUNTER_KEY = "0000"
        private val FRB_TAG = "FragB"
    }

}
