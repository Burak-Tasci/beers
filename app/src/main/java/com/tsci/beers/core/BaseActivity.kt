package com.tsci.beers.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by tasci on 19.06.2023.
 */
abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

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

    // The function to handle ui setup
    abstract fun setupUi()

    open fun preOnCreate() {

    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        preOnCreate()

        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }
}