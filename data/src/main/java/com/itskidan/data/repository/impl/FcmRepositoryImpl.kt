package com.itskidan.data.repository.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.itskidan.data.util.Constants
import com.itskidan.domain.repository.FcmRepository
import javax.inject.Inject

class FcmRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FcmRepository {
    override suspend fun saveFcmToken(token: String) {
        sharedPreferences.edit {
            putString(Constants.FCM_TOKEN_KEY, token)
        }
    }

    override suspend fun getFcmToken(): String? {
        return sharedPreferences.getString(Constants.FCM_TOKEN_KEY, null)
    }
}