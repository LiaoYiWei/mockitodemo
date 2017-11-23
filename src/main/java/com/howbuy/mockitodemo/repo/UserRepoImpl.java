package com.howbuy.mockitodemo.repo;

import com.howbuy.mockitodemo.domain.User;
import org.springframework.stereotype.Repository;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
@Repository
public class UserRepoImpl implements UserRepo{

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }
}
