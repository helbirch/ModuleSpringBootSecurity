package com.sec.demo.service;

import com.sec.demo.entities.AppRole;
import com.sec.demo.entities.AppUser;

public interface AccounteService {
    public AppUser saveUser(String userName,String password,String confirmedPassword);
    public AppRole save(AppRole role);
    public AppUser loadUserByUserName(String userName);
    public void addRoleToUser(String userName,String roleName);
}
