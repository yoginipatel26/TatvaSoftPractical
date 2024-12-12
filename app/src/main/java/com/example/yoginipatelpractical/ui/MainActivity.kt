package com.example.yoginipatelpractical.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yoginipatelpractical.R
import com.example.yoginipatelpractical.data.UserAdapter
import com.example.yoginipatelpractical.data.UserAdapter.OnSaveImageClickListener
import com.example.yoginipatelpractical.databinding.ActivityMainBinding
import com.example.yoginipatelpractical.models.Item
import com.example.yoginipatelpractical.viewmodel.MainActivityVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , OnSaveImageClickListener{
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainActivityVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[MainActivityVM ::class.java]

        viewModel.getUsersData(1,10,"")
        if(checkForInternet(this)){
            viewModel.observeUserLiveData().observe(this, Observer { userList ->
                userAdapter.setUserList(userList.items)
            })
        }else{
            viewModel.userListFromDB.observe(this, Observer { userList ->
                userAdapter.setUserList(userList as ArrayList<Item>)
            })

            if(userAdapter.itemCount == 0){
                Toast.makeText(this, "No bookmarks available", Toast.LENGTH_SHORT).show()
            }
        }

        binding.srRefresh.setOnRefreshListener {
            refreshData()
        }

    }

    private fun refreshData() {
        if(checkForInternet(this)){
            viewModel.observeUserLiveData().observe(this, Observer { userList ->
                userAdapter.setUserList(userList.items)
            })
        }else{
            viewModel.userListFromDB.observe(this, Observer { userList ->
                userAdapter.setUserList(userList as ArrayList<Item>)
            })

            if(userAdapter.itemCount == 0){
                Toast.makeText(this, "No bookmarks available", Toast.LENGTH_SHORT).show()
            }
        }
        binding.srRefresh.isRefreshing = false
    }

    private fun setUpRecyclerView() {

        userAdapter = UserAdapter()
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }





    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


    override fun onSaveImageClick(position: Int, user: Item) {
        lifecycleScope.launch {
            viewModel.saveUserData(user)
        }
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
            Log.d("Save", "Save Click---->")


    }


}