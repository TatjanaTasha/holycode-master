package com.holycode.github.core

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(),
    BaseView {

    abstract val presenter: BasePresenter

    override fun onDestroy() {
        presenter.onDestory()
        super.onDestroy()
    }

    override fun showError(string: String) {
        Log.e("Error", string)
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show()
    }
}