package com.example.accountbook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.accountbook.R
import com.example.accountbook.common.config.Config.Dialog.addDialog
import com.example.accountbook.ui.dialog.AddDataDialogLayout

/**
 * @author: YanMinng
 * @date: 2024/3/22
 * @description:
 */


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Index() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.boulder))
    ) {
        Button(
            onClick = { addDialog.showDialog() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("显示底部弹窗")
        }
    }
    AddDataDialogLayout()
}