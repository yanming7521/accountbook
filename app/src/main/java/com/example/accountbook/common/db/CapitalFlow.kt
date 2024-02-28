package com.example.accountbook.common.db

import com.litesuits.orm.db.annotation.*
import com.litesuits.orm.db.enums.AssignType
import java.util.Calendar

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
    var flowingWater: String? = "",
    /**
     * 是否入账
     */
    @Column("is_record")
    var isRecord: Boolean = false,
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
    var amount: Double? = null,

    /**
     * 分类ID
     */
    @Column("type_id")
    var detailType: Long? = null,
    /**
     * 用途描述
     */
    @Column("description")
    var description: String? = "",
    /**
     * 用户
     */
    @Column("user_id")
    var userId: Int? = null
) {
    fun update() {
        LiteOrm.liteOrm?.save(this)
    }

    fun delete() {
        LiteOrm.liteOrm?.delete(this)
    }
}

/**
 * 按照天组织流水数据
 * @property date Long
 * @property expenses List<CapitalFlow>
 * @constructor
 */
data class CapitalFlowData(
    var date: Long,
    var amount: Double? = null,
    val expenses: MutableList<CapitalFlow>
)

data class QueryStartsAndEnd(
    var startTimeStr: Long? = null,
    var endTimeStr: Long? = null
) {
    fun createStartsAndEnd(year: Int, month: Int) {
        // 准备查询日期时间戳
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1) // 月份是从0开始的，所以要减1
        val startDate = calendar.clone() as Calendar
        startDate.set(Calendar.DAY_OF_MONTH, 1) // 设置为指定月份的第一天
        startDate.set(Calendar.HOUR_OF_DAY, 0)
        startDate.set(Calendar.MINUTE, 0)
        startDate.set(Calendar.SECOND, 0)
        val endDate = calendar.clone() as Calendar
        endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH))
        endDate.set(Calendar.HOUR_OF_DAY, 23)
        endDate.set(Calendar.MINUTE, 59)
        endDate.set(Calendar.SECOND, 59)
        startTimeStr = startDate.timeInMillis
        endTimeStr = endDate.timeInMillis
    }
}