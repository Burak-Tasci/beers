package com.tsci.beers.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.tsci.beers.util.LogHelper

/**
 * The base class to use for dialog fragments. Requires a
 * DataBinding layout and a layoutId to provide.
 *
 * Also, the fragment may need to include @AndroidEntryPoint
 * if dependency injection is required.
 *
 * Create the fragment such as
 *
 *
 *      class LoadingFragment: BaseDialogFragment<DialogLoadingBinding>(R.layout.dialog_loading) {
 *           override fun setupUi() {
 *               // The code goes here
 *               binding.etc = x
 *           }
 *      }
 *
 * Note: While navigating, use the extension [navigate] so default
 * parameters can be sent & default animations can be applied
 * easily.
 *
 * Note 2: The dialog is not full screen by default. To do that,
 * override [isFullScreenDialog] and return true.
 *
 * WARNING: If the fragment is destroyed (a.k.a onDestroyView
 * is called) then the binding will be null.
 *
 * Created by Burak Taşcı on 6.02.2023.
 */
abstract class BaseDialogFragment<T : ViewDataBinding>(private val layoutId: Int) :
    DialogFragment() {

    // The local _binding parameter which is only available
    // within after onCreateView and before onDestroyView.
    private var _binding: T? = null

    // The property to access within the fragments.
    protected val binding: T
        get() = _binding!!

    // The TAG value to use in logs.
    protected val TAG = javaClass.simpleName

    // Determines whether this fragment was recreated.
    private var isRecreated = false

    // The function to use the UI setup
    protected abstract fun setupUi()

    override fun isCancelable(): Boolean = true

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        with(dialog){
            setCancelable(isCancelable)
            setCanceledOnTouchOutside(isCancelable)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        return dialog
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogHelper.warning(tag = "*****", message =  "$TAG is showing")
        setupUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}