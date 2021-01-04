package com.github.idarabi.stora.commons

import java.io.InputStream
import java.util.Collections.emptyList

private fun nowSeconds() = (System.currentTimeMillis() / 1000)

class BucketMetricsResponse(val bucket: String, val metrics: Metrics)

data class LoginRequest(val username: String, val password: String)

class LoginResponse(val token: String?, val message: String, val status: Int)

class RegisterRequest(val email: String, val username: String, val password: String)

class RegisterResponse(val username: String, val message: String)

data class SetQuotaRequest(val capacity: Long = -1, val trafficQuota: Long = -1)

data class SetQuotaResponse(val message: String?, val bucket: String?)

data class ObjectPutResult(val bucket: String,
                           val key: String,
                           val readBytes: Long,
                           val chunk: Int? = null,
                           val contentType: String? = null)


data class UploadCompleteResult(val bucket: String,
                                val key: String,
                                val md5sum: String? = null)

data class BucketResult(val bucket: String,
                        val inProgressFiles: Set<String> = emptySet(),
                        val time: Long = nowSeconds())

data class GetResult(val contentType: String? = null,
                     val inputStream: InputStream,
                     val length: Long? = null)

data class Metrics(var usage: Long = 0,
                   var download: Long = 0,
                   var upload: Long = 0,
                   var numberOfGet: Long = 0,
                   var numberOfPut: Long = 0,
                   var trafficQuota: Long = 0,
                   var numberOfFiles: Long = 0,
                   var capacity: Long = 0)

data class ClientToken(val token: String)

data class UpdateMetaRequest(val customMeta: String?)

data class UpdateMetaResult(val bucket: String, val key: String)

data class ListFiles(val bucket: String, val listFiles: List<String> = emptyList<String>(), val numberOfFiles: Long = 0)