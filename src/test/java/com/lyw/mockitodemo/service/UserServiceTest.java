package com.lyw.mockitodemo.service;

import com.lyw.mockitodemo.common.SpringContextUtil;
import com.lyw.mockitodemo.domain.User;
import com.lyw.mockitodemo.repo.UserRepo;
import com.lyw.mockitodemo.service.ext.IdentityVerifyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    //1.生成mock对象到ApplicationContext
    @MockBean
    UserRepo userRepo;
    @MockBean
    IdentityVerifyService identityVerifyService;

    @Autowired
    UserService userService;

    @Before
    public void setUp() {
        //准备阶段
//        UserRepo userRepo = mock(UserRepo.class);
//        IdentityVerifyService identityVerifyService = mock(IdentityVerifyService.class);
//        userService = new UserService(userRepo, identityVerifyService);
    }

    /**
     * 测试注册成功的路径
     */
    @Test
    public void register_SuccessPath() {

        //2.获取UserRepo mock对象
        //优先选择mockBean注入ApplicationContext
        UserRepo bean = SpringContextUtil.getBean(UserRepo.class);

        //3.mock设置
        User waitRegisterUser = createRegisteredUser();
        given(userRepo.save(waitRegisterUser)).willReturn(createRegisteredUserWithId());
        given(identityVerifyService.verify("testIdentity", "testName")).willReturn(true);

        //4.业务逻辑执行
        //运行阶段
        User registeredUser = userService.register(waitRegisterUser);

        //5.断言校验
        Assert.assertTrue(registeredUser.equals(createRegisteredUserWithId()));

        //6.验证mock方法是否执行
        //验证阶段
        then(userRepo).should().save(waitRegisterUser);
        then(identityVerifyService).should().verify("testIdentity", "testName");
    }

    /**
     * 测试标识信息校验失败的路径
     */
    @Test(expected = RuntimeException.class)
    public void register_IdentityVerityFailPath() {
        //7.mock设置
        User waitRegisterUser = createRegisteredUser();
        given(userRepo.save(waitRegisterUser)).willReturn(createRegisteredUserWithId());
        given(identityVerifyService.verify("testIdentity", "testName")).willReturn(false);

        //8.业务逻辑执行
        //运行阶段
        User registeredUser = userService.register(waitRegisterUser);
        Assert.assertTrue(registeredUser.equals(createRegisteredUserWithId()));

        //9.验证mock方法是否执行
        //验证阶段
        then(userRepo).should().save(waitRegisterUser);
        then(identityVerifyService).should().verify("testIdentity", "testName");
    }


    private User createRegisteredUserWithId() {
        User dummyUser = createRegisteredUser();
        dummyUser.setId(1L);
        return dummyUser;
    }

    private User createRegisteredUser() {
        User user = new User();
        user.setName("testName");
        user.setMobile("18612345678");
        user.setPassword("password");
        user.setIdentity("testIdentity");
        return user;
    }

}