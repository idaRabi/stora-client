package commons

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
        const val SET_QUOTA_PATH = "accounting/buckets/{bucket}/setquota"
        const val LIST_BUCKET_PATH_METRICS = "accounting/buckets/list"
        const val REGISTER_PATH = "register"
        const val AUTHENTICATION_PATH = "auth"
    }
}