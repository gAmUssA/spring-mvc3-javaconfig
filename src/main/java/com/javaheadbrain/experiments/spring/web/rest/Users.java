package com.javaheadbrain.experiments.spring.web.rest;

import com.javaheadbrain.experiments.spring.domain.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
public class Users {
    private List<User> users = new ArrayList<User>();

    public Users(Iterable<User> all) {
        for (User user : all) {
            users.add(user);
        }
    }
}
