package com.github.idarabi.stora.commons

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class MetaDto(val userBucketAndKey: UserIdBucketAndKey,
              val contentType: String? = null,
              val customMeta: MutableMap<String, String> = mutableMapOf()) : Serializable {

    var contentLength: Long = -1
        private set

    var createdDate: Long = 0
        private set

    var md5: String = ""
        private set

    var lastAccess: Long = 0
        private set

    fun withObjectSize(contentLength: Long): MetaDto {
        this.contentLength = contentLength
        return this
    }

    fun withCreatedDate(createdDate: Long): MetaDto {
        this.createdDate = createdDate
        return this
    }

    fun withMd5(md5: String): MetaDto {
        this.md5 = md5
        return this
    }

    fun withLastAccess(lastAccess: Long): MetaDto {
        this.lastAccess = lastAccess
        return this
    }
}