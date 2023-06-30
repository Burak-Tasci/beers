package com.tsci.beers.features.beer_other_informations

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.text.HtmlCompat
import com.tsci.beers.R
import com.tsci.beers.core.BaseFragment
import com.tsci.beers.databinding.FragmentBeerOtherInformationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerOtherInformationsFragment :
    BaseFragment<FragmentBeerOtherInformationsBinding>(R.layout.fragment_beer_other_informations) {


    override val bindingInflater: (LayoutInflater) -> FragmentBeerOtherInformationsBinding
        get() = FragmentBeerOtherInformationsBinding::inflate

    override fun setupUi() {
        hideToolbar()
        initArguments()
    }

    private fun initArguments() {
        val content = requireArguments().getString(ARGUMENT_CONTENT, "")
        val isContentHtml = requireArguments().getBoolean(ARGUMENT_IS_CONTENT_HTML)
        binding.textViewContent.text = if (isContentHtml)
            HtmlCompat.fromHtml(content, 0)
        else
            content
    }

    companion object{

        private const val ARGUMENT_CONTENT = "argument-content"
        private const val ARGUMENT_IS_CONTENT_HTML = "argument-is-content-html"

        @JvmStatic
        fun newInstance(content: String, isContentHtml: Boolean = false): BeerOtherInformationsFragment{
            val args = Bundle()
            val fragment =  BeerOtherInformationsFragment().apply {
                args.putString(ARGUMENT_CONTENT, content)
                args.putBoolean(ARGUMENT_IS_CONTENT_HTML, isContentHtml)
                arguments = args
            }
            return fragment
        }
    }
}