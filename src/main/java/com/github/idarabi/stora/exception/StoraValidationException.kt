package com.github.idarabi.stora.exception


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
open class StoraValidationException : StoraException {
    var statusCode: Int
    var errorCode: Int

    constructor() : super("") {
        statusCode = 0
        errorCode = 0
    }

    constructor(message: String, statusCode: Int, errorCode: Int) : super(message) {
        this.statusCode = statusCode
        this.errorCode = errorCode
    }

    constructor(message: String,
                statusCode: Int = 400,
                errorCode: Int = 400,
                cause: Throwable) : super(message, cause) {
        this.statusCode = statusCode
        this.errorCode = errorCode
    }

    companion object {
        private val objectMapper = ObjectMapper()
        fun extractException(json: String, bucket: String, key: String, partNumber: Int?):
                StoraValidationException {

            val exp = objectMapper.readValue(json, StoraValidationException::class.java)
            return when (exp.errorCode) {
                StoraExceptionCodes.BUCKET_ALREADY_EXISTS.code -> BucketAlreadyExistsException(bucket)
                StoraExceptionCodes.BUCKET_NOT_FOUND.code -> BucketNotFoundException(bucket)
                StoraExceptionCodes.UPLOAD_NOT_INITIALIZED.code -> UploadNotInitializeException(bucket, key)
                StoraExceptionCodes.KEY_NOT_FOUND.code -> KeyNotFoundException(bucket, key)
                StoraExceptionCodes.KEY_NOT_FINISHED.code -> KeyNotFinishedException(bucket, key)
                StoraExceptionCodes.DUPLICATE_CHUNK.code -> DuplicateChunkException(bucket, key, partNumber!!)
                StoraExceptionCodes.FIRST_CHUNK_NOT_RECEIVED.code -> FirstChunkNotReceivedException(bucket, key)
                StoraExceptionCodes.SOME_CHUNK_NOT_RECEIVED.code -> SomeChunkNotReceivedException(bucket, key)
                StoraExceptionCodes.PERSIST_ABORTED.code -> PersistAbortException(exp, bucket, key)
                StoraExceptionCodes.DUPLICATE_OBJECT.code -> DuplicateKeyException(bucket, key)
                StoraExceptionCodes.TRAFFIC_QUOTA_EXCITED.code -> TrafficQuotaExcitedException(bucket)
                StoraExceptionCodes.CAPACITY_EXCITED.code -> CapacityExcitedException(bucket)
                //TODO handle exception which they need userId,token,....
                else -> exp
            }
        }
    }
}


