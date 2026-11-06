package dev.steel.api.errors

class SteelIoException
@JvmOverloads
constructor(message: String? = null, cause: Throwable? = null) : SteelException(message, cause)
