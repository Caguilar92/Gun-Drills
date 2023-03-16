package com.gundrills.views


import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.github.barteksc.pdfviewer.PDFView
import com.gundrills.MainActivity
import com.gundrills.R
import com.gundrills.factory.StudyViewModelFactory
import com.gundrills.view_models.StudyViewModel


class StudyQuizCardFragment : Fragment(), View.OnClickListener {

    private lateinit var frontCard: TextView
    private lateinit var backCard: TextView
    private lateinit var rightArrow: TextView
    private lateinit var leftArrow: TextView
    private lateinit var questionNumber: TextView
    private var cardFront = true
    private lateinit var viewModel: StudyViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_studycards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var key = "systemId"
        var systemId = arguments?.getInt(key)

        viewModel = ViewModelProvider(this, StudyViewModelFactory(systemId!!))[StudyViewModel::class.java]


        frontCard = view.findViewById(R.id.frontCard)
        backCard = view.findViewById(R.id.backCard)
        questionNumber = view.findViewById(R.id.questionNumbers)
        frontCard.text = systemId.toString()
        backCard.isInvisible = true
        rightArrow = view.findViewById(R.id.rightArrow)
        leftArrow = view.findViewById(R.id.leftArrow)
        frontCard.setOnClickListener(this)
        backCard.setOnClickListener(this)
        rightArrow.setOnClickListener(this)
        leftArrow.setOnClickListener(this)


        viewModel.questionObj().observe(viewLifecycleOwner) {
            frontCard.text = "${it.question}"
            questionNumber.text = "${it.questionNumber} / ${viewModel.getQuestionListSize()}"

            var clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    var bundle = Bundle()
                    var pdfKey = "pdf"
                    var pageNumberKey = "page"

                    when(reference(it.reference)) {
                        getString(R.string.reference1) -> {
                            bundle.putString(pdfKey,"Mortars")
                            bundle.putInt(pageNumberKey,it.page)
                        }
                        getString(R.string.reference2) -> {
                            bundle.putString(pdfKey,"Mortar Fire Direction Procedures")
                            bundle.putInt(pageNumberKey,it.page)
                        }
                        getString(R.string.reference3) -> {
                            bundle.putString(pdfKey,"Tactical Employment Of Mortars")
                            bundle.putInt(pageNumberKey,it.page)
                        }

                    }


                    view.findNavController().navigate(R.id.action_studyQuizCardFragment_to_pdfFragment,bundle)


                }

            }

            var spannableString = SpannableString(reference(it.reference))
            spannableString.setSpan(
                clickableSpan,
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            backCard.text = TextUtils.concat("${it.answer}" + "\n" + "\n" + "Link: ", spannableString)
            backCard.movementMethod = LinkMovementMethod.getInstance()

        }


    }



    override fun onStart() {

        checkCardState(cardFront)
        super.onStart()
    }


    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.frontCard -> {
                    flipToBack(v)
                    flipToFront(backCard)
                    backCard.isInvisible = false
                    frontCard.isInvisible = true
                    cardFront = false


                }
                R.id.backCard -> {
                    flipToBack(v)
                    flipToFront(frontCard)
                    backCard.isInvisible = true
                    frontCard.isInvisible = false
                    cardFront = true
                }

                R.id.rightArrow -> {

                    slideLeft()
                    viewModel.nextQuestion()

                }
                R.id.leftArrow -> {

                    slideRight()
                    viewModel.previousQuestion()

                }
            }
        }
    }

    private fun checkCardState(cardFront:Boolean) {
        if(!cardFront) {
            flipToBack(frontCard)
            flipToFront(backCard)
            backCard.isInvisible = false
            frontCard.isInvisible = true
            this.cardFront = false
        }
    }


    private fun flipToBack(v: View) {

        (AnimatorInflater.loadAnimator(context, R.animator.front_to_back) as AnimatorSet).apply {
            setTarget(v)
            start()
        }


    }

    private fun flipToFront(v: View) {
        (AnimatorInflater.loadAnimator(context, R.animator.back_to_front) as AnimatorSet).apply {
            setTarget(v)
            start()
        }
    }

    private fun slideLeft() {
        if (cardFront) {
            YoYo.with(Techniques.SlideOutLeft)
                .duration(500)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideOutLeft)
                .duration(500)
                .playOn(backCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(backCard)
        } else {
            YoYo.with(Techniques.SlideOutLeft)
                .duration(500)
                .playOn(backCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(backCard)

            YoYo.with(Techniques.SlideOutLeft)
                .duration(500)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(frontCard)

            flipToBack(backCard)
            flipToFront(frontCard)
            backCard.isInvisible = true
            frontCard.isInvisible = false
            cardFront = true


        }


    }

    private fun slideRight() {
        if (cardFront) {
            YoYo.with(Techniques.SlideOutRight)
                .duration(500)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideOutRight)
                .duration(500)
                .playOn(backCard)

            YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(backCard)
        } else {
            YoYo.with(Techniques.SlideOutRight)
                .duration(500)
                .playOn(backCard)


            YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(backCard)

            YoYo.with(Techniques.SlideOutRight)
                .duration(500)
                .playOn(frontCard)


            YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(frontCard)

            flipToBack(backCard)
            flipToFront(frontCard)
            backCard.isInvisible = true
            frontCard.isInvisible = false
            cardFront = true


        }
    }

    private fun reference(referenceNum: Int): String {
        when (referenceNum) {
            1 -> {
                return getString(R.string.reference1)
            }
            2 -> {
                return getString(R.string.reference2)
            }
            else -> {
                return getString(R.string.reference3)
            }
        }
    }




}