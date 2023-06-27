package com.tsci.beers.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tsci.beers.R
import com.tsci.beers.databinding.FragmentBaseBinding

/**
 * Created by tasci on 25.06.2023.
 */

abstract class BaseFragment<VB>(@LayoutRes private val contentLayoutId: Int) :
    Fragment(contentLayoutId) where VB : ViewDataBinding {

    private lateinit var baseBinding: FragmentBaseBinding

    // The local _binding parameter which is only available
    // within after onCreateView and before onDestroyView.
    private var _binding: VB? = null

    // The property to access within the fragments.
    protected val binding: VB
        get() = _binding!!

    // The TAG value to use in logs.
    @Suppress("PropertyName")
    protected val TAG: String = javaClass.simpleName

    // The inflater class for ViewBinding
    abstract val bindingInflater: (LayoutInflater) -> VB


    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_base, container, false)
        _binding = bindingInflater.invoke(layoutInflater)
        setupBinding()
        baseBinding.containerFrameLayout.setOnClickListener { }
        return baseBinding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupBinding() {
        baseBinding.contentViewActionBar.viewStub?.apply {
            layoutResource = contentLayoutId
            inflate()
        }
        baseBinding.contentViewActionBar.setContainingBinding(binding)
        _binding = baseBinding.contentViewActionBar.binding!! as VB
        binding.lifecycleOwner = viewLifecycleOwner
    }

    fun hideToolbar() {
        baseBinding.includeToolbar.toolbar.visibility = View.GONE
    }

    fun setToolbarTitle(@StringRes titleResId: Int) {
        baseBinding.includeToolbar.toolbar.title = getString(titleResId)
        baseBinding.includeToolbar.logoImageView.visibility = View.GONE
    }

    fun setToolbarTitle(title: String) {
        baseBinding.includeToolbar.toolbar.title = title
        baseBinding.includeToolbar.logoImageView.visibility = View.GONE
    }

    fun setToolbarSubtitle(subtitle: String) {
        baseBinding.includeToolbar.toolbar.subtitle = subtitle
        baseBinding.includeToolbar.logoImageView.visibility = View.GONE
    }

    fun showBackButton() {
        showToolbarNavigationIcon(R.drawable.ic_back)
    }

    fun showCloseButton() {
        showToolbarNavigationIcon(R.drawable.ic_close_vector)
    }

    private fun showToolbarNavigationIcon(@DrawableRes iconResId: Int) {
        baseBinding.includeToolbar.toolbar.apply {
            setNavigationIcon(iconResId)
            setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }

    abstract fun setupUi()

}
