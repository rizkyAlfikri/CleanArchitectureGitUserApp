package com.alfikri.rizky.data.cache

import android.content.Context
import android.util.Log
import com.alfikri.rizky.data.cache.serializer.Serializer
import com.alfikri.rizky.data.response.GitUserDetailResponse
import com.alfikri.rizky.data.exception.UserNotFoundException
import com.alfikri.rizky.domain.executor.ThreadExecutor
import io.reactivex.Observable
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitUserCacheImpl @Inject constructor(
    private val context: Context,
    private val serializer: Serializer,
    private val fileManager: FileManager,
    private val threadExecutor: ThreadExecutor
) : GitUserCache {

    private var cacheDir: File = context.cacheDir

    override fun get(username: String): Observable<GitUserDetailResponse> {
        return Observable.create { emitter ->
            val gitUserDetailEntityFile = this.buildFile(username)
            val fileContent = fileManager.readFileContent(gitUserDetailEntityFile)
            val gitUserDetailResponse: GitUserDetailResponse? =
                serializer.deserialize(fileContent, GitUserDetailResponse::class.java)

            if (gitUserDetailResponse != null) {
                emitter.onNext(gitUserDetailResponse)
                emitter.onComplete()
            } else {
                emitter.onError(UserNotFoundException())
            }
        }
    }

    override fun put(gitUserDetailResponse: GitUserDetailResponse?) {
        if (gitUserDetailResponse != null) {
            val gitUserEntityFile = this.buildFile(gitUserDetailResponse.login)
            if (!isCached(gitUserDetailResponse.login)) {
                val jsonString =
                    serializer.serialize(gitUserDetailResponse, GitUserDetailResponse::class.java)
                this.executeAsynchronously(CacheWrite(fileManager, gitUserEntityFile, jsonString))
                this.setLastCacheUpdateTimeMillis()
            }
        }
    }

    override fun isCached(username: String): Boolean {
        val gitUserDetailEntityFile = this.buildFile(username)
        return fileManager.isFileExists(gitUserDetailEntityFile)
    }

    override fun isExpire(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        val isExpire = ((currentTime - lastUpdateTime) > EXPIRATION_TIME)

        if (isExpire) {
            this.evictAll()
        }

        return isExpire
    }

    override fun evictAll() {
        this.executeAsynchronously(CacheEvictor(fileManager, cacheDir))
    }

    private fun buildFile(username: String): File {
        val fileNameBuilder = StringBuilder()
        fileNameBuilder.apply {
            append(cacheDir.path)
            append(File.separator)
            append(DEFAULT_FILE_NAME)
            append(username)
        }
        return File(fileNameBuilder.toString())
    }

    private fun setLastCacheUpdateTimeMillis() {
        val currentMillis = System.currentTimeMillis()
        fileManager.writeToPreferences(
            context,
            SETTINGS_FILE_NAME,
            SETTINGS_KEY_LAST_CACHE_UPDATE,
            currentMillis
        )
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return fileManager.getFromPreference(
            context,
            SETTINGS_FILE_NAME,
            SETTINGS_KEY_LAST_CACHE_UPDATE
        )
    }

    private fun executeAsynchronously(runnable: Runnable) {
        threadExecutor.execute(runnable)
    }

    companion object {
        private const val SETTINGS_FILE_NAME = "com.alfikri.rizky.dicoding.SETTINGS"
        private const val SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update"
        private const val DEFAULT_FILE_NAME = "git_user_"
        private const val EXPIRATION_TIME = 60 * 10 * 1000

        private class CacheWrite(
            private val fileManager: FileManager,
            private val fileToWrite: File,
            private val fileContent: String
        ) : Runnable {

            override fun run() {
                fileManager.writeToFile(fileToWrite, fileContent)
            }

        }

        private class CacheEvictor(
            private val fileManager: FileManager,
            private val cacheDir: File
        ) : Runnable {

            override fun run() {
                fileManager.clearDirectory(cacheDir)
            }
        }
    }

}