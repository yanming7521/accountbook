package com.example.accountbook.common.db

/**
 * @author: YanMinng
 * @date: 2024/2/26
 * @description: 初始化数据
 */
object InitData {
    private val expenseTypeList = mutableListOf<ExpenseType>().apply {
        add(ExpenseType(1,"饮食","基本支出"))
        add(ExpenseType(2,"衣物","基本支出"))
        add(ExpenseType(3,"出行","基本支出"))
        add(ExpenseType(4,"住房","基本支出"))
        add(ExpenseType(5, "通讯", "基本支出"))
        add(ExpenseType(6, "医疗保健", "基本支出"))
        add(ExpenseType(7, "娱乐", "非必要支出"))
        add(ExpenseType(8, "教育", "非必要支出"))
        add(ExpenseType(9, "日常用品", "非必要支出"))
        add(ExpenseType(10, "债务还款", "特殊支出"))
    }
    private val revenueTypeList = mutableListOf<RevenueType>().apply {
        add(RevenueType(1, "工资", "主要收入"))
        add(RevenueType(2, "兼职", "额外收入"))
        add(RevenueType(3, "投资收益", " pass"))
        add(RevenueType(4, "礼金", " pass"))
    }
    private val detailTypeList =  mutableListOf<DetailType>().apply {
        // 饮食分类下的详细分类
        add(DetailType(expenseTypeId = 1, name = "早餐", description = "每天早上的用餐费用"))
        add(DetailType(expenseTypeId = 1, name = "午餐", description = "每天中午的用餐费用"))
        add(DetailType(expenseTypeId = 1, name = "晚餐", description = "每天晚上的用餐费用"))

        // 衣物分类下的详细分类
        add(DetailType(expenseTypeId = 2, name = "衬衫", description = "购买新衬衫的费用"))
        add(DetailType(expenseTypeId = 2, name = "裤子", description = "购买新裤子的费用"))
        add(DetailType(expenseTypeId = 2, name = "鞋子", description = "购买新鞋子的费用"))

        // 出行分类下的详细分类
        add(DetailType(expenseTypeId = 3, name = "公交车费", description = "乘坐公交车的费用"))
        add(DetailType(expenseTypeId = 3, name = "地铁费", description = "乘坐地铁的费用"))
        add(DetailType(expenseTypeId = 3, name = "出租车费", description = "乘坐出租车的费用"))

        // 住房分类下的详细分类
        add(DetailType(expenseTypeId = 4, name = "房租", description = "每月支付的房租"))
        add(DetailType(expenseTypeId = 4, name = "水电费", description = "每月的水电费支出"))
    }

    /**
     * 初始化各种收支分类
     */
    fun initTypeData(){
        expenseTypeList.forEach{
            it.update()
        }
        revenueTypeList.forEach{
            it.update()
        }
        detailTypeList.forEach{
            it.update()
        }
    }
}