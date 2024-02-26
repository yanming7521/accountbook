package com.example.accountbook.common.db
import com.litesuits.orm.db.annotation.Column
import com.litesuits.orm.db.annotation.PrimaryKey
import com.litesuits.orm.db.annotation.Table
import com.litesuits.orm.db.enums.AssignType

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: 账户信息实体类
 */

@Table("account_info")
data class AccountInfo(
    /**
     * 账户ID
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    var id: Long? = null,

    /**
     * 用户ID
     */
    @Column("user_id")
    var userId: Long? = null,

    /**
     * 账户名称
     */
    @Column("name")
    var name: String? = "",

    /**
     * 账户类型
     */
    @Column("type")
    var type: String? = "",

    /**
     * 账户余额
     */
    @Column("balance")
    var balance: Double? = 0.0
){
    fun update(){
        LiteOrm.liteOrm?.save(this)
    }
    fun delete(){
        LiteOrm.liteOrm?.delete(this)
    }
}
