package com.example.accountbook.common.db
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
){
    fun update(){
        LiteOrm.liteOrm?.save(this)
    }
    fun delete(){
        LiteOrm.liteOrm?.delete(this)
    }
}