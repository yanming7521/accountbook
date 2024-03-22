package com.example.accountbook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.accountbook.R

/**
 * @author: YanMinng
 * @date: 2024/3/22
 * @description:
 */

@Preview(showBackground = true)
@Composable
fun MinePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.coral_red))
    ) {

    }
}