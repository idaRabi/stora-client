package com.github.idarabi.stora

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.idarabi.stora.commons.*
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

val jacksonMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

interface RetrofitRestClient {

    @DELETE(RestPaths.PARTS_PATH)
    fun abortMultiPart(@Header("Authorization") token: String,
                       @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                       @Path(RestPaths.KEY_PATH_PARAM) key: String): Call<Unit>

    @POST(RestPaths.PARTS_PATH)
    fun completeMultiPart(@Header("Authorization") token: String,
                          @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                          @Path(RestPaths.KEY_PATH_PARAM) key: String,
                          @Header("Content-Type") contentType: String): Call<UploadCompleteResult>

    @GET(RestPaths.OBJECT_ID_PATH)
    fun getObject(@Header("Authorization") token: String,
                  @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                  @Path(RestPaths.KEY_PATH_PARAM) key: String,
                  @Header("Range") header: String?): Call<ResponseBody>

    @GET(RestPaths.BUCKET_ID_PATH)
    fun getInProgressFiles(@Header("Authorization") token: String,
                           @Path("bucket") bucket: String): Call<BucketResult>

    @GET(RestPaths.PARTS_PATH)
    fun listParts(@Header("Authorization") token: String,
                  @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                  @Path(RestPaths.KEY_PATH_PARAM) key: String): Call<Set<Int>>

    @POST(RestPaths.BUCKET_ID_PATH)
    fun createBucket(@Header("Authorization") token: String,
                     @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String): Call<Unit>

    @DELETE(RestPaths.BUCKET_ID_PATH)
    fun deleteBucket(@Header("Authorization") token: String,
                     @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String): Call<Unit>

    @HEAD(RestPaths.BUCKET_ID_PATH)
    fun hasBucket(@Header("Authorization") token: String,
                  @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String): Call<Void>

    @POST(RestPaths.OBJECT_ID_PATH)
    fun putObject(@Header("Authorization") token: String,
                  @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                  @Path(RestPaths.KEY_PATH_PARAM) key: String,
                  @Body body: RequestBody): Call<Unit>

    @PUT(RestPaths.PARTS_ID_PATH)
    fun uploadPart(@Header("Authorization") token: String,
                   @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                   @Path(RestPaths.KEY_PATH_PARAM) key: String,
                   @Path(RestPaths.PART_NUMBER_PATH_PARAM) partNumber: Int,
                   @Body body: RequestBody): Call<Unit>

    @DELETE(RestPaths.OBJECT_ID_PATH)
    fun removeObject(@Header("Authorization") token: String,
                     @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                     @Path(RestPaths.KEY_PATH_PARAM) key: String): Call<Unit>

    @POST(RestPaths.REGISTER_PATH)
    fun registerUser(@Body body: RegisterRequest): Call<RegisterResponse>

    @POST(RestPaths.AUTHENTICATION_PATH)
    fun createAuthenticationToken(@Body body: LoginRequest): Call<LoginResponse>

    @POST(RestPaths.SET_QUOTA_PATH)
    fun setBucketQuota(@Header("Authorization") token: String,
                       @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                       @Body body: SetQuotaRequest): Call<SetQuotaResponse>

    @POST(RestPaths.CLIENT_TOKEN_PATH)
    fun clientToken(@Header("Authorization") token: String): Call<ClientToken>

    @GET(RestPaths.BUCKET_PATH_METRICS)
    fun bucketMetrics(@Header("Authorization") token: String,
                      @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String): Call<BucketMetricsResponse>

    @GET(RestPaths.LIST_BUCKET_PATH_METRICS)
    fun listBucket(@Header("Authorization") token: String): Call<List<BucketMetricsResponse>>

    @PUT(RestPaths.META_ID_PATH)
    fun updateMeta(@Header("Authorization") token: String,
                   @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                   @Path(RestPaths.KEY_PATH_PARAM) key: String,
                   @Body body: UpdateMetaRequest): Call<UpdateMetaResult>

    @GET(RestPaths.META_ID_PATH)
    fun getMeta(@Header("Authorization") token: String,
                @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String,
                @Path(RestPaths.KEY_PATH_PARAM) key: String): Call<MetaDto>

    @GET(RestPaths.OBJECT_LIST_ID_PATH)
    fun getListFiles(@Header("Authorization") token: String,
                     @Path(RestPaths.BUCKET_PATH_PARAM) bucket: String): Call<ListFiles>

    companion object {
        fun getClient(address: String): RetrofitRestClient {
            val okHttpClient =
                    OkHttpClient.Builder()
                            .readTimeout(20, TimeUnit.SECONDS)
                            .connectTimeout(20, TimeUnit.SECONDS).build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(address + RestPaths.VERSION + "/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
                    .build()
            return retrofit.create(RetrofitRestClient::class.java)
        }
    }
}