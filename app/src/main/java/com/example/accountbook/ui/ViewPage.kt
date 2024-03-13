package com.example.accountbook.ui

import android.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.accountbook.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author: YanMinng
 * @date: 2024/2/23
 * @description: 主视图
 */
object ViewPageData {
    val pagerCount = mutableIntStateOf(4)
    val pagerIndex = mutableIntStateOf(0)
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ViewPages() {
    val pagerState = rememberPagerState(initialPage = ViewPageData.pagerIndex.intValue) {
        ViewPageData.pagerCount.intValue
    }
    HorizontalPager(pagerState) { page ->
        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = page.toString(),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
