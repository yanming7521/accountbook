package com.example.accountbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.colorResource
import com.blankj.utilcode.util.LogUtils
import com.drake.net.utils.scopeLife
import com.example.accountbook.common.db.DetailType
import com.example.accountbook.common.db.ExpenseType
import com.example.accountbook.common.db.LiteOrm
import com.example.accountbook.ui.MainContainer
import com.example.accountbook.ui.MainContainerData
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.litesuits.orm.db.assit.QueryBuilder
import kotlinx.coroutines.delay
import java.util.Date

/**
 * 超过主分支
 * @author: YanMinng
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            rememberSystemUiController().setNavigationBarColor(
                color = colorResource(R.color.black)
            )
            MainContainer()
        }
//        lateInit()
//        mFlowView.openFlowView()
        scopeLife {
            LiteOrm.liteOrm?.query(ExpenseType::class.java)?.let {
                it.forEach { expenseType ->
                    LogUtils.a("expenseType: $expenseType")
                    LiteOrm.liteOrm?.query(QueryBuilder(DetailType::class.java).apply {
                        where("type_id = ${expenseType.id}", null)
                    })?.let { detailTypeList ->
                        detailTypeList.forEach { detailType ->
                            LogUtils.a("detailType: $detailType")
                        }
                    }
                }
            }
            while (true) {
                delay(1000)
                MainContainerData.message.value = Date()
            }
        }
    }
}

