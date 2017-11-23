package com.howbuy.mockitodemo.service;

import com.howbuy.mockitodemo.domain.User;
import com.howbuy.mockitodemo.repo.UserRepo;
import com.howbuy.mockitodemo.service.ext.IdentityVerifyService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
@Service
public class UserService {

    @Setter
    UserRepo userRepo;
    @Setter
    IdentityVerifyService identityVerifyService;

    @Autowired
    public UserService(UserRepo userRepo, IdentityVerifyService identityVerifyService) {
        this.userRepo = userRepo;
        this.identityVerifyService = identityVerifyService;
    }

    public User register(User registeredUser) {
        boolean verify = identityVerifyService.verify(registeredUser.getIdentity(), registeredUser.getName());
        if (!verify) throw new RuntimeException("identity verify failed");
        return userRepo.save(registeredUser);
    }


}
