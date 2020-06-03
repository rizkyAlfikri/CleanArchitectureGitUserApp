package com.alfikri.rizky.cleanarchitecturegituserapp.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alfikri.rizky.cleanarchitecturegituserapp.R

/**
 * A simple [Fragment] subclass.
 */
class FollowingGitUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following_git_user, container, false)
    }

}
