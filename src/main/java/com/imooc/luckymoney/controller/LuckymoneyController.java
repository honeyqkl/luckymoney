package com.imooc.luckymoney.controller;

import com.imooc.luckymoney.LuckymoneyRepository;
import com.imooc.luckymoney.LuckymoneyService;
import com.imooc.luckymoney.aspect.HttpAspect;
import com.imooc.luckymoney.entity.Luckymoney;
import com.imooc.luckymoney.entity.Result;
import com.imooc.luckymoney.utils.ResultUtil;
import org.hibernate.jdbc.Expectation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckymoneyController {

    private final static Logger logger = LoggerFactory.getLogger(LuckymoneyController.class);

    /**
     * 获取红包列表
     */
    @Autowired
    private LuckymoneyRepository repository;

    @Autowired
    private LuckymoneyService service;

    @GetMapping("/luckymoneys")
    public List<Luckymoney> list(){

        logger.info("list方法执行");
        return repository.findAll();
    }

    /**
     * 创建红包 （发红包）
     * 可以直接使用 Luckymoney luckymoney的方式来获取表单中的值
     * bindingResult 获取验证信息
     */
    @PostMapping("luckymoneys")
    public Result<Luckymoney> create(@Valid Luckymoney luckymoney, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(repository.save(luckymoney));
    }

    /**
     * 通过id查询红包
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckymoney findById(@PathVariable("id") Integer id){
       return repository.findById(id).orElse(null);
    }

    /**
     * 更新红包(领红包)
     */
    @PutMapping("/luckymoneys/{id}")
    public Luckymoney update(@PathVariable("id") Integer id,@RequestParam("consumer") String consumer){

        Optional<Luckymoney> optional = repository.findById(id);
        //判断实例是否存在
        if (optional.isPresent()){
            Luckymoney luckymoney = optional.get();
            luckymoney.setConsumer(consumer);
            return repository.save(luckymoney);
        }

        return null;
    }

    @PostMapping("/luckymoneys/two")
    public void createTwo(){
        service.createTwo();
    }

    //对红包金额进行判断
    @GetMapping(value = "/luckymoneys/getMoney/{id}")
    public void getMoney(@PathVariable("id") Integer id) throws Exception {
        service.getMoney(id);
    }

}
