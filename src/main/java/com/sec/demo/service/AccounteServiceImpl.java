package com.sec.demo.service;

import com.sec.demo.dao.AppRoleRepository;
import com.sec.demo.dao.AppUserRepository;
import com.sec.demo.entities.AppRole;
import com.sec.demo.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccounteServiceImpl implements AccounteService {
    private  AppRoleRepository appRoleRepository;
    private  AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public AccounteServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser saveUser(String userName, String password, String confirmedPassword) {
        AppUser user=appUserRepository.findByUserName(userName);
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Plese confirm your password");
        AppUser appUser=new AppUser();
        appUser.setUserName(userName);
        appUser.setActived(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(userName,"USER");
        return appUser;
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUserName(String userName) {
        return appUserRepository.findByUserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser=appUserRepository.findByUserName(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }
}
