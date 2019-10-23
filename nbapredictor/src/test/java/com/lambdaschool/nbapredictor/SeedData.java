//package com.lambdaschool.nbapredictor;
//
//import com.github.javafaker.Faker;
//import com.github.javafaker.service.FakeValuesService;
//import com.github.javafaker.service.RandomService;
//import com.lambdaschool.nbapredictor.models.*;
//import com.lambdaschool.nbapredictor.services.PlayerService;
//import com.lambdaschool.nbapredictor.services.RoleService;
//import com.lambdaschool.nbapredictor.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Locale;
//
//@Transactional
//@Component
//public class SeedData implements CommandLineRunner
//{
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    PlayerService playerService;
//
//
//    @Override
//    public void run(String[] args) throws Exception {
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role("data");
//
//        roleService.save(r1);
//        roleService.save(r2);
//        roleService.save(r3);
//
//        // admin, data, user
//        ArrayList<UserRoles> admins = new ArrayList<>();
//        admins.add(new UserRoles(new User(), r1));
//        admins.add(new UserRoles(new User(), r2));
//        admins.add(new UserRoles(new User(), r3));
//        User u1 = new User("T admin", "ILuvM4th!", "admin@lambdaschool.local", admins);
//        u1 = userService.save(u1);
//
//
//
//        // data, user
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        datas.add(new UserRoles(new User(), r3));
//        datas.add(new UserRoles(new User(), r2));
//        User u2 = new User("cinnamon", "1234567", "cinnamon@lambdaschool.local", datas);
//
//        u2 = userService.save(u2);
//
//        // user
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r1));
//        User u3 = new User("testbarn", "ILuvM4th!", "testbarn@school.lambda", users);
//
//        u3 = userService.save(u3);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        User u4 = new User("testcat", "password", "testcat@school.lambda", users);
//        u4 = userService.save(u4);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        User u5 = new User("testdog", "password", "testdog@school.lambda", users);
//        u5 = userService.save(u5);
//
//        ArrayList<SimilarPlayer> s = new ArrayList<>();
//        Player p1 = new Player();
//        p1.setName("MJ");
//        p1.setSimilarplayers(s);
//        p1.setUser(u1);
//        playerService.save(p1);
//
//        System.out.println("\n*** Seed Data ***");
//        System.out.println(u1);
//        System.out.println(u2);
//        System.out.println(u3);
//        System.out.println(u4);
//        System.out.println(u5);
//        System.out.println("*** Seed Data ***\n");
//    }
//}