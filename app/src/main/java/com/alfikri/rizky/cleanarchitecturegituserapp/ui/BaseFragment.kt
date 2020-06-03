package com.alfikri.rizky.cleanarchitecturegituserapp.ui

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alfikri.rizky.cleanarchitecturegituserapp.di.HasComponent

open class BaseFragment : Fragment() {

    protected fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun <C> getComponent(componentType: Class<C>): C? {
        return componentType.cast((requireActivity() as HasComponent<C>).getComponent())
    }
}