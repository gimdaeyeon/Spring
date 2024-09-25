package com.core.core;

import com.core.core.member.Grade;
import com.core.core.member.Member;
import com.core.core.member.MemberService;
import com.core.core.member.MemberServiceImpl;
import com.core.core.order.Order;
import com.core.core.order.OrderService;
import com.core.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());

    }
}
