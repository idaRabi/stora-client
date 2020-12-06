package ir.sls.stora.exception

import ir.sls.stora.exception.StoraExceptionCodes.*


class BucketAlreadyExistsException(bucket: String) :
        StoraValidationException("Bucket already exists: $bucket", 409, BUCKET_ALREADY_EXISTS.code)

class BucketNotFoundException(bucket: String) :
        StoraValidationException("Bucket not found: $bucket", 404, BUCKET_NOT_FOUND.code)

class UploadNotInitializeException(bucket: String, key: String) :
        StoraValidationException("Upload Not Initialize. bucket:$bucket key:$key",
                                 400,
                                 UPLOAD_NOT_INITIALIZED.code)

class KeyNotFoundException(bucket: String, key: String) :
        StoraValidationException("Key not found. bucket:$bucket key:$key",
                                 404,
                                 KEY_NOT_FOUND.code)

class KeyNotFinishedException(bucket: String, key: String) :
        StoraValidationException("Key not finished. bucket:$bucket key:$key",
                                 400,
                                 KEY_NOT_FINISHED.code)

class DuplicateChunkException(bucket: String, key: String, chunkNumber: Int) :
        StoraValidationException("Chunk already received.  bucket:$bucket key:$key chunkNumber:$chunkNumber",
                                 409,
                                 DUPLICATE_CHUNK.code)

class FirstChunkNotReceivedException(bucket: String, key: String) :
        StoraValidationException("First chunk is not received yet. bucket:$bucket key:$key.",
                                 400,
                                 FIRST_CHUNK_NOT_RECEIVED.code)

class SomeChunkNotReceivedException(bucket: String, key: String) :
        StoraValidationException(
                "We have segmentation in received chunks with bucket:$bucket key:$key",
                400,
                SOME_CHUNK_NOT_RECEIVED.code)


class PersistAbortException(exception: Exception, bucket: String, key: String) :
        StoraValidationException("Persist aborted. bucket:$bucket key:$key",
                                 500,
                                 PERSIST_ABORTED.code,
                                 exception)

class DuplicateKeyException(bucket: String, key: String) :
        StoraValidationException("Duplicate key. bucket:$bucket key:$key",
                                 409,
                                 DUPLICATE_OBJECT.code)

class InvalidStartOffsetException(bucket:String, key:String) :
        StoraValidationException("Invalid startOffset in Range-Request bucket:$bucket key:$key ",
                                 416,
                                 INVALID_START_OFFSET.code)

class InvalidSizeException(bucket:String, key:String) :
        StoraValidationException("Invalid size in Range-request for bucket:$bucket key:$key ",
                                 416,
                                 INVALID_SIZE.code)

class InvalidApiKeyException(apiKey: String) :
        StoraValidationException("Invalid API key:$apiKey",
                                 401,
                                 API_KEY_INVALID.code)

class InvalidChunkNumber(bucket: String, key: String, chunkNumber: Int) :
        StoraValidationException(
                "Chunk number: $chunkNumber must be greater than zero. bucket:$bucket key:$key",
                400,
                INVALID_CHUNK_NUMBER.code)

