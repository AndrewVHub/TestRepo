package com.example.testrepo.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.testrepo.ui.activity.MainActivity
import timber.log.Timber

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(val inflate: Inflate<VB>) :
    Fragment(), LifecycleEventObserver {

    protected var _binding: VB? = null
    protected val binding get() = checkNotNull(_binding)

    private val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = _binding ?: inflate.invoke(inflater, container, false)
        return _binding?.root
    }

    open fun onSecondResume() {}

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Timber.tag(getFragmentTag()).i(event.name)

        if (event == Lifecycle.Event.ON_DESTROY) {
            _binding = null
        }
    }

    protected fun navigateSafety(destination: NavDirections) =
        findNavController().currentDestination?.getAction(destination.actionId)?.let {
            findNavController().navigate(destination)
        } ?: Timber.wtf("navigateSafety:: error destination ${destination.actionId}")

    protected fun popBackStack(unit: Unit) = findNavController().popBackStack()

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(this)
    }

    fun setUpStatusBarPadding(view: View) {
        mainActivity.setUpStatusBarPaddings(view)
    }

    protected fun getFragmentTag() = this@BaseFragment::class.simpleName ?: ""
}