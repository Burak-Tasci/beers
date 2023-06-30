package com.tsci.beers.data

import android.content.Context
import androidx.annotation.StringRes
import com.tsci.beers.R
import com.tsci.beers.util.LogHelper
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Created by tasci on 25.06.2023.
 */
class NetworkManager constructor(
    private val application: Context
) {

    private val ioDispatcher = Dispatchers.IO

    suspend fun <T> apiCall(
        request: suspend NetworkManager.() -> T
    ): Resource<T> {
        return withContext(ioDispatcher) {
            val result = CompletableDeferred<Resource<T>>()
            try {
                val response = request(this@NetworkManager)
                if (response == null) {
                    LogHelper.debug("Response is null", TAG)
                    result.complete(Resource.Error(stringResource(R.string.general_error_message)))
                } else {
                    result.complete(Resource.Success(response))
                }
            } catch (ioException: IOException) {
                LogHelper.debug("Network Connection Failed.", TAG)
                result.complete(
                    Resource.Error(
                        ServerErrorModel(message = stringResource(R.string.not_connected_try_again))
                    )
                )
            } catch (exception: Exception) {
                val errorMessage = exception.localizedMessage ?: exception.message
                ?: stringResource(R.string.general_error_message)
                result.complete(
                    Resource.Error(
                        ServerErrorModel(message = errorMessage)
                    )
                )
            }
            result.await()
        }
    }

    private fun stringResource(@StringRes id: Int) = application.getString(id)


    private companion object {
        const val TAG = "NetworkManager"
    }
}