package com.knotes.app.data.api

import android.util.Log
import com.google.gson.Gson
import com.knotes.app.BuildConfig
import com.knotes.app.data.models.CreateNoteModel
import com.knotes.app.data.models.Note
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface NotesService {

    @GET("0zvYNa/knotes")
    fun list(): Call<List<Note>>

    @POST("0zvYNa/knotes")
    fun create(@Body body: CreateNoteModel): Call<Note>

    companion object {
        private  var TAG = this::class.java.name

        fun instance(): NotesService {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
//                        .addHeader("Authorization", UUID.randomUUID().toString())
                        .build()

                    Log.d(TAG,"REQUEST METHOD: ${newRequest.method} ${newRequest.url} \n ${newRequest.body}")
                    val response = chain.proceed(newRequest)
                    Log.d(TAG,"RESPONSE CODE: ${response.code} \n RESPONSE BODY: ${Gson().toJson(response.body)}")
                    response
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()


            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NotesService::class.java)
        }
    }

}