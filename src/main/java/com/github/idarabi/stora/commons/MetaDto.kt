package com.github.idarabi.stora.commons

import java.io.*

class MetaDto(val userBucketAndKey: UserIdBucketAndKey,
              val contentType: String? = null,
              var md5: String? = null,
              val customMeta: MutableMap<String, String> = mutableMapOf(),
              val size: Long? = null,
              val createdDate: Long? = null,
              val lastAccess: Long = -1) : Serializable