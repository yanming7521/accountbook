package com.example.accountbook.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.accountbook.R
import java.util.Date

/**
 * @author: YanMinng
 * @date: 2024/3/13
 * @description: 主视图
 */

object MainContainerData {
    val message = mutableStateOf(Date())
}

@Preview(showBackground = true)
@Composable
fun MainContainer() {
    MyAppTheme{
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
                .constrainAs(bottomBar) { bottom.linkTo(parent.bottom) }
                .fillMaxWidth()
                .height(60.dp)
            ) {
                TabBar()
            }
        }
        LoginDialog()
    }
}

object TabBarData {
    var itemList: MutableList<String> = arrayListOf()
}

@Preview(showBackground = true)
@Composable
fun TabBar(
) {
    TabBarData.itemList = mutableListOf(
        stringResource(R.string.tab_1),
        stringResource(R.string.tab_2),
        stringResource(R.string.tab_3),
        stringResource(R.string.tab_4),
    )
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        TabBarData.itemList.forEachIndexed { index, s ->
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(
                    if (ViewPageData.pagerIndex.intValue == index) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.background
                    }
                )
                .clickable {
                    ViewPageData.pagerIndex.intValue = index
                }) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                    contentDescription = s,
                    modifier = Modifier
                        .width(33.dp)
                        .height(33.dp)
                        .padding(top = 4.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = s, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

