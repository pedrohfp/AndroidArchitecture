package br.com.androidarchictecture.view.base

import android.content.Context
import android.content.DialogInterface
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by pedrohenrique on 13/07/17.
 */

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity() {
    protected var waitingView: BaseView<P>? = null
    var currentStackName: String? = null
        protected set

    protected fun onBeforeDoWait() {
        /* Empty */
    }

    protected fun onAfterDoWait() {
        /* Empty */
    }

    protected fun onBeforeUndoWait() {
        /* Empty */
    }

    protected fun onAfterUndoWait() {
        /* Empty */
    }

    @JvmOverloads fun doWait(hide: BaseView<out BasePresenter>, addToBackStack: Boolean = false, backStackName: String? = null) {
        onBeforeDoWait()

        if (addToBackStack) {
            supportFragmentManager
                    .beginTransaction()
                    .hide(hide as Fragment)
                    .show(waitingView as Fragment?)
                    .addToBackStack(backStackName)
                    .commitAllowingStateLoss()

            currentStackName = backStackName
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .hide(hide as Fragment)
                    .show(waitingView as Fragment?)
                    .commitAllowingStateLoss()
        }

        onAfterDoWait()
    }

    @JvmOverloads fun undoWait(show: BaseView<out BasePresenter>, addToBackStack: Boolean = false, backStackName: String? = null) {
        onBeforeUndoWait()

        if (addToBackStack) {
            supportFragmentManager
                    .beginTransaction()
                    .hide(waitingView as Fragment?)
                    .show(show as Fragment)
                    .addToBackStack(backStackName)
                    .commitAllowingStateLoss()

            currentStackName = backStackName
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .hide(waitingView as Fragment?)
                    .show(show as Fragment)
                    .commitAllowingStateLoss()
        }

        onAfterUndoWait()
    }

    fun showFailureDialog(titleResource: Int, messageResource: Int): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(messageResource).setTitle(titleResource)
        val dialog = builder.create()
        dialog.show()

        return dialog
    }

    fun showFailureDialog(titleResource: Int, message: String): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message).setTitle(titleResource)
        val dialog = builder.create()
        dialog.show()

        return dialog
    }

    fun showFailureDialog(titleResource: Int, message: Int, titleButton: Int, clickListener: DialogInterface.OnClickListener): AlertDialog {

        val builder = AlertDialog.Builder(this)
        builder.setMessage(message).setTitle(titleResource)
        builder.setPositiveButton(titleButton, clickListener)
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        return dialog
    }

    val lastBackStackEntryName: String
        get() {
            val index = supportFragmentManager.backStackEntryCount - 1
            val backEntry = supportFragmentManager.getBackStackEntryAt(index)
            return backEntry.name
        }

    fun updateToolbar(title: String?) {
        try {
            if (title != null) {
                supportActionBar!!.setTitle(title)
            } else {
                supportActionBar!!.setTitle("")
            }
        } catch (e: Exception) {
            // TODO: toolbar do not exist or not binded.
            e.printStackTrace()
        }

    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}

