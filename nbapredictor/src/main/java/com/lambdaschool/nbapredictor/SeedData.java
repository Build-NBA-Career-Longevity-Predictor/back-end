package com.lambdaschool.nbapredictor;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.nbapredictor.models.Role;
import com.lambdaschool.nbapredictor.models.User;
import com.lambdaschool.nbapredictor.models.UserRoles;
import com.lambdaschool.nbapredictor.services.RoleService;
import com.lambdaschool.nbapredictor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Locale;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        roleService.save(r1);
        roleService.save(r2);

        // admin, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                                 r1));
        admins.add(new UserRoles(new User(),
                                 r2));
        User u1 = new User("admin",
                           "admin",
                           "admin@admin.local",
                           admins);

        userService.save(u1);

        ArrayList<UserRoles> users = new ArrayList<>();

        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                                                                    new RandomService());
        Faker nameFaker = new Faker(new Locale("en-US"));

        for (int i = 0; i < 10; i++)
        {
            new User();
            User fakeUser;

            users = new ArrayList<>();
            users.add(new UserRoles(new User(),
                                    r2));
            fakeUser = new User(nameFaker.name()
                                         .username(),
                                "password",
                                nameFaker.internet()
                                         .emailAddress(),
                                users);

            userService.save(fakeUser);
        }
    }
}