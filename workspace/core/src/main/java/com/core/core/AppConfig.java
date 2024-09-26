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

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(discountPolicy(),memberRepository());
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
