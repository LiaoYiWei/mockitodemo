package com.howbuy.mockitodemo.repo;

import com.howbuy.mockitodemo.domain.User;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public interface UserRepo {

    User save(User user);

    User get(Long id);
}
