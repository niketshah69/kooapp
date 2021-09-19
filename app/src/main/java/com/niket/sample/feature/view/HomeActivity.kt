package com.niket.sample.feature.view

import android.os.Bundle
import com.niket.sample.R
import com.niket.sample.base.view.BaseActivity

class HomeActivity : BaseActivity() {
    override fun getScreenName() = HomeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}