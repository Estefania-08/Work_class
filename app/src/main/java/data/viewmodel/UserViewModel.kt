package data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import data.model.UserModel
import data.network.RetrofitClient
import kotlinx.coroutines.launch

class UserViewModel:ViewModel(){
    val api = RetrofitClient.api

    fun loginApi(user_model: UserModel, onResult: (JsonObject?) -> Unit){
        viewModelScope.launch {
            try {
                val response = api.login(user_model)
                if(response.isSuccessful){
                    val jsonResponse = response.body()
                    Log.d("debug", response.body().toString())
                    onResult(jsonResponse)
                } else {
                    Log.d("debug", "Error: ${response.body()}")
                    onResult(null)
                }
            } catch (exception: Exception){
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }
    }
}