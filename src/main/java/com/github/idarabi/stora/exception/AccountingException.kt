package com.github.idarabi.stora.exception

import com.github.idarabi.stora.exception.StoraExceptionCodes.*

// TODO refactor exception hierarchy
class CapacityExcitedException(bucket: String) :
        StoraValidationException("Bucket : $bucket excited it't capacity limit",
                                 402,
                                 CAPACITY_EXCITED.code)

class TrafficQuotaExcitedException(bucket: String) :
        StoraValidationException("Bucket : $bucket excited it't traffic limit",
                                 402,
                                 TRAFFIC_QUOTA_EXCITED.code)

class UserNotFoundException(username: String) :
        StoraValidationException("User with username : $username doesn't exist",
                                 404,
                                 USER_NOT_FOUND.code)

class ApiKeyInvalidException(apiKey: String) :
        StoraValidationException("Api key: $apiKey is invalid", 403, INVALID_API_KEY.code)

class UserAlreadyExistsException(username: String) :
        StoraValidationException("User with username:$username already exists",
                                 409,
                                 USER_ALREADY_EXIST.code)

class UnAuthorizedUserException(userId: Long) :
        StoraValidationException("UnAuthorized user. userId: $userId", 401, UNAUTHORIZED_USER.code)

class ForbiddenOperationException(token: String) :
        StoraValidationException("Forbidden operation with current token: $token",
                                 401,
                                 FORBIDDEN_OPERATION.code)

class ExpiredSuperiorTokenException(token: String) :
        StoraValidationException("Superior token has expired. token: $token",
                                 498,
                                 EXPIRED_SUPERIOR_TOKEN.code)

class ExpiredClientTokenException(token: String) :
        StoraValidationException("Client token has expired. token: $token",
                                 498,
                                 EXPIRED_CLIENT_TOKEN.code)
