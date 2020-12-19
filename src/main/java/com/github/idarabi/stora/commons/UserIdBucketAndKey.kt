package com.github.idarabi.stora.commons

import java.io.Serializable

class UserIdBucketAndKey(var userId: Long, var bucket: String, var key: String) :Serializable{
    override fun toString(): String {
        return "$userId:$bucket:$key"
    }
}
