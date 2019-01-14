package com.sec.demo.web;

import com.sec.demo.entities.AppUser;
import com.sec.demo.service.AccounteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
    @Autowired
    private AccounteService accounteService;
    @PostMapping("/register")
    public AppUser register(@RequestBody UserFoms userFoms){
        return accounteService.saveUser(userFoms.getUserName(),userFoms.getPassword(),userFoms.getConfirmedPassword());
    }
}
    @Data
    class UserFoms{
    private String userName;
    private String password;
    private String confirmedPassword;

}

