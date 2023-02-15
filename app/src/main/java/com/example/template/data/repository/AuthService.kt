package com.example.template.data.repository



import com.example.template.model.CarDetailsResponse
import com.example.template.model.CarResponce
import com.example.template.model.LoginResponse
import com.example.template.model.MissingCarRequest
import com.example.template.model.MissingCarResponse
import com.example.template.model.VerifyOtp
import com.example.template.model.VerifyOtpResponse
import com.example.template.model.carRequest
import com.example.template.utils.constants.EndPoints
import retrofit2.Response
import retrofit2.http.*

interface AuthService {

    @GET(EndPoints.LOGIN)
    suspend fun logIn(): Response<LoginResponse>

    @POST(EndPoints.VERIFY)
    suspend fun verifyOtp(
        @Body verifyOtp: VerifyOtp
    ): Response<VerifyOtpResponse>

    @POST(EndPoints.GETCARS)
    suspend fun getCars(
        @Body carRequest: carRequest= carRequest("Muradnagar Police Station")
    ):Response<CarResponce>

    @GET("/car/getcar/{id}")
    suspend fun getCar(@Path("id") carNumber: String):Response<CarDetailsResponse>

    @POST("/car/save_Car")
    suspend fun saveCar(@Body car: MissingCarRequest): Response<MissingCarResponse>

}