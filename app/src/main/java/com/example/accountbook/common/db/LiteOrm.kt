package com.example.accountbook.common.db

import android.content.Context
import com.litesuits.orm.LiteOrm
import com.litesuits.orm.db.assit.QueryBuilder
import java.util.Calendar
import java.util.Date

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description:
 */
object LiteOrm {
    var liteOrm: LiteOrm? = null

    /**
     * 初始化
     * @param context Context
     */
    fun getLiteOrmInstance(context: Context) {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(context, "book.db")
            liteOrm?.setDebugged(true)
        }
    }

    /**
     * 删除数据库
     */
    fun deleteDataBase() {
        liteOrm?.deleteDatabase()
    }

    object CapitalFlowQuery {
        /**
         * 查询月数据
         */
        fun queryMonthlyExpense(year: Int, month: Int): List<CapitalFlowData> {
            val queryStartsAndEnd = QueryStartsAndEnd().apply {
                createStartsAndEnd(year, month)
            }
            val result = arrayListOf<CapitalFlowData>()
            var tempData: CapitalFlowData? = null
            liteOrm?.query(QueryBuilder(CapitalFlow::class.java).apply {
                where(
                    "record_time >= ${queryStartsAndEnd.startTimeStr} " +
                            "AND record_time <= ${queryStartsAndEnd.endTimeStr} " +
                            "AND record_classify = 0",
                    null
                ).appendOrderDescBy("record_time")
            })?.let {
                it.forEach { flow ->
                    if (tempData == null || Calendar.getInstance()
                            .apply { time = Date(tempData!!.date) }
                            .get(Calendar.DATE) != Calendar.getInstance()
                            .apply { time = Date(flow.recordTime!!) }.get(Calendar.DATE)
                    ) {
                        tempData = CapitalFlowData(flow.recordTime!!, 0.toDouble(), arrayListOf())
                        tempData?.let { result.add(it) }
                    }
                    tempData?.expenses?.add(flow)
                    tempData?.amount = (tempData?.amount ?: 0.0) + (flow.amount ?: 0.0)
                }
            }
            return result
        }
    }
}