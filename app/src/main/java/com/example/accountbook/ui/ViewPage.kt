package com.example.accountbook.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.drake.net.utils.scope

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
@Preview(showBackground = true)
@Composable
fun ViewPages() {
    val pagerState = rememberPagerState(initialPage = ViewPageData.pagerIndex.intValue) {
        ViewPageData.pagerCount.intValue
    }
    scope {
        pagerState.scrollToPage(ViewPageData.pagerIndex.intValue)
    }
    LaunchedEffect(pagerState.currentPage) {
        ViewPageData.pagerIndex.intValue = pagerState.currentPage
    }
    HorizontalPager(pagerState) { page ->
        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            when (page) {
                0 -> Index()
                1 -> DataAnalysis()
                2 -> Account()
                3 -> MinePage()
                else -> PageContent(page)
            }
        }
    }
}

@Composable
fun PageContent(page: Int) {
    // 这里可以自定义每一页的内容，例如：
    Text(
        text = "Page $page",
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}
