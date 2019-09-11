package com.astudio.fragmentsandactivity


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 *Activities that contain this fragment must implement the
 * [AFragment.OnClickListener] interface
 * to handle interaction events.
 * Use the [AFragment.newInstance] factory method to
 * create an instance of this fragment.
 * [AFragment] contains Button for display [BFragment] in PORTRAIT configuration
 * and increment number of clicks Button.
 */
class AFragment : Fragment() {

    private var listener: OnClickListener? = null
    private var clickButton: Button? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListener) {
            listener = context
        } else {
            throw RuntimeException("Activity must implement OnClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButton = view.findViewById(R.id.buttonFragA)
    }

    override fun onStart() {
        super.onStart()
        clickButton?.setOnClickListener {
            listener?.onClickCounter()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object{
        val TAG = AFragment::class.java.name

        fun newInstance():AFragment{
            return AFragment()
        }
    }
}
