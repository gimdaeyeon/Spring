package com.core.core;

import com.core.core.discount.DiscountPolicy;
import com.core.core.discount.FixDiscountPolicy;
import com.core.core.discount.RateDiscountPolicy;
import com.core.core.member.MemberRepository;
import com.core.core.member.MemberService;
import com.core.core.member.MemberServiceImpl;
import com.core.core.member.MemoryMemberRepository;
import com.core.core.order.OrderService;
import com.core.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
