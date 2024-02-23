package com.example.accountbook.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drake.net.utils.scope

/**
 * @author: YanMinng
 * @date: 2024/2/23
 * @description: 主视图
 */

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ViewPages(indexLiveData: LiveData<Int> = MutableLiveData(0)){
    val pagerState = rememberPagerState(pageCount = { 4 })
    val index by indexLiveData.observeAsState(0)
    scope {
        pagerState.scrollToPage(index)
    }
    HorizontalPager(pagerState) { page ->
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            // 根据页面索引显示不同的内容
            Text(text = page.toString(), style = MaterialTheme.typography.h5)
        }
    }
}
