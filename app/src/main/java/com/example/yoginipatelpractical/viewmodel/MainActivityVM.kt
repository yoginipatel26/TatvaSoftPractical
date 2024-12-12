package com.example.yoginipatelpractical.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.yoginipatelpractical.api.RetrofitInstance
import com.example.yoginipatelpractical.api.UserInterface
import com.example.yoginipatelpractical.database.UserDao
import com.example.yoginipatelpractical.models.Item
import com.example.yoginipatelpractical.models.UserResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(application: Application, val userDao: UserDao, val userInterface: UserInterface)  : AndroidViewModel(application){

    private var userLiveData = MutableLiveData<UserResponseModel>()

    val  userListFromDB : LiveData<List<Item>> = userDao.getAllUSersFromDB()

    fun getUsersData(page: Int, pageSize: Int, sort : String) {
        userInterface.getUsers(page = page, pageSize = pageSize, sort= sort).enqueue(object  : Callback<UserResponseModel>{
            override fun onResponse(call: Call<UserResponseModel>, response: Response<UserResponseModel>) {
                if (response.body()!=null){
                    userLiveData.value = response.body()!!
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<UserResponseModel>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeUserLiveData() : LiveData<UserResponseModel> {
        return userLiveData
    }
//    fun observeUserLiveDataFromDB() : LiveData<UserResponseModel> {
//        return userListFromDB
//    }

    fun saveUserData(user : Item){
        viewModelScope.launch (Dispatchers.IO){
            userDao.insertUser(user)
        }
    }


}