package com.example.accountbook.common.db

import android.content.Context
import com.litesuits.orm.LiteOrm

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description:
 */
object LiteOrm {
    var liteOrm: LiteOrm? = null
    fun getLiteOrmInstance(context: Context) {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(context, "book.db")
            liteOrm?.setDebugged(true)
        }
    }

    fun deleteDataBase() {
        liteOrm?.deleteDatabase()
    }
}