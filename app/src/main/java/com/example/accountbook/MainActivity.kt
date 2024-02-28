package com.example.accountbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.drake.net.utils.scopeLife
import com.example.accountbook.common.db.CapitalFlow
import com.example.accountbook.common.db.DetailType
import com.example.accountbook.common.db.ExpenseType
import com.example.accountbook.common.db.LiteOrm
import com.example.accountbook.ui.ViewPages
import com.litesuits.orm.db.assit.QueryBuilder
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Date


class MainActivity : ComponentActivity() {
    private val message = MutableLiveData<Date>()
    private val index = MutableLiveData<Int>(3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ConstraintLayoutExample()
                ViewPages(index)
                UpdatingDataDemo(message)
            }
        }
        scopeLife {
            LiteOrm.liteOrm?.query(ExpenseType::class.java)?.let {
                it.forEach { expenseType ->
//                    LogUtils.a("expenseType: $expenseType")
                    LiteOrm.liteOrm?.query(QueryBuilder(DetailType::class.java).apply {
                        where("type_id = ${expenseType.id}", null)
                    })?.let { detailTypeList ->
                        detailTypeList.forEach { detailType ->
//                            LogUtils.a("detailType: $detailType")
                        }
                    }
                }
            }
            while (true) {
                delay(1000)
                message.postValue(Date())
            }
        }
    }

    @Preview
    @Composable
    fun UpdatingDataDemo(liveData: LiveData<Date> = message) {
        val data by liveData.observeAsState(Date())
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = data.toString(), style = MaterialTheme.typography.h5)
            Button(onClick = {
                CapitalFlow().apply {
                    val calendar = Calendar.getInstance().apply { time = Date() }
                    calendar.add(Calendar.DAY_OF_MONTH, -1)
                    flowingWater = "book${Date().time}"
                    isRecord = true
                    recordTime = calendar.timeInMillis
                    recordClassify = 0
                    typeOfExpense = 2
                    amount = 200.toDouble()
                    detailType = 3
                    description = "测试"
                    userId = 1
                    update()
                }
                LogUtils.a(
                    "result: ${
                        GsonUtils.toJson(
                            LiteOrm.CapitalFlowQuery.queryMonthlyExpense(
                                2024,
                                2
                            )
                        )
                    }"
                )
            }) {
                Text("添加数据")
            }
        }
    }

    @Preview
    @Composable
    fun ConstraintLayoutExample() {
        ConstraintLayout(
            modifier = Modifier.padding(16.dp)
        ) {
            // 定义第一个文本元素在顶部的约束
            val (topText) = createRefs()
            Text(
                text = "顶部文本",
                modifier = Modifier.constrainAs(topText) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            // 定义第二个文本元素在第一个文本元素的下方，并水平居中
            val (bottomText) = createRefs()
            Text(
                text = "底部文本",
                modifier = Modifier.constrainAs(bottomText) {
                    top.linkTo(topText.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}