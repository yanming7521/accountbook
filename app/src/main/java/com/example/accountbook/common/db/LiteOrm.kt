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

    object CapitalFlowQuery{
        /**
         * 查询月数据
         */
        fun queryMonthlyData(year: Int, month: Int):List<CapitalFlowData>{
            val queryStartsAndEnd = QueryStartsAndEnd().apply {
                createStartsAndEnd(year,month)
            }
            var result = arrayListOf<CapitalFlowData>()
            var curDate =  Calendar.getInstance().get(Calendar.DATE)
            liteOrm?.query(QueryBuilder(CapitalFlow::class.java).apply {
                where( "record_time >= ${queryStartsAndEnd.startTimeStr} AND record_time <= ${queryStartsAndEnd.endTimeStr}",
                    null).appendOrderDescBy("id")
            })?.let {
                val tempData = CapitalFlowData()
                it.forEach {
                    if(curDate ==  Calendar.getInstance(it.recordTime).get(Calendar.DATE)){

                    }
                }

            }
            return result
        }

    }
}