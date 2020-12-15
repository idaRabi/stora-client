package com.github.idarabi.stora.exception


class BucketAlreadyExistsException(bucket: String) :
        StoraValidationException("Bucket already exists: $bucket", 409, StoraExceptionCodes.BUCKET_ALREADY_EXISTS.code)

class BucketNotFoundException(bucket: String) :
        StoraValidationException("Bucket not found: $bucket", 404, StoraExceptionCodes.BUCKET_NOT_FOUND.code)

class UploadNotInitializeException(bucket: String, key: String) :
        StoraValidationException("Upload Not Initialize. bucket:$bucket key:$key",
                                 400,
                                 StoraExceptionCodes.UPLOAD_NOT_INITIALIZED.code)

class KeyNotFoundException(bucket: String, key: String) :
        StoraValidationException("Key not found. bucket:$bucket key:$key",
                                 404,
                                 StoraExceptionCodes.KEY_NOT_FOUND.code)

class KeyNotFinishedException(bucket: String, key: String) :
        StoraValidationException("Key not finished. bucket:$bucket key:$key",
                                 400,
                                 StoraExceptionCodes.KEY_NOT_FINISHED.code)

class DuplicateChunkException(bucket: String, key: String, chunkNumber: Int) :
        StoraValidationException("Chunk already received.  bucket:$bucket key:$key chunkNumber:$chunkNumber",
                                 409,
                                 StoraExceptionCodes.DUPLICATE_CHUNK.code)

class FirstChunkNotReceivedException(bucket: String, key: String) :
        StoraValidationException("First chunk is not received yet. bucket:$bucket key:$key.",
                                 400,
                                 StoraExceptionCodes.FIRST_CHUNK_NOT_RECEIVED.code)

class SomeChunkNotReceivedException(bucket: String, key: String) :
        StoraValidationException(
                "We have segmentation in received chunks with bucket:$bucket key:$key",
                400,
                StoraExceptionCodes.SOME_CHUNK_NOT_RECEIVED.code)


class PersistAbortException(exception: Exception, bucket: String, key: String) :
        StoraValidationException("Persist aborted. bucket:$bucket key:$key",
                                 500,
                                 StoraExceptionCodes.PERSIST_ABORTED.code,
                                 exception)

class DuplicateKeyException(bucket: String, key: String) :
        StoraValidationException("Duplicate key. bucket:$bucket key:$key",
                                 409,
                                 StoraExceptionCodes.DUPLICATE_OBJECT.code)

class InvalidStartOffsetException(bucket:String, key:String) :
        StoraValidationException("Invalid startOffset in Range-Request bucket:$bucket key:$key ",
                                 416,
                                 StoraExceptionCodes.INVALID_START_OFFSET.code)

class InvalidSizeException(bucket:String, key:String) :
        StoraValidationException("Invalid size in Range-request for bucket:$bucket key:$key ",
                                 416,
                                 StoraExceptionCodes.INVALID_SIZE.code)

class InvalidTokenException(apiKey: String) :
        StoraValidationException("Invalid API key:$apiKey",
                                 401,
                                 StoraExceptionCodes.TOKEN_INVALID.code)

class InvalidChunkNumber(bucket: String, key: String, chunkNumber: Int) :
        StoraValidationException(
                "Chunk number: $chunkNumber must be greater than zero. bucket:$bucket key:$key",
                400,
                StoraExceptionCodes.INVALID_CHUNK_NUMBER.code)

