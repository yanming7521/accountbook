package com.example.accountbook.common.config

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.GsonUtils
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV

/**
 * MMKV使用封装
 */
object ConfigUtils {

    /**
     * 初始化
     */
    fun initMMKV(context: Context): String? = MMKV.initialize(context)

    /**
     * 保存数据（简化）
     * 根据value类型自动匹配需要执行的方法
     */
    fun put(key: String, value: Any) = when (value) {
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
        is Double -> putDouble(key, value)
        is String -> putString(key, value)
        is Boolean -> putBoolean(key, value)
        else -> false
    }

    fun putString(key: String, value: String): Boolean = MMKV.defaultMMKV().encode(key, value)

    fun getString(key: String, defValue: String): String =
        MMKV.defaultMMKV().decodeString(key, defValue) ?: defValue

    fun putInt(key: String, value: Int): Boolean = MMKV.defaultMMKV().encode(key, value)

    fun getInt(key: String, defValue: Int): Int = MMKV.defaultMMKV().decodeInt(key, defValue)

    fun putLong(key: String, value: Long): Boolean = MMKV.defaultMMKV().encode(key, value)

    fun getLong(key: String, defValue: Long): Long = MMKV.defaultMMKV().decodeLong(key, defValue)

    fun putDouble(key: String, value: Double): Boolean = MMKV.defaultMMKV().encode(key, value)

    fun getDouble(key: String, defValue: Double): Double =
        MMKV.defaultMMKV().decodeDouble(key, defValue)

    fun putFloat(key: String, value: Float): Boolean = MMKV.defaultMMKV().encode(key, value)

    fun getFloat(key: String, defValue: Float): Float =
        MMKV.defaultMMKV().decodeFloat(key, defValue)

    fun putBoolean(key: String, value: Boolean): Boolean = MMKV.defaultMMKV().encode(key, value)

    fun getBoolean(key: String, defValue: Boolean): Boolean =
        MMKV.defaultMMKV().decodeBool(key, defValue)

    fun <T : Parcelable> getParcelable(key: String, serviceClass: Class<T>, defValue: T?): T? =
        MMKV.defaultMMKV().decodeParcelable(key, serviceClass, defValue)

    fun <T : Parcelable> putParcelable(key: String, value: T): Boolean =
        MMKV.defaultMMKV().encode(key, value)

    fun contains(key: String): Boolean = MMKV.defaultMMKV().contains(key)

    fun removeValueForKey(key: String) = MMKV.defaultMMKV().removeValueForKey(key)

}

abstract class ConfigItem<T>(private var key: String, private var defaultVal: T) {
    abstract var config: T
    open fun resetToDefault() {
        ConfigUtils.removeValueForKey(key)
        liveData.postValue(config)
    }

    var liveData: MutableLiveData<T> = MutableLiveData()
        get() {
            if (field.value == null) {
                field.postValue(config)
            }
            return field
        }
}

data class StringConfig(
    private var key: String, private var defaultVal: String
) : ConfigItem<String>(key, defaultVal) {
    override var config: String
        get() {
            return ConfigUtils.getString(key, defaultVal)
        }
        set(_value) {
            ConfigUtils.putString(key, _value)
            liveData.postValue(_value)
        }

    override fun toString(): String {
        return "'$key'=$config"
    }
}

data class BooleanConfig(
    var key: String, var defaultVal: Boolean
) : ConfigItem<Boolean>(key, defaultVal) {
    override var config: Boolean
        get() {
            return ConfigUtils.getBoolean(key, defaultVal)
        }
        set(_value) {
            ConfigUtils.putBoolean(key, _value)
            liveData.postValue(_value)
        }

    override fun toString(): String {
        return "'$key'=$config"
    }
}

data class IntConfig(
    var key: String, var defaultVal: Int
) : ConfigItem<Int>(key, defaultVal) {
    override var config: Int
        get() {
            return ConfigUtils.getInt(key, defaultVal)
        }
        set(_value) {
            ConfigUtils.putInt(key, _value)
            liveData.postValue(_value)
        }

    override fun toString(): String {
        return "'$key'=$config"
    }
}


data class LongConfig(
    var key: String, var defaultVal: Long
) : ConfigItem<Long>(key, defaultVal) {
    override var config: Long
        get() {
            return ConfigUtils.getLong(key, defaultVal)
        }
        set(_value) {
            ConfigUtils.putLong(key, _value)
            liveData.postValue(_value)
        }

    override fun toString(): String {
        return "'$key'=$config"
    }
}

data class DoubleConfig(
    var key: String, var defaultVal: Double
) : ConfigItem<Double>(key, defaultVal) {
    override var config: Double
        get() {
            return ConfigUtils.getDouble(key, defaultVal)
        }
        set(_value) {
            ConfigUtils.putDouble(key, _value)
            liveData.postValue(_value)
        }

    override fun toString(): String {
        return "'$key'=$config"
    }
}

data class FloatConfig(
    var key: String, var defaultVal: Float
) : ConfigItem<Float>(key, defaultVal) {
    override var config: Float
        get() {
            return ConfigUtils.getFloat(key, defaultVal)
        }
        set(_value) {
            ConfigUtils.putFloat(key, _value)
            liveData.postValue(_value)
        }


    override fun toString(): String {
        return "'$key'=$config"
    }
}


data class FloatListConfig(
    var key: String, var defaultValue: MutableList<Float>
) : ConfigItem<MutableList<Float>>(key, defaultValue) {
    override var config: MutableList<Float>
        get() {
            return getFloatList(key, defaultValue)
        }
        set(_value) {
            putFloatList(key, _value)
            liveData.postValue(_value)
        }


    private fun getFloatList(key: String, defaultVal: MutableList<Float>): MutableList<Float> {
        val str = ConfigUtils.getString(key, GsonUtils.toJson(defaultVal))
        try {
            return GsonUtils.fromJson(str, GsonUtils.getListType(Float::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return defaultVal
    }

    private fun putFloatList(key: String, value: MutableList<Float>): Boolean {
        return ConfigUtils.putString(key, GsonUtils.toJson(value))
    }

}

data class MapConfig(var key: String, var defaultValue: MutableMap<Int, Int>) :
    ConfigItem<MutableMap<Int, Int>>(key, defaultValue) {
    override var config: MutableMap<Int, Int>
        get() = getMap(key, defaultValue)
        set(_value) {
            setMap(key, _value)
            liveData.postValue(_value)
        }

    private fun setMap(key: String, value: MutableMap<Int, Int>): Boolean {
        return ConfigUtils.putString(key, GsonUtils.toJson(value))
    }

    private fun getMap(key: String, defaultVal: MutableMap<Int, Int>): MutableMap<Int, Int> {
        val str = ConfigUtils.getString(key, GsonUtils.toJson(defaultVal))
        try {
            val type = object : TypeToken<Map<Int, Int>>() {}.type // 消除警告
            return GsonUtils.fromJson(str, type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return defaultVal
    }
}
