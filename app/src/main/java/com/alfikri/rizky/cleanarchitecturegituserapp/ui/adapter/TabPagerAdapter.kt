package com.alfikri.rizky.cleanarchitecturegituserapp.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alfikri.rizky.cleanarchitecturegituserapp.R
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.follower.FollowerGitUserFragment
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.following.FollowingGitUserFragment

class TabPagerAdapter(context: Context, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles = listOf(
        context.getString(R.string.follower),
        context.getString(R.string.following)
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FollowerGitUserFragment()
            else -> FollowingGitUserFragment()
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}