package com.gundrills.views


import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
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

        /*
    if systemId value 60, expected flash card questions should be for the 60mm mortar.
    if  systemId value 81, expected flash card questions should be for the 81mm mortar.
    if systemId value 120, expected flash card questions should be for the 120mm mortar.

    this value is pass to the study view model which calls the Questions.class in order to generate questions
    for the respective mortar system.

   */

        viewModel = ViewModelProvider(this, StudyViewModelFactory(systemId!!))[StudyViewModel::class.java]


        frontCard = view.findViewById(R.id.frontCard)
        backCard = view.findViewById(R.id.backCard)
        questionNumber = view.findViewById(R.id.questionNumbers)
        /*
        * The flash cards are made up to two different textviews laid out one on top of the other. Initially when the app is loaded the front of the card
        * is visible and the back is not. The front of the card contains a question, the back of the answer contains the answer. When the card is tapped
        * it flips both cards over at the same time, as the card rotates the front becomes invisible and the back becomes visible. When back is visible to
        * the user the cardFront variable gets set to false, indicating that the front of the card is now flipped to the back.
        * */
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

            /*creates a clickable span that navigates to the proper reference and page where the answer can be found.
            * */
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
            /* underlines the link of the clickable span */
            var spannableString = SpannableString(reference(it.reference))
            spannableString.setSpan(
                clickableSpan,
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            backCard.text = TextUtils.concat("${it.answer}" + "\n" + "\n" + "Link: ", spannableString)
            /* sets movement method which allows navigation between links*/
            backCard.movementMethod = LinkMovementMethod.getInstance()

        }


    }





    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.frontCard -> {
                    flipToBack(v)//flip card front to back
                    flipToFront(backCard)//flip card back to front
                    backCard.isInvisible = false
                    frontCard.isInvisible = true
                    cardFront = false


                }
                R.id.backCard -> {
                    flipToBack(v)//flip the card over(card was facing front, flip to back)
                    flipToFront(frontCard)//font of the card was in the back, flip to front)
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
        var duration = 500L
        if (cardFront) {
            YoYo.with(Techniques.SlideOutLeft)
                .duration(duration)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(duration)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideOutLeft)
                .duration(duration)
                .playOn(backCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(duration)
                .playOn(backCard)
        } else {
            YoYo.with(Techniques.SlideOutLeft)
                .duration(duration)
                .playOn(backCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(duration)
                .playOn(backCard)

            YoYo.with(Techniques.SlideOutLeft)
                .duration(duration)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideInRight)
                .duration(duration)
                .playOn(frontCard)

            flipToBack(backCard)
            flipToFront(frontCard)
            backCard.isInvisible = true
            frontCard.isInvisible = false
            cardFront = true


        }


    }

    private fun slideRight() {
        var duration = 500L
        if (cardFront) {
            YoYo.with(Techniques.SlideOutRight)
                .duration(duration)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideInLeft)
                .duration(duration)
                .playOn(frontCard)

            YoYo.with(Techniques.SlideOutRight)
                .duration(duration)
                .playOn(backCard)

            YoYo.with(Techniques.SlideInLeft)
                .duration(duration)
                .playOn(backCard)
        } else {
            YoYo.with(Techniques.SlideOutRight)
                .duration(duration)
                .playOn(backCard)


            YoYo.with(Techniques.SlideInLeft)
                .duration(duration)
                .playOn(backCard)

            YoYo.with(Techniques.SlideOutRight)
                .duration(duration)
                .playOn(frontCard)


            YoYo.with(Techniques.SlideInLeft)
                .duration(duration)
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