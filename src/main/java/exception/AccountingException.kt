package ir.sls.stora.exception

import ir.sls.stora.exception.StoraExceptionCodes.*

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

