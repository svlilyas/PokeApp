package com.mobinest.pokeapp.core.network.utils

import com.mobinest.pokeapp.core.model.extension.StringExtension.empty
import com.mobinest.pokeapp.core.model.remote.network.Resource
import com.mobinest.pokeapp.core.model.remote.response.ErrorResponse
import com.mobinest.pokeapp.core.model.utils.JsonSerializer.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

object NetworkHandler {
    inline fun <T> handleResponse(crossinline request: suspend () -> Response<T>): Flow<Resource<T>> =
        channelFlow {
            var response: Response<T>? = null
            try {
                response = request.invoke()

                if (response.isSuccessful) {
                    send(Resource.success(data = response.body()))
                } else {
                    throw HttpException(response)
                }
            } catch (e: UnknownHostException) {
                send(
                    response.defaultServerError()
                )
            } catch (e: HttpException) {
                send(
                    response.defaultServerError()
                )
            } catch (e: IllegalStateException) {
                send(
                    response.defaultServerError()
                )
            } catch (e: Exception) {
                send(
                    response.defaultServerError()
                )
            }
        }.flowOn(Dispatchers.IO)

    fun <T> Response<T>?.defaultServerError(): Resource<T> =
        Resource.error(
            error = this?.errorBody()?.string()?.toObject() ?: ErrorResponse(
                errorCode = this?.code() ?: -1,
                message = this?.errorBody()?.string() ?: String.empty
            )
        )
}