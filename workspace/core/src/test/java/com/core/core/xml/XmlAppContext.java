package com.core.core.xml;

import com.core.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {
    @Test
    void xmlAppContext(){
        ApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = genericXmlApplicationContext.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
