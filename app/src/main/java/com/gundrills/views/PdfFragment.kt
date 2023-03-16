package com.gundrills.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gundrills.MainActivity
import com.gundrills.R


class PdfFragment : Fragment() {



    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var pdfView:PDFView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomNavigationView = (requireActivity() as MainActivity).findViewById(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.GONE
        return inflater.inflate(R.layout.fragment_pdf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            pdfView = view.findViewById(R.id.pdfView)
            Log.v("page","page is " + arguments?.getInt("page") )

            var page = arguments?.getInt("page")
            when (arguments?.getString("pdf")) {
                "Mortars" -> {
                    if (page != null) {
                        loadPage("Mortars.pdf",page)
                    }
                }
                "Mortar Fire Direction Procedures" -> {
                    if (page != null) {
                        loadPage("FDC.pdf",page)
                    }
                }
                "Tactical Employment Of Mortars" -> {
                    if (page != null) {
                        loadPage("tac_emply_mrtrs.pdf",page)
                    }
                }
                "Ranger" -> {
                    if(page != null) {
                        loadPage("Ranger.pdf",page)
                    }
                }


        }



    }

    private fun loadPage(pdf:String,page: Int) {
        pdfView.fromAsset(pdf)
            .defaultPage(page)
            .pageSnap(true)
            .autoSpacing(true)
            .pageFling(true)
            .scrollHandle(DefaultScrollHandle(requireContext()))
            .enableAntialiasing(true)
            .pageFitPolicy(FitPolicy.WIDTH)
            .load()
    }

    override fun onPause() {
        bottomNavigationView.visibility = View.VISIBLE
        arguments?.putString("pdf",arguments?.getString("pdf"))
        arguments?.putInt("page",pdfView.currentPage)
        super.onPause()
    }




}