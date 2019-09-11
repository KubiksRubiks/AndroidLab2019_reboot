package com.astudio.fragmentsandactivity


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astudio.fragmentsandactivity.MainActivity.Companion.COUNTER_KEY
import kotlinx.android.synthetic.main.fragment_b.*

/**
 * A simple [Fragment] subclass.
 * Use the [BFragment.newInstance] factory method to
 * create an instance of this fragment.
 * [BFragment] contains TextView that display numbers of Button from [AFragment] clicks.
 */
class BFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onStart() {
        super.onStart()
        val bundle = arguments
        if (bundle != null) {
            val counter = bundle.getInt(COUNTER_KEY)
            setCounter(counter.toString())
        }
    }

    fun setCounter(counter: String) {
        textViewFragB.setText(counter)
    }

    companion object{
        val TAG = BFragment::class.java.name

        fun newInstance(clickCounter: Int):BFragment{
            val bundle = Bundle()
            val fragment = BFragment()
            bundle.putInt(COUNTER_KEY, clickCounter)
            fragment.arguments = bundle
            return fragment
        }
    }
}
