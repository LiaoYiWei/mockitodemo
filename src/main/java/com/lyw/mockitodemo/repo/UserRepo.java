package com.lyw.mockitodemo.repo;

import com.lyw.mockitodemo.domain.User;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public interface UserRepo {

    User save(User user);

    User get(Long id);
}
