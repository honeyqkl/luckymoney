package com.imooc.luckymoney;

import com.imooc.luckymoney.entity.Luckymoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuckymoneyRepository extends JpaRepository<Luckymoney,Integer> {
}
