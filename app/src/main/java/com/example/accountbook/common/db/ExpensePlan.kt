package com.example.accountbook.common.db
import com.litesuits.orm.db.annotation.Column
import com.litesuits.orm.db.annotation.PrimaryKey
import com.litesuits.orm.db.annotation.Table
import com.litesuits.orm.db.enums.AssignType
/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: 消费计划
 */
@Table("expense_plan")
data class ExpensePlan(
    /**
     * 计划ID
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    var id: Long? = null,

    /**
     * 用户ID
     */
    @Column("user_id")
    var userId: Long? = null,

    /**
     * 支出大分类ID
     */
    @Column("expense_type_id")
    var expenseTypeId: Long? = null,

    /**
     * 支出金额
     */
    @Column("amount")
    var amount: Double? = null,

    /**
     * 计划周期（1：年、2：季度、3：月、4：周、5：天）
     */
    @Column("plan_period")
    var planPeriod: Int? = null,

)