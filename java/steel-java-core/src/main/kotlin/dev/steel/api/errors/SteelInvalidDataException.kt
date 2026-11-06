package dev.steel.api.errors

class SteelInvalidDataException
@JvmOverloads
constructor(message: String? = null, cause: Throwable? = null) : SteelException(message, cause)
