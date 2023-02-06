package com.example.innovecstask.presentation.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.innovecstask.common.utils.Time
import com.example.innovecstask.data.dto.ConfigResponse
import com.example.innovecstask.databinding.ActivityMainBinding
import com.example.innovecstask.domain.viewmodel.ConfigsViewModel


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val configsViewModel: ConfigsViewModel by viewModels { viewModelFactory }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            var num = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
            var cur: Cursor? = result.data?.data?.let { contentResolver.query(it, num, null, null, null) }
            if (cur?.moveToFirst()!!) {
                val intent = Intent(
                    Intent.ACTION_DIAL
                )
                intent.setData(Uri.parse("tel:" + cur.getString(0)))
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnAction.setOnClickListener {
            initializeAction()
        }
    }

    private fun initializeAction() {
        configsViewModel.configs.observe(this) { list ->
            try {
                val action = chooseAction(list)
                if (action != null) {
                    when(action.type) {
                        "animation" -> {
                            rotateButton()
                        }
                        "toast" -> {
                            Toast.makeText(this, "Action is Toast!", Toast.LENGTH_LONG).show()
                        }
                        "call" -> {
                            val intent = Intent(Intent.ACTION_PICK)
                            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
                            resultLauncher.launch(intent)
                        }
                        "notification" -> {
                            var intent = Intent(Intent.ACTION_PICK)
                            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
                            resultLauncher.launch(intent)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("ttt", e.message.toString())
            }
        }
        configsViewModel.loadConfigs()
    }

    private fun chooseAction(actions: List<ConfigResponse>): ConfigResponse? {
        val filteredMutableActions = actions.filter { conf ->
            conf.enabled
        }.filter {conf ->
            conf.validDays.contains(Time.getCurrentDayOfWeek())
        }.toMutableList()

        filteredMutableActions.sortBy {
            it.priority
        }

        if (filteredMutableActions.size >= 1) {
            return if (filteredMutableActions[0].type != "toast") {
                filteredMutableActions[0]
            } else {
                if (isNetworkAvailable()) {
                    filteredMutableActions[0]
                } else {
                    //If toast and no internet connection
                    if (filteredMutableActions.size > 1) {
                        filteredMutableActions[1]
                    } else {
                        Toast.makeText(this, "Oops.. Looks like all actions are not accessible", Toast.LENGTH_LONG).show()
                        null
                    }

                }
            }
        } else {
            Toast.makeText(this, "Oops.. Looks like all actions are accessible", Toast.LENGTH_LONG).show()
            return null
        }
    }

    private fun rotateButton() {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 1000
        rotate.interpolator = LinearInterpolator()

        binding.btnAction.startAnimation(rotate)
    }

    private fun isNetworkAvailable(): Boolean {
        var result = false
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}