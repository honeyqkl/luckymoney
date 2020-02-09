package com.imooc.luckymoney;

import com.imooc.luckymoney.entity.Luckymoney;
import com.imooc.luckymoney.enums.ResultEnum;
import com.imooc.luckymoney.exception.MoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LuckymoneyService {

    @Autowired
    private LuckymoneyRepository repository;

    //事务 指数据库的事务
    //需求存入520 1314两个红包
    //这里举了一个例子假设数据库 money字段长度为3
    //那么520会存入 1314不会存入
    //如果想要同时存入 或者 同时不存入 需要使用 @Transactional注解来声明事务
    //但还是报错 查看表引擎是否支持事务 MyISAM不支持事务 InnoDB支持事务
    @Transactional
    public void createTwo(){
        Luckymoney luckymoney1 = new Luckymoney();
        luckymoney1.setProducer("杜");
        luckymoney1.setMoney(new BigDecimal("520"));
        repository.save(luckymoney1);

        Luckymoney luckymoney2 = new Luckymoney();
        luckymoney2.setProducer("杜");
        luckymoney2.setMoney(new BigDecimal("1314"));
        repository.save(luckymoney2);
    }


    //通过id 获取money

    public void getMoney(Integer id) throws Exception{
        Luckymoney luckymoney = new Luckymoney();
        luckymoney.setId(id);
        Example<Luckymoney> example = Example.of(luckymoney);

        Optional<Luckymoney> optionalLuckymoney = repository.findOne(example);

        if (optionalLuckymoney.isPresent()){
            Luckymoney luckymoneyResult = optionalLuckymoney.get();
            BigDecimal money = luckymoneyResult.getMoney();
            //
            if (money.compareTo(new BigDecimal("10")) <= 0){
                throw new MoneyException(ResultEnum.POOL);
            } else if (money.compareTo(new BigDecimal("200")) > 0){
                throw new MoneyException(ResultEnum.RICH);
            }

        }

    }


    /**
     * 通过id来查询
     * @param id
     * @return
     */
    public Luckymoney findOne(Integer id){
        Optional<Luckymoney> optionalLuckymoney =  repository.findById(id);
        if (optionalLuckymoney.isPresent()){
            Luckymoney luckymoneyResult = optionalLuckymoney.get();
            return luckymoneyResult;
        }
        return null;
    }
}
