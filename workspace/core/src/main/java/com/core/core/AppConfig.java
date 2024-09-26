package com.core.core;

import com.core.core.discount.FixDiscountPolicy;
import com.core.core.member.MemberService;
import com.core.core.member.MemberServiceImpl;
import com.core.core.member.MemoryMemberRepository;
import com.core.core.order.OrderService;
import com.core.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new FixDiscountPolicy(),new MemoryMemberRepository());
    }
}
