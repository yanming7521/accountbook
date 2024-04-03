package com.example.accountbook.ui.dialog

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.accountbook.common.config.Config
import com.example.accountbook.common.config.DialogConfig
import com.example.accountbook.ui.MyAppTheme
import com.example.accountbook.ui.MyAppThemeData
import com.example.accountbook.ui.getThemePalette

/**
 * @author: YanMinng
 * @date: 2024/3/27
 * @description:
 */
@Preview(showBackground = true)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThemeDialog(dialogConfig: DialogConfig = Config.Dialog.themeDialog) {
    MyAppTheme {
        if (dialogConfig.isShowDialog().not()) {
            return@MyAppTheme
        }
        Dialog(onDismissRequest = { dialogConfig.dismissDialog() }) {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp)),
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Text(text = "主题切换", modifier = Modifier.padding(20.33f.dp))
                FlowRow(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(top = 60.dp)
                        .padding(start = 12.dp)
                        .padding(end = 12.dp)
                        .padding(bottom = 20.dp)
                ) {
                    MyAppThemeData.themeList.forEach {
                        Button(
                            onClick = {
                                Config.Theme.MyAppTheme.config = it
                            },
                            modifier = Modifier
                                .padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = getThemePalette(it).primary,
                                contentColor = getThemePalette(it).background
                            )
                        ) {

                        }
                    }
                }

            }
        }
    }
}