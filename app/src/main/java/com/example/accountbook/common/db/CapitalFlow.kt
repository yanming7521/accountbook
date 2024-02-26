package com.example.accountbook.common.db
import com.litesuits.orm.db.annotation.*
import com.litesuits.orm.db.enums.AssignType

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description:
 */
@Table("capital_flow")
data class CapitalFlow(
    /**
     * id
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    var id: Long? = null,
    /**
     * 流水号
     */
    @Column("flowing_water")
    var flowingWater:String? = "",
    /**
     * 是否入账
     */
    @Column("is_record")
    var isRecord:Boolean = false,
    /**
     * 记录时间
     */
    @Column("record_time")
    var recordTime: Long? = null,
    /**
     * 记录类型(1收入，0支出)
     */
    @Column("record_classify")
    var recordClassify: Int? = null,
    /**
     * 支出类型
     */
    @Column("type_of_expense")
    var typeOfExpense: Int? = null,
    /**
     * 收入类型
     */
    @Column("type_of_revenue")
    var typeOfRevenue: Int? = null,
    /**
     * 金额
     */
    @Column("amount")
    var amount:Double?=null,

    /**
     * 分类ID
     */
    @Column("type_id")
    var expenseTypeId: Long? = null,
    /**
     * 用途描述
     */
    @Column("description")
    var description:String? = "",
    /**
     * 用户
     */
    @Column("user_id")
    var userId:Int?=null
){
    fun update(){
        LiteOrm.liteOrm?.save(this)
    }
    fun delete(){
        LiteOrm.liteOrm?.delete(this)
    }
}
