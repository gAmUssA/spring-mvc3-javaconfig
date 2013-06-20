package com.javaheadbrain.experiments.spring.web.rest;

import com.javaheadbrain.experiments.spring.domain.User;
import com.javaheadbrain.experiments.spring.entity.Account;
import com.javaheadbrain.experiments.spring.mapper.AccountMapper;
import com.javaheadbrain.experiments.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/rest/users")
public class UserRestController {

    private final UserRepository repository;

    @Inject
    AccountMapper accountMapper;

    @RequestMapping("/")
    @ResponseBody
    public List<User> showAll() {
        return (List<User>) repository.findAll();
    }

    @RequestMapping("/accounts")
    @ResponseBody
    public List<Account> showAllAccounts() {
        return accountMapper.findAllAccounts();
    }

    @Autowired
    public UserRestController(UserRepository repository) {
        Assert.notNull(repository);
        this.repository = repository;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public Users showUsers(Model model) {
        return new Users(repository.findAll());
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody User User, HttpServletResponse response) {
        repository.save(User);
        response.setHeader("Location",
                String.format("/rest/Users/%s", User.getId()));
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public User showUser(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseStatus(OK)
    public void updateUser(@RequestBody User User) {

        repository.save(User);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseStatus(OK)
    public void deleteUser(@PathVariable Long id) {
        repository.delete(id);
    }
}