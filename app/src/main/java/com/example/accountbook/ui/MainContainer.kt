package com.example.accountbook.ui

import android.view.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.accountbook.R
import java.util.Date

/**
 * @author: YanMinng
 * @date: 2024/3/13
 * @description: 主视图
 */

object MainContainerData {
    val message = mutableStateOf(Date())
    var window: Window? = null
}

@Preview(showBackground = true)
@Composable
fun MainContainer() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (viewPages) = createRefs()
        Box(modifier = Modifier
            .constrainAs(viewPages) { top.linkTo(parent.top) }
            .fillMaxWidth()
            .padding(bottom = 60.dp)
        ) {
            ViewPages()
        }
        val (bottomBar) = createRefs()
        Box(modifier = Modifier
            .background(colorResource(R.color.black))
            .constrainAs(bottomBar) { bottom.linkTo(parent.bottom) }
            .fillMaxWidth()
            .height(60.dp)
        ) {
            UpdatingDataDemo()
        }
    }
}

@Preview
@Composable
fun UpdatingDataDemo() {
    Text(
        text = MainContainerData.message.value.toString(),
        color = colorResource(R.color.white),
        modifier = Modifier.padding(16.dp)

    )
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

