package com.example.accountbook

import android.app.Application
import android.content.Intent
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.VideoFrameDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.example.accountbook.common.config.Config
import com.example.accountbook.common.config.ConfigUtils
import com.example.accountbook.common.db.InitData
import com.example.accountbook.common.db.LiteOrm

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: Application
 */
class BookApplication : Application(), ImageLoaderFactory {


    override fun onCreate() {
        super.onCreate()
        initConfig()
        initOnFirstBoot()
    }

    /**
     * 第一次启动，准备数据
     */
    private fun initOnFirstBoot() {
        if (Config.System.isFirstBoot.config) {
            Config.System.isFirstBoot.config = false
            //以下代码仅执行一次
            InitData.initTypeData()
        }
    }

    private fun initConfig() {
        // 通用工具类
        Utils.init(this)
        // 配置
        ConfigUtils.initMMKV(this)
        // 设置日志格式
        LogUtils.getConfig().apply {
            globalTag = "AccountBook"
            isLogSwitch = true
            setBorderSwitch(false)
        }
        // 数据库
        LiteOrm.getLiteOrmInstance(baseContext)
    }


    private fun getHomeIntent(): Intent {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        return homeIntent
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext).crossfade(false).components {
            add(VideoFrameDecoder.Factory())
        }.memoryCache {
            // 设置内存缓存
            MemoryCache.Builder(applicationContext).maxSizePercent(0.1).build()
        }.diskCache {
            // 设置磁盘缓存
            DiskCache.Builder().directory(cacheDir.resolve("image_cache")).maxSizePercent(0.02)
                .build()
        }.build()
    }
}