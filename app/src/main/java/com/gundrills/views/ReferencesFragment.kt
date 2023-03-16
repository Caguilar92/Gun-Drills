package com.gundrills.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gundrills.R


class ReferencesFragment : Fragment(),View.OnClickListener {
    private lateinit var mortarCard: CardView
    private lateinit var fdcCard: CardView
    private lateinit var tacEmployment: CardView
    private lateinit var rangerCard: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_references, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mortarCard = view.findViewById(R.id.mortars)
        fdcCard = view.findViewById(R.id.fdc)
        tacEmployment = view.findViewById(R.id.tac_emp)
        rangerCard = view.findViewById(R.id.ranger)

        mortarCard.setOnClickListener(this)
        fdcCard.setOnClickListener(this)
        tacEmployment.setOnClickListener(this)
        rangerCard.setOnClickListener(this)

    }



    override fun onClick( view: View?) {

        var bundle = Bundle()
        var key = "pdf"
        if(view != null) {
            when(view.id) {
                R.id.mortars -> {
                    bundle.putString(key,"Mortars")
                    view.findNavController()
                        .navigate(R.id.action_referencesFragment_to_pdfFragment,bundle)
                }
                R.id.fdc -> {
                    bundle.putString(key,"Mortar Fire Direction Procedures")
                    view.findNavController()
                        .navigate(R.id.action_referencesFragment_to_pdfFragment,bundle)
                }
                R.id.tac_emp -> {
                    bundle.putString(key,"Tactical Employment Of Mortars")
                    view.findNavController()
                        .navigate(R.id.action_referencesFragment_to_pdfFragment,bundle)
                }

                R.id.ranger -> {
                    bundle.putString(key,"Ranger")
                    view.findNavController().navigate(R.id.action_referencesFragment_to_pdfFragment,bundle)
                }
            }
        }
    }




}