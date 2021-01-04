package com.github.idarabi.stora.commons

class RestPaths {
    companion object {
        const val VERSION = "/v1"
        const val BUCKET_PATH_PARAM = "bucket"
        const val KEY_PATH_PARAM = "key"
        const val OFFSET_PATH_PARAM = "offset"
        const val SIZE_PATH_PARAM = "endOffset"
        const val PART_NUMBER_PATH_PARAM = "part"
        const val BUCKET_ID_PATH = "buckets/{bucket}"
        const val OBJECT_ID_PATH = "objects/{bucket}/{key}"
        const val PARTS_PATH = "objects/{bucket}/{key}/parts"
        const val PARTS_ID_PATH = "objects/{bucket}/{key}/parts/{part}"
        const val BUCKET_PATH_METRICS = "accounts/buckets/{bucket}"
        const val LIST_BUCKET_PATH_METRICS = "accounts/buckets/list"
        const val SET_QUOTA_PATH = "accounts/buckets/{bucket}/setquota"
        const val REGISTER_PATH = "register"
        const val AUTHENTICATION_PATH = "auth"
        const val CLIENT_TOKEN_PATH = "auth/client"
        const val META_ID_PATH = "metas/{bucket}/{key}"
        const val OBJECT_LIST_ID_PATH = "buckets/objects/{bucket}"
    }
}