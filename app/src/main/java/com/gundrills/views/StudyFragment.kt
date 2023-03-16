package com.gundrills.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gundrills.R


class StudyFragment : Fragment(),View.OnClickListener {

        private lateinit var card1:CardView
        private lateinit var card2:CardView
        private lateinit var card3:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_study, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card1 = view.findViewById(R.id.card1)
        card1.setOnClickListener(this)

        card2 = view.findViewById(R.id.card2)
        card2.setOnClickListener(this)

        card3 = view.findViewById(R.id.card3)
        card3.setOnClickListener(this)









    }


    override fun onClick(view: View?) {

        var bundle = Bundle()
        var key = "systemId"
        if(view != null) {
            when(view.id) {
                R.id.card1 -> {
                    bundle.putInt(key,60)
                }
                R.id.card2 -> {
                    bundle.putInt(key,81)
                }
                R.id.card3 -> {
                    bundle.putInt(key,120)
                }
            }
        }

        view?.findNavController()?.navigate(R.id.action_studyFragment_to_studyQuizCardFragment,bundle)

    }



}