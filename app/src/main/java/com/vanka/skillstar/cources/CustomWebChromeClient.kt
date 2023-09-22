package com.vanka.skillstar.cources

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import com.vanka.skillstar.R


class CustomWebChromeClient(private val activity: Activity,frame:FrameLayout) : WebChromeClient() {
    private var customView: View? = null
    private var customViewCallback: WebChromeClient.CustomViewCallback? = null
    private var originalOrientation: Int = 0
    private val fullscreenContainer: FrameLayout = frame

    override fun onHideCustomView() {
        if (customView == null) {
            return
        }

        fullscreenContainer.removeView(customView)
        fullscreenContainer.visibility = View.GONE
        customView = null
        customViewCallback?.onCustomViewHidden()

        activity.requestedOrientation = originalOrientation

    }

    override fun onShowCustomView(view: View?, callback: WebChromeClient.CustomViewCallback?) {
        if (customView != null) {
            onHideCustomView()
            return
        }

        view?.let {
            customView = it
            originalOrientation = activity.requestedOrientation
            customViewCallback = callback

            fullscreenContainer.addView(customView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
            fullscreenContainer.visibility = View.VISIBLE
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}
