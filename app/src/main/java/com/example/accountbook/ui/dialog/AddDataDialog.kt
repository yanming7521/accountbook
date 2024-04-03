package com.example.accountbook.ui.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.accountbook.R
import com.example.accountbook.common.config.Config
import com.example.accountbook.common.config.DialogConfig
import com.example.accountbook.ui.MyAppTheme

/**
 * @author: YanMinng
 * @date: 2024/3/27
 * @description: 主弹窗添加数据
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun AddDataDialogLayout(
    dialogConfig: DialogConfig = Config.Dialog.addDialog,
    mSheetState: SheetState = rememberModalBottomSheetState()
) {
    MyAppTheme {
        if (dialogConfig.isShowDialog()) {
            ModalBottomSheet(
                onDismissRequest = { dialogConfig.dismissDialog() },
                sheetState = mSheetState,
                containerColor = MaterialTheme.colors.background
            ) {
                AddDataLayout()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddDataLayout() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.CenterStart),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp),
                        contentDescription = "",
                    )
                    Text(text = "收入")
                }
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp),
                        contentDescription = "",
                    )
                    Text(text = "支出")
                }
            }
        }
    }
}
