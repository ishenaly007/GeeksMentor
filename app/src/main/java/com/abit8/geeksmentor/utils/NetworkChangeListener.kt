package com.abit8.geeksmentor.utils

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.abit8.geeksmentor.R
import kotlin.system.exitProcess

class NetworkChangeListener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!Common.isConnectedToInternet(context)) {
            Dialog(context).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.no_internet_dialog)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                findViewById<View>(R.id.tvRetry).setOnClickListener {
                    onReceive(context, intent)
                    dismiss()
                }
                findViewById<View>(R.id.tvExit).setOnClickListener {
                    exitProcess(-1)
                }
                show()
            }
        }
    }
}

object Common {
    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}
//private val networkChangeListener: NetworkChangeListener by lazy {
//    NetworkChangeListener()
//}
//override fun onStart() {
//    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//    requireActivity().registerReceiver(networkChangeListener, filter)
//    super.onStart()
//}
//
//override fun onStop() {
//    requireActivity().unregisterReceiver(networkChangeListener)
//    super.onStop()
//}
//этот код надо будет добавить в BaseFragment

