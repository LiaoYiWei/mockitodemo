package com.howbuy.mockitodemo.service.ext;

import org.springframework.stereotype.Service;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
@Service
public class IdentityVerifyServiceImpl implements IdentityVerifyService {

    @Override
    public boolean verify(String identity, String name) {
        return false;
    }

}
