package com.example.accountbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.drake.net.utils.scopeLife
import com.example.accountbook.ui.ViewPages
import kotlinx.coroutines.delay
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
            while (true){
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