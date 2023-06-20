package com.papirus.androidbase.core.data.mapper

import com.papirus.androidbase.core.model.remote.response.ErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper : ApiErrorModelMapper<ErrorResponse> {

    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorResponse {
        return ErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}
