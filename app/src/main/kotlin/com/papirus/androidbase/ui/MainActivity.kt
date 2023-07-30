package com.papirus.androidbase.ui

import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.papirus.androidbase.R
import com.papirus.androidbase.core.network.utils.ConnectivityObserver
import com.papirus.androidbase.core.network.utils.NetworkConnectivityObserver
import com.papirus.androidbase.core.uicomponents.binding.BindingActivity
import com.papirus.androidbase.core.uicomponents.binding.ViewExtension.gone
import com.papirus.androidbase.core.uicomponents.binding.ViewExtension.visible
import com.papirus.androidbase.core.uicomponents.extensions.FlowExt.flowWithLifecycle
import com.papirus.androidbase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    @Inject
    lateinit var networkConnectivityObserver: NetworkConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        observeConnection()
    }

    private fun observeConnection() {
        flowWithLifecycle(flowData = networkConnectivityObserver.observe()) { networkStatus ->
            when (networkStatus) {
                ConnectivityObserver.ConnectionStatus.Available -> showOnlineMode()

                ConnectivityObserver.ConnectionStatus.Unavailable -> showOfflineMode()

                else -> showOfflineMode()
            }
        }
    }

    private suspend fun showOnlineMode() {
        withContext(Dispatchers.Main) {
            binding.tvAppMode.run {
                visible()
                text = getString(R.string.online_mode)
                setTextColor(getColor(R.color.grayishBrown))
                background =
                    AppCompatResources.getDrawable(this@MainActivity, R.drawable.bg_network_online)

                delay(5000)

                binding.tvAppMode.gone()
            }
        }
    }

    private suspend fun showOfflineMode() {
        withContext(Dispatchers.Main) {
            binding.tvAppMode.run {
                visible()
                text = getString(R.string.offline_mode)
                setTextColor(getColor(R.color.white))
                background =
                    AppCompatResources.getDrawable(this@MainActivity, R.drawable.bg_network_offline)
            }
        }
    }
}