package com.example.accountbook.common.config

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: 配置
 */
object Config {
    object System{
        /**
         * 是否是第一次启动
         */
        val isFirstBoot = BooleanConfig("KEY_IS_FIRST_BOOT", true)
    }
}