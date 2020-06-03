package com.alfikri.rizky.data.cache.serializer

import com.google.gson.Gson
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Serializer @Inject constructor(){

    private val gson = Gson()

    fun <T> serialize(objects: T, clazz: Class<T>): String {
        return gson.toJson(objects, clazz)
    }

    fun <T> deserialize(reader: String, clazz: Class<T>): T {
        return gson.fromJson(reader, clazz)
    }
}