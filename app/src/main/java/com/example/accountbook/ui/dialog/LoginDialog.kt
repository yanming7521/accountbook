package com.example.accountbook.ui.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.accountbook.common.config.Config
import com.example.accountbook.common.config.DialogConfig
import com.example.accountbook.ui.MyAppTheme

/**
 * @author: YanMinng
 * @date: 2024/3/26
 * @description: 登录弹窗
 */

object LoginDialogData {
    val userName = mutableStateOf(Config.CatchUser.user.mutableStateData.value.username ?: "")
    val account = mutableStateOf(Config.CatchUser.user.mutableStateData.value.email ?: "")
    val password = mutableStateOf(Config.CatchUser.user.mutableStateData.value.password ?: "")
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun LoginDialog(dialogConfig: DialogConfig = DialogConfig(Config.CatchUser.isLogin.mutableStateData)) {
    MyAppTheme {
        if (dialogConfig.isShowDialog()) {
            return@MyAppTheme
        }
        Dialog(onDismissRequest = { dialogConfig.dismissDialog() }) {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .defaultMinSize(minHeight = 200.dp),
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.33.dp)
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Text(text = "登录", style = MaterialTheme.typography.h6)
                    OutlinedTextField(
                        modifier = Modifier.padding(16.dp),
                        value = LoginDialogData.account.value,
                        onValueChange = {
                            LoginDialogData.account.value = it
                        },
                        placeholder = { Text("请输入账号") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { /* doSomething() */ }),
                    )
                    OutlinedTextField(
                        modifier = Modifier.padding(16.dp),
                        value = LoginDialogData.password.value,
                        onValueChange = {
                            LoginDialogData.password.value = it
                        },
                        placeholder = { Text("请输入密码") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { /* doSomething() */ }),
                    )
                }
            }
        }
    }
}
