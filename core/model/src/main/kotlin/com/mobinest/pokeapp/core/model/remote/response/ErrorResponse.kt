package com.mobinest.pokeapp.core.model.remote.response

import com.squareup.moshi.Json

/**
 * @param code A network response code.
 * @param message A network error message.
 */
data class ErrorResponse(
    @Json(name = "errorCode") val errorCode: Int? = null,
    @Json(name = "message") val message: String? = null,
    @Json(name = "errors") val errors: List<FieldError>? = null
){
    data class FieldError(
        @Json(name = "field") val field: String? = null,
        @Json(name = "message") val message: String? = null
    )
}
