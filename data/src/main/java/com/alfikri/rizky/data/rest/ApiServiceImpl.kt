package com.alfikri.rizky.data.rest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.alfikri.rizky.data.exception.NetworkConnectionException
import com.alfikri.rizky.data.response.GitUserDetailResponse
import com.alfikri.rizky.data.response.GitUserFollowerResponse
import com.alfikri.rizky.data.response.GitUserFollowingResponse
import com.alfikri.rizky.data.response.GitUserSearchResponse
import com.alfikri.rizky.data.response.mapper.GitUserResJsonMapper
import io.reactivex.Observable
import java.net.MalformedURLException

class ApiServiceImpl(
    private val context: Context,
    private val gitUSerDataMapper: GitUserResJsonMapper
) : ApiService {

    private val BASE_URL = "https://api.github.com"

    override fun getSearchGitUser(username: String): Observable<GitUserSearchResponse> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    val responseGitUserSearch = getUserSearchFromApi(username)
                    if (responseGitUserSearch.isNotEmpty()) {
                        emitter.onNext(
                            gitUSerDataMapper.transformGitUserSearch(
                                responseGitUserSearch
                            )
                        )
                        emitter.onComplete()
                    } else {
                        emitter.onError(NetworkConnectionException())
                    }
                } catch (e: Exception) {
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    override fun getGitUserDetail(username: String): Observable<GitUserDetailResponse> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    val responseGitUserDetail = getUserDetailFromApi(username)
                    if (responseGitUserDetail.isNotEmpty()) {
                        emitter.onNext(
                            gitUSerDataMapper.transformGitUserDetail(
                                responseGitUserDetail
                            )
                        )
                        emitter.onComplete()
                    } else {
                        emitter.onError(NetworkConnectionException())
                    }
                } catch (e: Exception) {
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    override fun getGitUserFollowers(username: String): Observable<GitUserFollowerResponse> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    val responseGitFollower = getUserFollowerFromApi(username)
                    if (responseGitFollower.isNotEmpty()) {
                        emitter.onNext(
                            gitUSerDataMapper.transformGitUserFollower(responseGitFollower)
                        )
                        emitter.onComplete()
                    } else {
                        emitter.onError(NetworkConnectionException())
                    }
                } catch (e: Exception) {
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    override fun getGitUserFollowing(username: String): Observable<GitUserFollowingResponse> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    val responseGitFollowing = getUserFollowingFromApi(username)
                    if (responseGitFollowing.isEmpty()) {
                        emitter.onNext(
                            gitUSerDataMapper.transformGitUserFollowing(responseGitFollowing)
                        )
                        emitter.onComplete()
                    } else {
                        emitter.onError(NetworkConnectionException())
                    }
                } catch (e: Exception) {
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    @Throws(MalformedURLException::class)
    private fun getUserSearchFromApi(username: String): String {
        val apiUrl = "$BASE_URL/search/users?q=$username"
        return ApiConnection.createGET(apiUrl).requestSyncCall()
    }


    @Throws(MalformedURLException::class)
    private fun getUserDetailFromApi(username: String): String {
        val apiUrl = "$BASE_URL/users/$username"
        return ApiConnection.createGET(apiUrl).requestSyncCall()
    }


    @Throws(MalformedURLException::class)
    private fun getUserFollowerFromApi(username: String): String {
        val apiUrl = "$BASE_URL/users/$username/followers"
        return ApiConnection.createGET(apiUrl).requestSyncCall()
    }


    @Throws(MalformedURLException::class)
    private fun getUserFollowingFromApi(username: String): String {
        val apiUrl = "$BASE_URL/users/$username/followers"
        return ApiConnection.createGET(apiUrl).requestSyncCall()
    }


    //    override fun getSearchGitUser(username: String): Observable<GitUserSearchResponse> {
//        return Observable.merge(apiService.getSearchGitUser(username))
//
//    }
//
//    override fun getGitUserDetail(username: String): Observable<GitUserDetailResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getGitUserFollowers(username: String): Observable<GitUserFollowerResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getGitUserFollowing(username: String): Observable<GitUserFollowingResponse> {
//        TODO("Not yet implemented")
//    }
//
    private fun isThereInternetConnection(): Boolean {
        var isConnected = false

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkInfo = connectivityManager.activeNetwork ?: return false
            val actualNetwork =
                connectivityManager.getNetworkCapabilities(networkInfo) ?: return false

            return when {
                actualNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actualNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                actualNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                actualNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}