package com.example.accountbook.common.db

import com.litesuits.orm.db.annotation.Column
import com.litesuits.orm.db.annotation.PrimaryKey
import com.litesuits.orm.db.annotation.Table
import com.litesuits.orm.db.enums.AssignType

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: 收入支出类型
 */
/**
 * 支出类型实体类
 */
@Table("expense_type")
data class ExpenseType(
    /**
     * 支出类型ID
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    var id: Long? = null,

    /**
     * 支出类型名称
     */
    @Column("name")
    var name: String? = "",

    /**
     * 支出类型描述
     */
    @Column("description")
    var description: String? = ""
){
    fun update(){
        LiteOrm.liteOrm?.save(this)
    }
    fun delete(){
        LiteOrm.liteOrm?.delete(this)
    }
}

/**
 * 收入类型实体类
 */
@Table("revenue_type")
data class RevenueType(
    /**
     * 收入类型ID
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    var id: Long? = null,

    /**
     * 收入类型名称
     */
    @Column("name")
    var name: String? = "",

    /**
     * 收入类型描述
     */
    @Column("description")
    var description: String? = ""
){
    fun update(){
        LiteOrm.liteOrm?.save(this)
    }
    fun delete(){
        LiteOrm.liteOrm?.delete(this)
    }
}

/**
 * 支出详细分类实体类
 */
@Table("detail_type")
data class DetailType(
    /**
     * 详细分类ID
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    var id: Long? = null,

    /**
     * 分类ID
     */
    @Column("type_id")
    var expenseTypeId: Long? = null,

    /**
     * 详细分类名称
     */
    @Column("name")
    var name: String? = "",

    /**
     * 详细分类描述
     */
    @Column("description")
    var description: String? = ""
){
    fun update(){
        LiteOrm.liteOrm?.save(this)
    }
    fun delete(){
        LiteOrm.liteOrm?.delete(this)
    }
}