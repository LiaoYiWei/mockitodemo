# mockito demo  

## mockito介绍
Java中单元测试的模拟框架

## mockito原理
* mockito本质上是一个Proxy代理模式的应用。
* mockito提供了一个proxy对象，所有对真实对象的调用，都先经过proxy对象，然后由proxy对象根据情况，决定相应的处理，它可以直接做一个自己的处理，也可以再调用真实对象对应的方法。

## mockito官网
<http://site.mockito.org/>

## demo说明

```java
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
``` 
