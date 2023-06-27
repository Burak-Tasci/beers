package com.tsci.beers.data


/**
 * Created by tasci on 25.06.2023.
 */
sealed class Resource<out T> {

    data class Success<out T>(val body: T) : Resource<T>()

    data class Loading(val isLoading: Boolean) : Resource<Nothing>()

    data class Error(val error: ServerErrorModel) : Resource<Nothing>() {
        constructor(errorMessage: String) : this(ServerErrorModel(errorMessage))
    }

    object Empty : Resource<Nothing>()

    inline fun onSuccess(
        block: (T) -> Unit
    ): Resource<T> {
        return this.also {
            if (it is Success<T>)
                block(it.body)
        }
    }

    inline fun onError(
        block: (ServerErrorModel) -> Unit
    ): Resource<T> {
        return this.also {
            if (it is Error)
                block(it.error)
        }
    }

    inline fun onLoading(
        block: (Boolean) -> Unit
    ): Resource<T> {
        return this.also {
            if (it is Loading)
                block(it.isLoading)
        }
    }

    inline fun finally(
        block: () -> Unit
    ) {
        if (this is Error || this is Success)
            block()

    }

    suspend inline fun suspendOnSuccess(
        crossinline block: suspend (T) -> Unit
    ): Resource<T> {
        return this.also {
            if (it is Success)
                block(it.body)
        }
    }

    suspend inline fun suspendOnLoading(
        crossinline block: suspend () -> Unit
    ): Resource<T> {
        return this.also {
            if (it is Loading)
                block()
        }
    }

    suspend inline fun suspendOnError(
        crossinline block: suspend (ServerErrorModel) -> Unit
    ): Resource<T> {
        return this.also {
            if (it is Error)
                block(it.error)
        }
    }
}
