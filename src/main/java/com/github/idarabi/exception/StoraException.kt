package com.github.idarabi.exception

open class StoraException : Exception {
    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}

enum class StoraExceptionCodes(val code: Int) {
    ASSEMBLER_QUEUE_FULL(1000),
    BUCKET_ALREADY_EXISTS(1001),
    BUCKET_NOT_FOUND(1002),
    UPLOAD_NOT_INITIALIZED(1003),
    KEY_NOT_FOUND(1004),
    KEY_NOT_FINISHED(1005),
    DUPLICATE_CHUNK(1006),
    FIRST_CHUNK_NOT_RECEIVED(1107),
    SOME_CHUNK_NOT_RECEIVED(1008),
    PERSIST_ABORTED(1009),
    DUPLICATE_OBJECT(1010),
    API_KEY_INVALID(1011),
    USER_NOT_FOUND(1014),
    INVALID_API_KEY(1015),
    USER_ALREADY_EXIST(1016),
    CAPACITY_EXCITED(1017),
    TRAFFIC_QUOTA_EXCITED(1018),
    DUPLICATE_USER(1019),
    INVALID_API(1020),
    INVALID_CHUNK_NUMBER(1021),
    INVALID_START_OFFSET(1022),
    INVALID_SIZE(1023),
}