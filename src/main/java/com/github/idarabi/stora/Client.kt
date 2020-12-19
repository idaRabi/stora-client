package com.github.idarabi.stora

import com.github.idarabi.stora.commons.*
import com.github.idarabi.stora.exception.ForbiddenOperationException
import com.github.idarabi.stora.exception.StoraValidationException
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response


open class Client(address: String) {
    private val restClient = RetrofitRestClient.getClient(address)

    private fun checkForExceptions(response: Response<*>,
                                   token: String,
                                   bucket: String = "",
                                   key: String = "",
                                   partNumber: Int? = null) {
        if (!response.isSuccessful) {
            val json = response.errorBody()?.string() ?: return
            if (json.isBlank()) return
            throw StoraValidationException.extractException(json, bucket, key, partNumber, token)
        }
    }

    fun register(email: String, username: String, password: String) {
        restClient.registerUser(RegisterRequest(email, username, password)).execute()
        //TODO by mahdi handle exception
    }

    fun login(username: String, password: String): LoginResponse? {
        return restClient.createAuthenticationToken(LoginRequest(username, password)).execute().body()
        //TODO by mahdi handle exception
    }

    fun abortMultipartUpload(token: String, bucket: String, key: String) {
        restClient.abortMultiPart(token, bucket, key).execute().let {
            checkForExceptions(it, token, bucket, key)
        }
    }

    fun completeMultipartUpload(token: String,
                                bucket: String,
                                key: String,
                                contentType: String): UploadCompleteResult {
        return restClient.completeMultiPart(token, bucket, key, contentType).execute().let {
            checkForExceptions(it, token, bucket, key)
            return@let it.body() ?: error("Body is null: $bucket : $key")
        }
    }

    // FIXME: 11/14/20 send range query param
    fun getObject(token: String, bucket: String, key: String, start: Long? = null, end: Long? = null): GetResult {
        if (start == null && end != null)
            throw IllegalArgumentException("start is null but end is not!")

        val header = start?.let { " bytes=$start-${end ?: ""}" }
        return restClient.getObject(token, bucket, key, header).execute().let {
            checkForExceptions(it, token, bucket, key)

            val contentType = it.headers().get("Content-Type")
            val contentLength = it.headers().get("Content-Length")?.toLongOrNull()
            val inputStream = it.body()?.byteStream()
                    ?: throw IllegalStateException("Body is null for bucket: $bucket, key: $key")
            return@let GetResult(contentType, inputStream, contentLength)
        }
    }

    fun getInProgressFiles(token: String, bucket: String): Set<String> {
        return restClient.getInProgressFiles(token, bucket).execute().let {
            checkForExceptions(it, token, bucket)
            return@let it.body()?.inProgressFiles ?: error("null getInProgressFiles body")
        }
    }

    fun listParts(token: String, bucket: String, key: String): Set<Int> {
        return restClient.listParts(token, bucket, key).execute().let {
            checkForExceptions(it, token, bucket, key)
            return@let it.body() ?: throw Exception("null listParts body")
        }
    }

    fun putObject(token: String, bucket: String, key: String, bytes: ByteArray, contentType: String) {
        val body = RequestBody.create(MediaType.get(contentType), bytes)
        restClient.putObject(token, bucket, key, body).execute().let {
            checkForExceptions(it, token, bucket, key)
        }
    }

    fun uploadPart(token: String, bucket: String, key: String, partNumber: Int, bytes: ByteArray) {
        val body = RequestBody.create(MediaType.get("application/octet-stream"), bytes)
        restClient.uploadPart(token, bucket, key, partNumber, body).execute().let {
            checkForExceptions(it, token, bucket, key, partNumber)
        }
    }

    fun createBucket(token: String, bucket: String) {
        restClient.createBucket(token, bucket).execute().let {
            checkForExceptions(it, token, bucket)
        }
    }

    fun deleteBucket(token: String, bucket: String) {
        restClient.deleteBucket(token, bucket).execute().let {
            checkForExceptions(it, token, bucket)
        }
    }

    fun removeObject(token: String, bucket: String, key: String) {
        restClient.removeObject(token, bucket, key).execute().let {
            checkForExceptions(it, token, bucket, key)
        }
    }

    fun setBucketQuota(token: String, bucket: String, capacity: Long = 100_000_000, trafficQuota: Long = 100_000_000) {
        restClient.setBucketQuota(token, bucket, SetQuotaRequest(capacity, trafficQuota)).execute().let {
            checkForExceptions(it, token, bucket)
        }
    }

    fun bucketExists(token: String, bucket: String): Boolean {
        return restClient.hasBucket(token, bucket).execute().let {
            checkForExceptions(it, token, bucket)
            when (it.code()) {
                200 -> return@let true
                404 -> return@let false
                401 -> throw ForbiddenOperationException(token)
                else -> throw Exception(
                        "invalid status code for bucketExists $bucket code: ${it.code()}")
            }
        }
    }

    fun clientToken(token: String): ClientToken {
        return restClient.clientToken(token).execute().let {
            checkForExceptions(it, token)
            return@let it.body() ?: throw Exception("null clientToken body")
        }
    }

    fun bucketMetrics(token: String, bucket: String): BucketMetricsResponse {
        return restClient.bucketMetrics(token, bucket).execute().let {
            checkForExceptions(it, token, bucket)
            return@let it.body() ?: throw Exception("null bucketMetrics body")
        }
    }

    fun listBucket(token: String): List<BucketMetricsResponse> {
        return restClient.listBucket(token).execute().let {
            checkForExceptions(it, token)
            return@let it.body() ?: throw Exception("null listBucket body")
        }
    }

    fun updateMeta(token: String, bucket: String, key: String,
                   customMetaMap: MutableMap<String, String>): UpdateMetaResult {
        var customMeta = ""
        customMetaMap.forEach { customMeta += it.key + "=" + it.value + "," }
        customMeta = customMeta.removeSuffix(",")
        val updateMetaRequest = UpdateMetaRequest(customMeta)
        return restClient.updateMeta(token, bucket, key, updateMetaRequest).execute().let {
            checkForExceptions(it, token)
            return@let it.body() ?: throw Exception("null updateMeta body")
        }
    }

    fun getMeta(token: String, bucket: String, key: String): MetaDto {
        return restClient.getMeta(token, bucket, key).execute().let {
            checkForExceptions(it, token)
            return@let it.body() ?: throw Exception("null getMeta body")
        }
    }
}
