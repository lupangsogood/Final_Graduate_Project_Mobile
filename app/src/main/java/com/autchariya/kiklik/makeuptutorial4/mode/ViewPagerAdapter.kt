package com.autchariya.kiklik.makeuptutorial4.mode

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


 class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        if (position == 0) {
            return Mode1Fragment()
        } else if (position == 1) {
            return Mode2Fragment()
        } else if (position == 2) {
            return Mode3Fragment()
        } else if (position == 3) {
            return Mode4Fragment()
        }
        return null
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0) {
            return "Working"
        } else if (position == 1) {
            return "Party"
        } else if (position == 2) {
            return "Wedding"
        } else if (position == 3) {
            return "Exercise"
        }
        return null
    }

}