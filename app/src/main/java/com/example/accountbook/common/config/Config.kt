package com.example.accountbook.common.config

import androidx.compose.runtime.mutableStateOf
import com.example.accountbook.common.db.CatchUserConfig
import com.example.accountbook.common.db.User

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: 配置
 */
object Config {
    object System {
        /**
         * 是否是第一次启动
         */
        val isFirstBoot = BooleanConfig("KEY_IS_FIRST_BOOT", true)


    }

    object Theme {
        val MyAppTheme = IntConfig("KEY_MY_APP_THEME", 1)
    }

    object CatchUser {
        /**
         * 当前登录用户
         */
        val user = CatchUserConfig("KEY_USER", User())

        /**
         * 登录状态
         */
        val isLogin = BooleanConfig("KEY_IS_LOGIN", true)
    }

    object Dialog {
        val themeDialog = DialogConfig(mutableStateOf(true))
        val addDialog = DialogConfig(mutableStateOf(false))
    }
}