package com.maishare.test;

import com.maishare.themis.component.base.checker.AssertMatcherChain;
import com.maishare.themis.component.base.checker.MatcherContext;
import com.maishare.themis.extension.ExtensionLoader;
import lombok.Data;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AssertMatcherTest {
    private static final AssertMatcherChain matcherChain = ExtensionLoader
        .getExtensionLoader(AssertMatcherChain.class).getAdaptiveExtension();

    public static void main(String[] args) throws Exception {
        objectType();
    }

    private static void objectType() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date birthday = sdf.parse("2020-03-04");

        User u1 = new User();
        u1.setAge(23);
        u1.setBirthday(birthday);
        u1.setName("zs");
        u1.setTag("我的地盘，我说了算");

        BeanWrapper wrapper1 = new BeanWrapperImpl(u1);

        User u2 = new User();
        u2.setAge(23);
        u2.setBirthday(birthday);
        u2.setName("zs");
        u2.setTag("我的地盘，我说了算");

        BeanWrapper wrapper2 = new BeanWrapperImpl(u2);


        MatcherContext context = new MatcherContext();
        context.setActual(wrapper1);
        context.setExpected(wrapper2);
        matcherChain.match(context);
    }




    @Data
    private static class User extends Person{
        private String name;
        private int    age;
        private Date   birthday;
    }

    @Data
    private static class Person{

        private String tag;

    }
}
