# MyFrame

用到的技术及框架
- 依赖注入: Dagger2
- http网络请求: retrofit
- rxJava


### 先讲一下Dagger的用法
##### Android Studio配置Dagger

在app的build.gradle添加
```
apply plugin: 'com.neenbedankt.android-apt'//添加apt命令(Dagger2)
```
```
dependencies {
    //Dagger2
    apt 'com.google.dagger:dagger-compiler:2.0.2' //指定注解处理器
    compile 'com.google.dagger:dagger:2.0.2'  //dagger公用api
    provided 'org.glassfish:javax.annotation:10.0-b28'  //添加android缺失的部分javax注解
}
```
在project的build.gradle添加

```
buildscript {
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8' //添加apt命令(Dagger2)
    }
}
```
暂时不讲概念性的东西，只描述Dagger2在这个项目中的应用。

创建两个Module：
AppModule.java
```java
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    User provideUser() {
        User user = new User();
        user.setUserName("kbq");
        return user;
    }

    @Provides
    @Singleton
    IpInfo provideIpInfo() {
        IpInfo ipInfo = new IpInfo();
        ipInfo.setIp("23.45.6.7");
        return ipInfo;
    }
}
```
LoginActivityModule.java
```java
@Module
public class LoginActivityModule {

    private LoginActivity loginActivity;

    public LoginActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    public LoginPresenter provideLoginPresenter(LoginViewBean loginViewBean, IpInfo ipInfo, LoginModel loginModel) {
        return new LoginPresenter(loginViewBean, ipInfo, loginModel);
    }

    @Provides
    @ActivityScope
    public LoginModel provideLoginModel(ApiService apiService) {
        return new LoginModel(apiService);
    }

    @Provides
    @ActivityScope
    public LoginViewBean provideLoginViewBean(User user){
        return new LoginViewBean(user);
    }
}
```
AppModule.java是为项目提供依赖的类，一般是全局性的。用`@Module`注解类，`@Provides`注解表示此方法为项目提供依赖，`@Singleton`表明提供的这个依赖是单实例的，只要注入的是这个依赖的user都指向同一个实例，id相同。
`LoginActivityModule.java`和`AppModule.java`基本相同，只是`LoginActivityModule.java`是提供依赖给`LoginActivity.java`的。

```java
@Provides
    @Singleton
    User provideUser() {
        User user = new User();
        user.setUserName("kbq");
        return user;
    }
```
如上是一个提供User实体bean的方法，方法名不做强制要求，单一般的命名规则是provide+要提供的实例；可以在这个方法中初始化数据，也可以直接new一个实例并返回。


---
AppComponent.java

```java
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
//  inject声明可以注入到哪个类中(可以注解到多个类中，只需方法参数不同)
//  void inject(AppApplication appApplication);
//  void inject(LoginActivity loginActivity);

//  以下声明外部其他地方可以访问实例
    Application getApplication();
    Context getContext();
    User getUser();
    IpInfo getIpInfo();
    ApiService getApiService();
}
```
LoginActivityComponent.java
```java
@ActivityScope
@Component(modules = LoginActivityModule.class,dependencies = AppComponent.class)
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
```
Component是连接module和调用实例的类的中间件，用`@Component`注解，括号中声明依赖哪几个Module。

`AppComponent.java`中类似`Application getApplication();`的方法是声明一个外部可调用的方法。例如`LoginActivityComponent.java`依赖`AppComponent.java`、连接`LoginActivityModule.java`，且`LoginActivityModule.java`中的方法
```
   @Provides
    @ActivityScope
    public LoginViewBean provideLoginViewBean(User user){
        return new LoginViewBean(user);
    }
```
中参数用到了user实例，所以在`AppComponent.java`中需要声明 `User getUser();`。

---
AppApplication.java
```java
public class AppApplication extends Application {
    private AppComponent appComponent;
    private static AppApplication instance;

    public static AppApplication get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
```
自定义的Application，主要初始化appComponent

---
LoginActivity.java
```java
public class LoginActivity extends BaseActivity{
    private static final String TAG = "LoginActivity";
    @Inject
    User user;
    @Inject
    LoginPresenter loginPresenter;
    @Inject
    IpInfo ipInfo;
    @Inject
    ApiService apiService;
    @Inject
    LoginModel loginModel;
    @Inject
    Context context;
    @Inject
    LoginViewBean loginViewBean;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginBinding.setLoginViewBean(loginViewBean);
        loginBinding.setLoginPresenter(loginPresenter);
        loginBinding.setIpInfo(ipInfo);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginActivityComponent.builder()
                .appComponent(appComponent)
                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);
    }
```
方法`setupActivityComponent(AppComponent appComponent)`初始化LoginActivityComponent，并告示inject到此类中。
然后用`@Inject`注解的对象都有值了，
```
@Inject
User user;
```
