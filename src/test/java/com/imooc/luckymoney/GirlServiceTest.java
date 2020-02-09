package com.imooc.luckymoney;

import com.imooc.luckymoney.entity.Luckymoney;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * 表示在测试环境下跑程序
 * @SpringBootTest启动工程
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private LuckymoneyService luckymoneyService;

    @Test
    public void findOneTest(){
        Luckymoney luckymoney = luckymoneyService.findOne(30);
        String luckymoneyValue = luckymoney.getMoney().toString();
        Assert.assertEquals(new String("200"),luckymoneyValue);
    }
}
