package org.lenapalasionak.spring.security.configurtion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity//класс ответственный за security configurations, @Configurtion можно не писать, т.к. эта аннотация уже в себе несет этот функционал
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;//соединения БД и сервера

    @Override// метод для аутентиикации
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);//1. берем все из БД

        //2. берм не из БД, создаем сами
//       User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//       auth.inMemoryAuthentication()
//               .withUser(userBuilder.username("Len")
//                       .password("len")
//                       .roles("EMPLOYEE"))
//               .withUser(userBuilder.username("Nelly")
//                       .password("nelly")
//                       .roles("HR"))
//               .withUser(userBuilder.username("Kim")
//                       .password("kim")
//                       .roles("MANAGER","HR"));
    }

    @Override// метод для авторизации
    protected void configure(HttpSecurity http) throws Exception { //запрашиваем авторизциюдля прописанных URL
      http.authorizeRequests().antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
              .antMatchers("/hr-info").hasRole("HR")
              .antMatchers("/manager-info").hasRole("MANAGER")
              .and().formLogin().permitAll();

    }
}
