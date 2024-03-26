package com.example.accountbook.common.db

import android.os.Parcel
import android.os.Parcelable
import com.example.accountbook.common.config.ConfigItem
import com.example.accountbook.common.config.ConfigUtils
import com.litesuits.orm.db.annotation.Column
import com.litesuits.orm.db.annotation.PrimaryKey
import com.litesuits.orm.db.annotation.Table
import com.litesuits.orm.db.enums.AssignType

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: 用户类
 */
@Table("user")
data class User(
    /**
     * 用户ID
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    var id: Long? = null,

    /**
     * 用户名
     */
    @Column("username")
    var username: String? = "",

    /**
     * 密码
     */
    @Column("password")
    var password: String? = "",

    /**
     * 邮箱
     */
    @Column("email")
    var email: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    fun update() {
        LiteOrm.liteOrm?.save(this)
    }

    fun delete() {
        LiteOrm.liteOrm?.delete(this)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        id?.let { parcel.writeLong(it) }
        username?.let { parcel.writeString(it) }
        password?.let { parcel.writeString(it) }
        email?.let { parcel.writeString(it) }
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

data class CatchUserConfig(private var key: String, private var defaultVal: User) :
    ConfigItem<User>("KEY_USER", defaultVal) {
    override var config: User
        get() {
            return ConfigUtils.getParcelable(key, User::class.java, defaultVal) ?: defaultVal
        }
        set(mValue) {
            ConfigUtils.putParcelable(key, mValue)
            mutableStateData.value = mValue
        }
}