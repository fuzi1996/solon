### 纪年

* v0: 2018 ~ 2019 (2y)
* v1: 2020 ~ 2022 (3y)
* v2: 2023 ~

### v1.x 升到 v2.x 提醒

* 提醒1：之前没有使用弃用接口的，可以直接升级 <br>
* 提醒2：有使用弃用接口的。建议先升级到 1.12.4；替换弃用代码后，再升级到 2.0.0

### 2.5.9
* 新增 solon.boot.vertx 插件?
* 新增 seata-solon-plugin 插件
* 新增 graphql-solon-plugin 插件
* 增加 quartz jdbc 及时更新支持（数据库里变了，它马上变） ???
* 调整 solon.config.add 与 solon.config.load 合并，规范格式（同时支持内部与外部） ???
* 调整 简化 SocketD ???
* 新增 hibernate-solon-plugin 插件
* 调整 使用更多的 slf4j 替换 LogUtil ???

### 2.5.8
* 取消 全局未处理异常走总线的机制，转由 Log 框架接收（简化用户体验）

### 2.5.7

* 增加 Context::headerOfResponse 接口
* 增加 http server gzip 整体配置支持
* 增加 solon.web.staticfiles 对 gzip 的配置支持
* 增加 solon.boot.jdkhttp 对 gzip 的配置支持（取消内部的自动处理）
* 调整 `@Import` 替代 `@PropertySource`、`@TestPropertySource`，后者标为弃用
* 调整 `@Rollback` 替代 `@TestRollback`，后者标为弃用
* 调整 SolonTestApp 默认关闭 http 服务；避免与已启动服务端口冲突
* 调整 solon.cache.jedis 的两个序列化实现，转到 solon.data（做为公用）
* 调整 solon.cache.redission RedissonCacheService 增加外部序列化接口支持
* 调整 `@Bean` 函数，参数没带注解的算必须
* 调整 paramsMap 增加 autoMultipart 处理
* 调整 nami,forest,feign 负载均衡的获取方式
* 修复 solon-maven-plugin 在 linux 下因为一些用户角色没有权限导致打包失败问题
* 优化 `VarGather` 检查增加自动排序
* 优化 Props::getMap 处理
* 简化 Props::getProp 处理提升性能
* wood 升为 1.2.2
* mybatis-flex 升为 1.6.8
* fastmybatis 升为 2.9.7
* easy-trans 升为 1.3.0
* sa-token 升为 1.36.0
* fastjson2 升为 2.4.0
* jetty 升为 9.4.52.v20230823
* undertow 升为 2.2.26.Final
* redisx 升为 1.6.0

### 2.5.6
* asm 升为 9.5 （for JDK21）

### 2.5.5
* 完成 JDK21 编译测试，功能单元测试
* 添加 HttpServerConfigure::setExecutor 接口，用于支持虚拟线程池（for JDK21）
* 添加 PropUtil 类。把原来的属性表达式与模板解析独立出来
* 添加 ContextPathListener 类，用于控制 contentPath 对 ws,tpc 的影响
* 添加 ContextPathFilter 一个简化的构造函数
* 添加 MethodHolder::getDeclaringClz、getDeclaringClzAnnotation 接口
* 添加 yaml 多片段支持（即一文件多环境支持）
* 添加 多配置文件交差引用变量支持
* 添加 DownloadedFile(file,name) 构造函数
* 添加 Router 对 405 的支持
* 调整 RunUtil 执行器分离为 parallelExecutor + asyncExecutor（for JDK21）
* 调整 CacheService 接口（增加类型化 get）
* 调整 SessionState 接口（增加类型化 get）
* 调整 Context::session 接口（增加类型化 get）
* 调整 BeanWrap 的 rawSet 改为公有
* 调整 SolonApp::enableWebSocketMvc, enableSocketMvc, 默认为 false
* 调整 SolonApp::enableWebSocket, enableWebSocketD 分离设置，各不相关（前者代表通讯，后者代表协议）
* 调整 CloudConfig 复用主框架的属性表达式与模板解析
* 调整 Listener 去掉 @FunctionalInterface，所有方法标为 default
* 调整 ChainManager::postResult 的执行策略改为包围式（相当于倒序）
* 调整 ValHolder 标为弃用
* 调整 jdkhttp,jlhttp,smarthttp 的 contentLength 适配处理
* 调整 使用更多的 slf4j 替换 bus
* 调整 mybatis-solon-plugin 的会话提交方式，修复二级缓存控制可能失效的问题
* 调整 "solon.config.load" 支持按顺序加载
* 调整 几个特定启动参数的处理方式。改与成 Solon.cfg() 同步，再统一从 Solon.cfg() 取值
* 调整 启动参数与系统属性的同步时机
* wood 升为 1.2.1
* redisx 升为 1.5.0
* mybatis-flex 升为 1.6.5
* sqltoy 升为 5.2.66
* polaris 升为 1.14.1
* slf4j 升为 2.0.9
* lombok 升为 1.18.30（for JDK21）

### 2.5.4
* 增加 AppContext::onEvent 接口
* 调整 paramsMap() 的 List 处理，避免出现只读情况
* 调整 JarClassLoader 更名为 AppClassLoader
* 调整 solon.serialization.fastjson2 转换枚举未匹配时则异常
* 调整 solon.serialization.snack3 转换枚举未匹配时则异常
* 调整 smarthttp,jetty,undertow 的 FORM_URLENCODED 预处理
* 调整 signal server 启动打印信息
* mybatis-flex 升为 1.6.4
* dromara-plugins 升为 0.1.0
* snack3 升为 3.2.80
* redisx 升为 1.4.10
* beetlsql 升为 3.25.4-RELEASE
* fastmybatis 升为 2.9.6
* bean-searcher 升为 4.2.4
* rabbitmq 升为 5.18.0
* kafka_2.13 升为 3.5.1
* dubbo3 升为 3.2.5
* logback 升为 1.3.11
* nacos2 升为 2.2.4
* snakeyaml 升为 2.2
* redisson 升为 3.23.3
* luffy 升为 1.6.7
* water 升为 2.11.3

### 2.5.3
* 增加 `AppContext` 类
* 调整 `AopContext` 标为弃用，由 `AppContext` 替代
* 调整 solon.docs.openapi2 对枚举类型的显示处理
* beetlsql 升为 3.25.2-RELEASE

### 2.5.2
* 增加 `@Component` 自动动态代理特性，即自动识别AOP需求并按需启用动态代理
* 调整 `@ProxyComponent` 标为弃用，组件统一使用 `@Component`
* 调整 `@Around` 标为弃用，统一使用 context::beanInterceptorAdd 接口添加拦截器
* liteflow 升为 2.11.0
* activerecord 升为 5.1.2
* enjoy 升为 5.1.2

### 2.4.6
* 增加 http range 分片输出支持
* 增加 IoUtil 工具类，替代旧的 Utils IO功能（旧的标为弃用）
* 增加 `@Tran` 事务监视事件支持，并增加 message 属性（只在最外层触发事件）
* 调整 kafka-solon-cloud-plugin 如果没有订阅，则不启用消费端
* 调整 PathLimiter 的弃用提示方式
* 调整 solon.cloud.metrics 的时间记录单位
* 调整 solon.data.dynamicds 增加默认源配置项
* 优化 solon.serialization.snack3 对根字符串的解码处理
* 优化 solon.scheduling 的 `@Async` 定制能力
* 优化 solon.docs.openapi2 对基础类型列表识别过度的问题
* 优化 solon.docs.openapi2 对实体扩展的字段识别
* 优化 solon.docs.openapi2 的 `@ApiModelProperty` 注解处理
* 优化 solon.data.dynamicds 手动控制能力（添加、移除、获取）
* 优化 sa-token-solon-plugin 两个适配类的处理
* 优化 ConditionUtil 条件工具类处理
* mybatis-flex 升为 1.6.1
* beetlsql 升为 3.25.0-RELEASE
* dbvisitor 升为 5.4.0
* snack3 升为 3.2.79
* forest 升为 1.5.33
* smarthttp 升为 1.3.0

### 2.4.5
* 修复 httputils of okhttp 手动设定超时无效的问题

### 2.4.4
* 新增 mqtt5-solon-cloud-plugin 插件（用于支持 v5 版本）
* 增加 mqtt-solon-cloud-plugin 支持 publishTimeout 配置（默认3秒）
* 增加 mqtt-solon-cloud-plugin 支持 topicFilter 表达式
* 增加 HttpServerConfigure::enableDebug 接口
* 增加 Context::close 强制实现约定
* 增加 JacksonRenderFactory::addFeatures、removeFeatures 接口
* 增加 solon.cloud.eventplus 对事件 qos 传递支持
* 优化 rabbitmq-solon-cloud-plugin 代码实现
* 优化 solon-maven-plugin 打包提示语
* 优化 maven 打包插件版本管理
* beetlsql 升为 3.24.0-RELEASE
* beetl 升为 3.15.8.RELEASE
* mybatis-flex 升为 1.5.8
* fastmybatis 升为 2.9.2
* fastjson2 升为 2.0.39
* snack3 升为 3.2.76
* liteflow 升为 2.10.6
* knife4j 升为 4.2.0

### 2.4.3
* 增加 solon.cloud.metrics 对 MeterBinder 的自动装配处理
* 增加 HandlerLoaderFactory，以实现所有 `@Mapping` 注解解析的重写支持
* 增加 rocketmq-solon-cloud-plugin 插件，对 ak/sk 的支持
* 增加 sa-token 插件，对 SaTokenDaoOfRedisson 实现
* 增加 solon.data.dynamicds 支持各源不同的连接池类型
* 调整 solon.serialization.fastjson 插件，默认增加字段排序特性
* 调整 solon-test 依赖，改成 solon-test-junit4 和 solon-test-junit5 的集合
* 调整 ConvertUtil 内的 LocalDateTime 处理，先通过 Date 中转，统一时间格式
* 调整 FiledWrap 反射权限改为用时再设置
* 调整 RouterInterceptorLimiter 标为弃用，由 RouterInterceptor::pathPatterns 替代
* 调整 ChainManager::getFilterNodes、getInterceptorNodes 输出类型
* 调整 solon-admin 的 server 和 client 通信为有状态格式
* 调整 solon 模块结构
* 调整 solon-admin-server 界面细节
* 修复 solon.docs.openapi2 插件 ApiImplicitParam::paramType 未生效的问题
* 修复 solon.docs.openapi2 插件 ApiParam::hidden 未生效的问题
* sa-token 升为 1.35.0.RC
* mybatis-flex 升为 1.5.7
* redisx 升为 1.4.9

### 2.4.2
* 新增 lettuce-solon-plugin 插件
* 新增 solon.docs.openapi2 插件
* 新增 solon.cloud.metrics 插件
* 升级 solon-maven-plugin 的相关依赖
* 增加 `List<Bean>` 和 `Map<String,Bean>` 注入支持
* 增加 Context::attrOrDefault 接口，原接口标为弃用
* 增加 RouterInterceptor::pathPatterns 接口，原接口标为弃用
* 增加 solon-admin-server 对 basic auth 配置的支持
* 增加 solon-admin-server 对 uiPath 配置的支持
* 增加 solon-admin-client 接口安全控制
* 增加 solon-admin-client 与 server 一起使用时，自动识别 serverUrl
* 增加 solon-admin-client 增加 token 访问控制
* 调整 solon-admin-client ，元信息改为 solon.app 的内容；server 监视日志改为 trace 级别
* 调整 solon.docs 的控制器启动，转到 solon-openapi2-knife4j。方便不同的框架定制自己的服务
* 调整 `@Bean` 函数的 VarGather::requireRun 由 false 改为 true，只要过了条件检测必然运行
* 调整 sa-token-solon-plugin 插件，增加对网关的支持
* 调整 CloudMetricService 接口设计，更适合与 micrometer 对接
* 调整 solon.health.detector 代码实现，增加复用性
* 修复 solon.cloud.eventplus 插件 `@CloudEventSubscribe` 注解在函数会出错的问题
* 修复 solon.docs 插件 `@ApiParam` 注解无效的问题
* mybatis-flex 升为 1.5.6
* beetlsql 升为 3.23.6-RELEASE
* sqltoy 升为 5.2.60
* snack3 升为 3.2.75
* wood 升为 1.1.8
* smarthttp 升为 1.2.9
* smartsocket 升为 1.5.32

### 2.4.1
* 新增 solon.web.servlet.jakarta 插件（用于 war 打包时，支持 tomcat10, jetty11 等 jakarta.servlet 容器）
* 新增 solon-admin-client 插件
* 新增 solon-admin-server 插件
* 新增 solon-admin-server-ui 插件
* 增加 Props::getByKeys、getOrDefault 接口
* 调整 EventBus::push 标为弃用，添加 ::publish 代之
* 调整 AopContext::beanAroundXxx 标为弃用，添加 ::beanInterceptorXxx 代之
* 调整 Around 相关名改为 Interceptor，原名标为弃用
* 优化 ProxyComponent 注解属性调整成与 Component 注解一样，支持用 tags 查找
* 优化 solon.boot 信号启动的执行时机后延
* 优化 solon.scheduling 插件的 retry 的兜底设计
* 优化 EventBus 增加订阅排序支持
* 修复 bean 所有字段只读且无 public 构造器时，会异常的问题
* wood 升为 1.1.7
* snack3 升为 3.2.73
* beetlsql 升为 3.23.5-RELEASE

### 2.4.0
* 新增 solon-openapi2-knife4j 插件，替代 solon-swagger2-knife4j
* 增加 server.socket.ssl.* 配置
* 增加 server.websocket.ssl.* 配置
* 增加 Context::isSecure 接口
* 增加 ChainManager::defExecuteHandler 接口
* 增加 ChainManager 对 SessionStateFactory 的管理，原管理方式移除
* 增加 `@Header` String[] xxx 注入支持
* 增加 Converter 体系，一般用于基础类型的通用转换
* 增加 日志服务孵化机制，可在打印前进行完成格式配置
* 增加 solon.boot.socketd.netty 插件对 ssl 的支持
* 增加 solon.boot.websocket.netty 插件对 ssl 的支持
* 优化 RouterListener 取消自己的线程池，改用 RunUtil
* 优化 JsonConverter 标为弃用，统一由 Converter 替代
* 优化 SocketChannelBase 标为弃用，统一由 ChannelBase 替代
* 优化 ResourceUtil 的根路径兼容性
* 优化 @Init 注解逻辑，仅对原始实例有效。保持与 LifecycleBean 相同策略
* 优化 solon.boot.smarthttp 的 ws 适配
* 优化 nacos2-solon-cloud-plugin 的适配，统一 jackson 版本
* 优化 nacos-solon-cloud-plugin 的适配，统一 jackson 版本
* 优化 LogUtil 扩展方式，改为静态扩展方式，原手动方式标为弃用
* 优化 Context::headerValues() 返回类型为 String[]
* 优化 Context::paramValues() 统一 server 相关处理逻辑
* 优化 统一 request 参数与实体字段的注入转换机制，并增加自定义转换机制
* 优化 内核日志打印顺序，由 SolonApp 实例化后再打印。以便日志格式配置先加载
* 优化 当使用 http ssl 时，服务启动打印为 https 地址
* 优化 ActionExecuteHandlerDefault::changeBody 参数结构，方便不同的序列化方案处理
* 优化 Nami 编码器匹配策略及头同步策略，尤其是仅单编码包引入时
* 优化 AbstractRoutingDataSource 的关闭处理
* 优化 ShardingDataSource 增加 Closeable 接口支持
* 调整 beetlsql-solon-plugin 插件，DbConnectionSource 改为公有，调整包结构
* 修复 solon-swagger2-knife4j 插件，递归类型的数据模型会栈溢出的问题
* 修复 solon-swagger2-knife4j 插件，相同 path 不能显示多个 method 的问题
* 修复 solon-swagger2-knife4j 插件，`List<Demo>` 风格参数，不能正常构建 json 示例
* 修复 solon-swagger2-knife4j 插件，`Page<Demo>`、`Result<Page<Demo>>` 等复杂嵌套的临时模型，不能正常构建 json 示例
* dbvisitor 升为 5.3.3
* mybatis-flex 升为 1.5.1
* sqltoy 升为 5.2.59
* fastmybatis 升为 2.8.1
* bean-searcher 升为 4.2.2
* liteflow 升为 2.10.5
* beetl 升为 3.15.7.RELEASE
* beetlsql 升为 3.23.4-RELEASE
* smarthttp 升为 1.2.8
* fastjson2 升为 2.0.35

### 2.3.8
* 增加 HttpServerConfigure::enableHttp2 接口, 默认为 false
* 增加 UploadedFile[] 注入支持
* 调整 solon.view.* 增加引擎提供者获取属性
* 调整 mqtt-solon-cloud-plugin 插件，增加获取原生 client 接口
* 调整 minio-solon-cloud-plugin 插件，minio 降为 8.2.2
* 调整 单元测试项目结构?
* 调整 IpUtil 增加扩展 ip 实现，可替换内部的实现
* 调整 solon.docs 插件，在无参 post 时，不再自动转成 get；增加 @Api::value() 做为 tags
* 调整 Gateway:register 执行时机为容器启动时，使注册时可使用注入字段
* 修复 solon.boot.undertow 在客户端进程关闭时，不能触发 onClose 事件的问题
* 修复 solon.boot.smarthttp 在客户端进程关闭时，不能触发 onClose 事件的问题
* 优化 sqltoy-solon-plugin 适配代码，增加 LightDao 的支持
* 优化 hasor-solon-plugin 插件适配，支持最新状态
* sqltoy 升为 5.2.58
* mybatis-flex 升为 1.4.7


### 2.3.7
* 优化 nami 解码器的渲染要求策略
* 优化 solon.boot.websocket 异步发送机制
* 优化 solon.boot.websocket.netty 异步发送机制
* 调整 RunUtil 增加线程池名
* 调整 StringSerializerRender 开放 serializer 属性
* 调整 app.router().caseSensitive 默认为 true
* 增加 CloudEvent 注解在函数上时，支持 AOP 扩展
* 增加 solon.docs 插件，对网关开发模式的支持
* 增加 solon.boot.socketd.jdksocket 插件，对 ssl 的支持
* 增加 server.http.ssl.* 配置
* 增加 NamiMapping、NamiBody 注解
* 增加 序列化接口（在渲染之外），可以外面复用（从 StringSerializerRender 开放 serializer）
* 增加 Context::headersMap, Context::headerValues 接口
* 修复 solon.serialization.jackson 在某些情况下，序列化 null 会出错的问题
* 修复 solon.boot.jetty 不能使用资源文件做 ssl 密钥文件的问题
* wood 升为 1.1.5
* smarthttp 升为 1.2.6
* smartsocket 升为 1.5.31
* sqltoy 升为 5.2.57
* mybatis-flex 升为 1.4.4
* beetlsql 升为 3.23.2-RELEASE

### 2.3.6
* 修复 异步监听可能为null的问题
* 调整 异步超时默认为30秒（-1L为不限，0L为默认）

### 2.3.5
* 新增 solon.boot.websocket.netty 插件
* 增加 solon.boot.jdkhttp 插件，虚拟异步支持（进而支持响应式接口）
* 增加 solon.boot.jlhttp 插件，虚拟异步支持（进而支持响应式接口）
* 调整 solon.web.flux 插件，出错时自动结束异步
* 调整 Context 异步接口机制，只能被调用一次
* 优化 solon.web.sse 插件，改为纯异步机制（所有 solon.boot.http 已支持异步）
* 优化 SmartHttp Context 异步接口机制
* 优化 Servlet 启动打印信息
* 优化 Context:forward 在有 context-path 时的处理
* 优化 ContextPathFilter 对根地址的映射处理
* 修复 pathNew 多次执行后 ContextPathFilter 会失效的问题

### 2.3.4
* 新增 solon.web.sse 插件（sse: Server Send Events）
* 新增 solon.web.flux 插件，响应式web开发插件（适用于支持异步的 http server）
* 增加 mybatis-plus-solon-plugin 插件，原生编译支持（GraalVM Native Image）
* 增加 solon.scheduling 插件，简单的 Retry 功能
* 增加 solon.validation 一次性验证所有字段的支持
* 增加 solon.docs 插件，支持字段 transient 排除
* 增加 Context 异步控制接口，为响应式web开发提供支持
* 增加 ActionReturnHandler 接口，之后特别的返回结果可定制。为响应式web开发提供支持
* 增加 ActionExecuteHandler 接口（替代旧的 ActionExecutor），并交由 chainManager 管理
* 增加 jetty、undertow 对 Context 异步适配
* 增加 Inject("{xxx:def}") 默认值转集合和数组支持
* 完善 mybatis-solon-plugin  原生编译支持
* 完善 solon.aot 增加 lambda 序列化支持
* 修复 请求路径动态变化后，路径变量获取失败的问题
* guava 升为 32.0.0-jre
* smarthttp 升为 1.2.4
* smartsocket 升为 1.5.30
* dromara-plugins 升为 0.0.9
* forest 升为 1.5.32
* mybatis-flex 升为 1.4.1
* sqltoy 升为 5.2.54
* hutool 升为 5.8.20
* fastjson2 升为 2.0.34
* java-websocket 升为 1.5.3

### 2.3.3
* 调整 solon-cloud-alibaba 快捷包 改用 nacos2,rocketmq5
* 调整 file-s3-solon-cloud-plugin 插件，不排除 aws-java-sdk-s3（之前为排除）
* 添加 dromara-plugins 所有插件的版本管理
* 添加 PathRule 工具类
* 添加 PathLimiter 用于限制 RouterInterceptor 的范围
* 添加 MybaitsAdapter::getMapper 增加缓存处理
* 添加 maxHeaderSize(8k), maxBodySize(2m) 为 server 统一默认配置，不然会出 readToken 错误
* 添加 Context::sessionOrDefault(),headerOrDefault(),paramOrDefault() 接口
* 添加 ChainManager::getFilterNodes(),getInterceptorNodes() 接口
* 优化 AsmProxy 代理类的缓存机制（简化）
* 优化 Utils::firstOrNull 增加 null 判断
* 优化 ClassUtil 的异常处理
* 优化 Context::filesMap() 改抛 IOException 异常
* 优化 Context::param(key,def) 处理
* wood 升为 1.1.3
* nacos2 升为 2.2.3
* rocketmq5 升为 5.0.5
* dubbo3 升为 3.2.2

### 2.3.2
* 调整 mybaits-solon-plugin 插件，添加 configuration.mapperVerifyEnabled 配置（控制是否启用 mapper 校验）
* 调整 solon.docs 插件，将非 `@Body` model 进行字段拆解
* activemq 升为 5.15.9

### 2.3.1
* 新增 solon.data.shardingds 插件
* 新增 redisson-solon-plugin 插件
* 调整 solon-swagger2-knife4j 插件的优先级，以便控制 enableDoc
* 调整 mybatis-solon-plugin 插件，增加 aot 处理适配（支持原生编译了）
* 调整 mybatis 适配增加 isMapper 检测接口
* 调整 开放 bean 内部形态注册的限制，之前只能用普通组件注解
* 调整 应用启动时的事件改由 push 推送（之前是 pushTry）
* 调整 XxxCacheService 增加新的构造函数
* 调整 jlhttp Part 的 body string 大小限制改为 MAX_BODY_SIZE（之前为 MAX_HEADER_SIZE）
* 调整 smarthttp Part 的 body string 大小限制改为 MAX_BODY_SIZE（之前为 MAX_HEADER_SIZE）
* 调整 jdkhttp Part 的 body string 大小限制改为 MAX_BODY_SIZE（之前为 MAX_HEADER_SIZE）
* 增加 Context::filesMap() 接口
* bean-searcher 升为 4.2.0
* sqltoy 升为 5.2.51
* redisson 升为 3.21.0
* netty 升为 4.1.75.Final
* fastjson2 升为 2.0.33
* mybatis-flex 升为 1.3.2
* fastmybatis 升为 2.6.1

### 2.3.0
* 升级 日志体系到 slf4j 2.x（如果冲突，排除旧的 1.x）!!!
* 新增 solon.docs 插件!!!
* 新增 solon-swagger2-knife4j 插件!!!
* 新增 zipkin-solon-cloud-plugin 插件
* 新增 etcd-solon-cloud-plugin 插件
* 新增 fastmybatis-solon-plugin 插件
* 弃用 `@Dao` `@Repository` `@Service` （改由 `@ProxyComponent` 替代）
* 增加 ProxyUtil::attach(ctx,clz,obj,handler) 接口
* 增加 aot 对 methodWrap 参数的自动登记处理
* 修复 AopContext::getWrapsOfType 返回结果失真的问题
* 调整 mybatis 按包名扫描只对 `@Mapper` 注解的接口有效（避免其它接口误扫）
* slf4j 升为 2.0.7
* log4j2 升为 2.20.0（基于 slf4j 2.x）
* logback 升为 1.3.7（基于 slf4j 2.x）
* sqltoy 升为  5.2.48
* mybatis-flex 升为 1.2.9
* beetlsql 升为 3.23.1-RELEASE
* wood 升为 1.1.2
* redisx 升为 1.4.8
* water 升为 2.11.0
* protobuf 升为 3.22.3
* jackson 升为 2.14.3
* dubbo3 升为 3.2.1
* grpc 升为 1.54.1
* zookeeper 升为 3.7.1
* nacos2-client 升为 2.2.2
* nacos1-client 升为 1.4.5
* jaeger 升为 1.8.1

### 2.2.20
* 添加 Props::bindTo 接口
* 优化 日志框架，在 window 下的彩色打印支持
* snack3 升为 3.2.72

### 2.2.19
* 调整 日志框架，增加 window 下的彩色打印支持
* 调整 bio maxThreads core x 32
* 调整 maxBodySize,maxFileSize 用大于号做判断 (undertow 之外，maxBodySize 相当于 maxFromContentSize)
* 修复 solon.boot.undertow 的 maxBodySize 配置无效问题
* 修复 solon.boot.smarthttp + ssl 在某些情况下会慢的问题
* 修复 maxFileSize 过大会超界的问题
* smartboot.socket 升为 1.5.28
* smartboot.http 升为 1.2.1
* snack3 升为 3.2.71

### 2.2.18
* 增加 solon.boot.jdkhttp 对 HttpServerConfigure 接口的支持，方便添加端口及ssl的编程控制
* 增加 solon.boot.jlhttp 对 HttpServerConfigure 接口的支持，方便添加端口及ssl的编程控制
* 增加 solon.boot.smarthttp 对 HttpServerConfigure 接口的支持，方便添加端口及ssl的编程控制
* 增加 solon.boot.jetty 对 HttpServerConfigure 接口的支持，方便添加端口及ssl的编程控制
* 增加 solon.boot.undertow 对 HttpServerConfigure 接口的支持，方便添加端口及ssl的编程控制
* 增加 solon.logging.logback 插件，文件扩展名配置（.log, .log.gz）
* 增加 solon.logging.log4j2 插件，文件扩展名配置（.log, .log.gz）
* snack3 升为 3.2.67

### 2.2.17
* 增加 @Inject 注入 bean 的 required 检测支持
* 增加 缓存服务适配类可传入客户端的构建函数
* 增加 DynamicDataSource 无参构造函数，方便定制
* 增加 CloudDiscoveryService 代理类，以支持发现代理的配置
* smart-socket 升级为 1.5.27
* smart-http 升级为 1.2.0
* fastjson2 升为 2.0.31

### 2.2.16
* 增加 ctx:pathAsLower() 接口
* 增加 solon.boot.undertow 原生配置申明
* 增加 solon.sessionstate.jwt 原生配置申明
* 增加 solon.logging.logback 原生配置申明
* 增加 solon.logging.log4j2 原生配置申明
* 增加 solon cloud 发现代理的配置支持（在 k8s 环境，可直接转发到 k8s sev 上）
* 调整 aot 注册时对空类名进行过滤
* 增强 solon.boot.jetty 在原生运行时兼容性
* 增强 solon.boot.undertow 在原生运行时兼容性
* 调整 Context::commit 函数位置，迁移到别处
* 调整 预热工具在 aot 时跳过执行
* 调整 MethodWrap 和 BeanWrap 的两个异常解包处理
* 解决 solon.aot 部分类型不能解析识别的问题
* polaris 升为 1.12.2
* beetl 升为 3.15.4.RELEASE
* beetlsql 升为 3.22.0-RELEASE
* sqltoy 升为 5.2.45
* liteflow 升为 2.10.2
* forest 升为 1.5.31
* dbvisitor 升为 5.3.1

### 2.2.15
* 增加 aot 配置注入实体的自动登记处理
* 增加 aot 函数包装的返回可序列化类型的自动登记处理
* 增加 aot 有注入jdk代理的自动登记处理
* 增加 aot jdbc 驱动的的自动登记处理
* 增加 aot 通用反射代理的自动登记处理
* 调整 aot 完成后的关闭处理方式

### 2.2.14
* 增加 afterInjection() 对注入的检测及非必须注入的支持
* 增加 @Bean demo(...) 对注入的检测及非必须注入的支持
* 增加 okhttp 原生编译支持配置
* 增加 solon.scheduling.quartz 原生编译支持配置
* 增加 water-solon-cloud-plugin 原生编译支持配置
* 增加 solon.aot 功能总体上实现（细节优需优化）
* 增加 VarGater::check 接口，用于在容器启动时做收集检测
* 增加 Router::caseSensitive 接口，用于设定区分大小写
* 调整 solon.test 增加代理类的字段注入
* 调整 solon.proxy.apt 的代理生成能力，转移到 solon.aot
* 调整 solon aot 时，取消 Runtime.getRuntime().addShutdownHook
* 调整 solon cloud 在 aot 时不做注册处理
* 调整 mybatis 适配的环境id规则保持也数据源bean同名
* 调整 Fastjson2Serializer 增加内部的上下文复用
* 调整 solon 路径分析器添加区分大小写的控制
* 调整 solon-maven-plugin 打包时，排除 provided 的包；支持配置 include 和 exclude 配置
* 调整 sa-token-solon-plugin 全局过滤器的 BeforeAuth 认证设为不受 includeList 与 excludeList 的限制，所有请求都会进入
* 修复 native 运行时，可能出现找不到资源文件而报错
* mybatis-flex 升为 1.2.0
* wood 升为 1.1.1

### 2.2.13
* 调整 solon.scheduling.quartz 管理机制
* 调整 solon.scheduling.simple 管理机制
* 调整 solon.aot 将 native 元数据生成到对应主类的包下
* 调整 solon bean 允许有多个其它基础形态
* 调整 EnjoyRender 增加获取 Engine 对象方法
* 调整 aot 时不启动 http 等通讯服务
* 移除 mybatis-flex-solon-plugin 的实现代码，改为引用第三方
* mybatis-flex 升为 1.1.8
* dubbo3 升为 3.2.0
* fastjson2 升为 2.0.29

### 2.2.12
* 新增 solon.aot 插件
* 新增 simplejavamail-solon-plugin 插件
* 新增 sms4j-solon-plugin 插件
* 增加 Utils::isEmpty(Collection s) 接口
* 增加 solon cloud i18n 默认值配置支持
* 修复 @Inject("${demo:hello}") 有默认值的配置注入不能自动更新的问题
* mybatis-flex 升为 1.1.3
* mybatis-plus 升为 3.5.3.1
* undertow 升为 2.2.24.Final
* jetty 升为 9.4.51.v20230217
* fastjson2 升为 2.0.28
* snack3 升为 3.2.66，支持 Charset 类型注入

### 2.2.11
* 新增 pulsar2-solon-plugin 插件
* 新增 drools-solon-plugin 插件
* 新增 solon.web.sdl 插件替代 solon.web.sso（之前名字搞错了）
* 插件 solon.boot.jetty 增加 jetty-servlet 依赖（solon.boot.jetty.add.servlet 就不需要了）
* 插件 solon.boot.jlhttp 优化线程不够时会处理方式
* 插件 solon.boot.sockted.jdksocket 优化线程不够时会处理方式
* 插件 solon.boot.smarthttp 优化二级池线程不够时会处理方式
* 插件 solon-maven-plugin 打包机制，支持 scope system 包编译
* 增加 @Configuration + @Inject 支持配置变更事件的注入
* 增加 solon.cloud/Config:_Props 申明为非序列化字段
* 增加 三个 http server 通过事件扩展的支持，比如定制执行服务
* 调整 sa-token sao 适配代码，优化相同key在多线程下的并发问题
* 调整 InvocationRunnableFactory::create 允许返回为 null，即自己直接执行
* 调整 @Init 的索引策略与 LifecycleBean 相同：+1
* 调整 根路由支持 remove 监听记录
* fastjson2 升为 2.0.27
* beetlsql 升为 3.22.0-RELEASE
* redisx 升为 1.4.7

### 2.2.10
* 新增 mybatis-flex-solon-plugin 插件
* 插件 solon.cloud.tracing 将 traceId 和 spanId 存入日志全局变量，方便在日志中打印
* 插件 solon.scheduling 增加 @Async 运行器创建扩展机制
* 调整 "@Init will be discarded" 打印时机，改由 debug 时打印
* 调整 solon.web.sso 插件的用法
* 调整 mybatis 相关的适配包名，基于2.0规范
* 调整 @SolonTest 注解为可继承
* 优化 mybatis-solon-plugin 去掉关闭连接时的 connection.setAutoCommit(true)。此段代码会导致查询速度增加20~30ms
* 优化 solon.boot.jlhttp 插件 JlHttpServer 类，实现接口公用性!!!
* 优化 solon.boot.jdkhttp 插件 JdkHttpServer 类，实现接口公用性!!!
* 优化 solon.boot.smarthttp 插件 SmHttpServer 类，实现接口公用性!!!
* snack3 升为 3.2.65，支持 File 类型注入

### 2.2.9
* 新增 solon.web.sso 插件
* 新增 wxjava-xxx-solon-plugin 插件
* 插件 solon.serialization.jackson 增加几个默认特性，增强与fastjson的兼容度???
* 插件 solon.serialization.snack3 增加嵌套泛型支持 List<List<Long>>、Map<String,List<Long>>
* 优化 jlhttp 状态码超界的处理
* 优化 solon.cloud.tracing @Tracing 增加全类名的记录
* 优化 时区解析统一由 ZoneId 处理???
* 调整 solon.serialization.snack3 从 solon-lib 移到 solon-api，更方便 json 的选择
* 增加 "/WEB-INF/templates/" 后端模板目录约定支持
* mybatis 升为 3.5.13
* fastjson2 升为 2.0.26
* beetl 升为 3.15.1.RELEASE
* beetlsql 升为 3.21.0-RELEASE
* snack3 升为 3.2.64

### 2.2.8
* 插件 dubbo-solon-plugin 增加 DubboFilterTracing 类
* 插件 solon.scheduling.simple 增加单计划任务的起停控制
* 增加 solon.boot.jlhttp 插件 JlHttpServer 类，提供可复用支持!!!
* 增加 solon.boot.jdkhttp 插件 JdkHttpServer 类，提供可复用支持!!!
* 增加 solon.boot.smarthttp 插件 SmHttpServer 类，提供可复用支持!!!
* 增加 动态代理时异常时的友好提示
* 优化 solon.boot.xxx "Server:main:" 打印
* 优化 solon.cloud.tracing Span 的 name 和 tags
* 优化 LifecycleBean 组件增加自动排位!!!
* 优化 ctx.realIp() 获取算法
* wood 升为 1.1.0 （拆分为： wood 和 wood.plus）
* water 升为 2.20.3 （配套 wood 拆分）

### 2.2.7
* 增加 @Path 替代 @PathVar（简短些），不过这个注解本身意义不大
* 增加 ResourceUtil::findResource 接口(ssl.keyStore 改用此接口)
* 增加 "application.xxx" 配置的弃用警告日志
* 增加 "solon.config" 多文件与内外文件支持
* 增加 "solon.config.add"(添加外部) 配置，用于替代 "solon.config"
* 增加 "solon.config.load"(加载内部) 配置，支持数组配置
* 增加 Props::getMap 接口，替代 Props::getXmap
* 调整 file-s3-solon-cloud-plugin 插件， "file.default"改为动态获取，增加bucket接口
* 调整 maven 版本管理，由 ${xxx.ver} 改为 ${xxx.version}
* 优化 请求参数 required 的提示
* 优化 Props::getProp 减少 forEach 次数
* 修复 solon.scheduling.simple 插件，在启动卡时后不能运行 cron=* * * * * ?
* 修复 AopContext::ProxyComponent 不能被 copyto 的问题
* sqltoy 升为 5.2.41
* wood 升为 1.0.9
* snack3 升为 3.2.62
* hutool 升为 5.8.16
* liteflow 升为 2.10.1

### v2.2.6
* 调整 solon.scheduling.quartz 增加非单例运行支持，method 支持拦截
* 调整 solon.scheduling.simple 增加非单例运行支持，method 支持拦截
* 调整 solon.web.staticfiles 改为固定长度输出
* 调整 CloudJob 增加非单例运行支持，method 支持拦截
* 增加 ModelAndView 操作便利性
* 增加 CloudLoadBalance 对策略机制的支持，可自定义
* 增加 CloudClient::loadBalance() 接口
* 增加 ids=t1,t2,t3 注入 List<T> 的支持
* 增加 插件加载失败日志
* 增加 动态代理注解的兼容检测，并打印警告日志
* 移除 不必要的 @Note 注释，同时缩减内核大小
* liteflow 升为 2.10.0
* fastjson2 升为 2.0.25
* snakeyaml 升为 2.0

### v2.2.5

* 增加 GsonActionExecutor 类
* 修复 FastjsonActionExecutor 配置没启效的问题
* snack3 升为 3.2.61
* wood 升为 1.0.8
* wood 升为 2.10.2
* gson 升为 1.10.1
* jackson 升为 2.14.2

### v2.2.4

* 新增 orika-solon-plugin 插件
* 新增 solon-job 插件
* 新增 solon-web-beetl 插件
* 新增 solon-web-enjoy 插件
* 增加 VaultUtils::guard 接口
* 修复 ColonClient.configLoad(g,k) 不能实时同步配置的问题
* beetlsql 升为 3.20.3-RELEASE
* fastjson2 升为 2.0.25
* snack3 升为 3.2.60
* hutool 升为 5.8.15
* sqltoy 升为 5.2.39
* beetlsql 升为 3.20.3-RELEASE

### v2.2.3

* 新增 easy-trans-solon-plugin 插件（引用）
* 增加 应用元信息 "solon.app.meta" 配置支持
* 增加 应用标签 "solon.app.tags" 配置支持
* 增加 nacos 客户端的自由配置支持（如：clusterName,contextPath）
* 增加 HealthHandler 自动注册支持，即组件模式
* 调整 sa-token 的集成逻辑
* 修正 LOG_SERVER 属性映射缺陷（pr）

### v2.2.2

* 新增 bean-searcher-solon-plugin 插件
* 优化 rocketmq5-solon-cloud-plugin 插件
* 移除 forest-solon-plugin 源码，改引用 com.dtflys.forest:forest-solon-plugin
* 移除 liteflow-solon-plugin 源码，改引用 com.yomahub:liteflow-solon-plugin
* 增加 调试模式下，模板仅在文件模式下才加载源码提供器
* 增加 用属性配置控制 solon.logging.log4j2::File 添加器是否启用！
* 增加 用属性配置控制 solon.logging.logback::File 添加器是否启用！
* 增加 "solon.logging.config" 配置支持，可将日志的配置文件移到外部
* 增加 CloudProps 关于 username/password 与 ak/sk 配置互通的支持！
* 增加 CloudProps 关于 username/password 上下级传导的支持！
* 增加 Solon::stopBlock 接口
* 调整 Solon::stop 时把 app,appMain置为null
* 调整 solon.web.static 的目录优先级（支持两个，但只让一个生效）
* 调整 让注解产生的生命周期，排序晚1个点
* 调整 让默认的 beanOnloaded 生命周期，排序为-1
* activerecord 升级为 5.0.4
* bean-searcher 升级为 4.1.2
* snack3 升级为 3.2.59
* hutool 升级为 5.8.14
* dubbo3 升级为 3.1.7
* forest 升级为 1.5.30
* rocketmq5-client 升级为 5.0.4
* smarthttp 升级为 1.1.22
* smartsocket 升级为 1.5.25
* ons-client 升级为 1.8.8.8.Final

### v2.2.1

* mybatis-solon-plugin，取消 mappers 检测异常，改为警告日志
* ContextPathFilter 增加与 cfg().serverContextPath 同步配置
* LifecycleBean 增加对 InitializingBean 的继承，使用时简便些
* 模板添加 templates 目录支持
* 移除 RenderUtil 类
* 降低内部手动添加 lifecycle 的执行排序，调为 -99 和 -98!!!
* 延迟 captcha-solon-plugin 运行时机
* 添加 SerializationConfig，为渲染器提供统一的配置帮助


### v2.2.0

* 概要：
* 1.吸收近期使用需求，增强内核能力（完善 bean 的生命周期；调整 plugin 仅定位为 spi；）
* 2.兼容的同时增强品牌个性化（增加 @SolonMain，@ProxyComponent 注解）
* 3.增加 apt 代理实现，增加 apt 生成 native 元信息配置的机制（为 native 简便打包，埋下好的基础 ）
* 
* 
* 新增 solon.proxy 插件
* 新增 solon.proxy.apt 插件
* 新增 solon.graalvm 插件
* 新增 solon.graalvm.apt 插件
* 调整 BeanInvocationHandler 内部代码，简化并增加 AptProxy 调用
* 调整 dateAsFormat 配置增加对 LocalDate 和 LocalDateTime 的支持
* 调整 Plugin::Init 标为弃用, 并由 InitializingBean 接口接替
* 调整 Plugin 接口不再做为组件形态，有生命周期需求的可改为 LifecycleBean 接口
* 调整 Plugin Spi 实例化改为 Bean 模式，之前为不能注入的 New 模式
* 调整 AopContext 标注 beanOnloaded 为弃用。事件概念调整为容器内部的生命周期概念
* 调整 AopContext 增加 start(),stop(),lifecycle() 接口；强化生命周期管理概念
* 调整 Lifecycle 增加可异常选择，并标注 @FunctionalInterface
* 调整 调整打包时主函数的提示信息
* 增加 应用属性配置内部引用增加默认值支持及环境变量引用
* 增加 @ProxyComponent 注解，使用时强依赖于 solon.proxy 插件
* 增加 @SolonMain 主解，作为 apt 生成 Graalvm Native 元信息配置的入口
* 增加 apt 代理实现方式（做为 asm 实现的补充），为全功能实现 Graalvm Native 打包提供支持
* 增加 InitializingBean 接口
* 增加 LifecycleBean 接口
* 增加 ClassUtil 工具类
* sqltoy 升级为 5.2.37

### v2.1.4

* 新增 bean-searcher-solon-plugin 插件
* Props::getByParse 增加默认值支持
* Props::getByExpr 增加默认值支持
* Condition 注解重新规范条件属性名
* AopContext 增加 Condition::onMissingBean 条件属性支持
* 升级 snack3 为 3.2.54 ，支持 kotlin data


### v2.1.3

* 插件 solon-maven-plugin 增加 jdk19 支持
* 插件 snowflake-id-solon-cloud-plugin 增加 workId 可配置
* 插件 solon.test 增加 AbstractHttpTester 类，名字比 HttpTestBase 顺眼些
* 插件 solon.test Http 测试器 增加 http(int port) 接口，方便本机所有http端口测试（比如 mock server）
* 插件 solon-test-junit4 增加 mockito-core 依赖，方便 mock 测试
* 增加 新的类存在检测方式 Utils.hasClass(() -> AuthUtil.class)
* 修复 activerecord-solon-pllugin::修复在 ActiveRecordPlugin::start 前不能注入 DbPro 的问题
* 修复 solon.cache.redisson 默认缓存时间没有生效的问题，及缓存键头没用起来的问题
* 调整 solon.cache.jedis 缓存键以':'为间隔
* 优化 solon.test 排除只读类成为代理类
* 优化 Gateway 根据路由记录数量，自动切换主处理
* 优化 http 输出长度模式 与 chunked模式 的自动切换
* 升级 mockito 为 4.11.0
* 升级 beetl 为 3.14.1.RELEASE
* 升级 sqltoy 为 5.2.34


### v2.1.2
* 新增 solon.health.detector 插件
* 增强 detector-solon-plugin 扩展能力
* 增强 mybatis-solon-plugin 的 typeAliases,typeHandlers,mappers 表达式配置能力
* 增加 ResourceUtil 提供资源路径表达式分析能力
* 调整 local-solon-cloud-plugin 本地文件路径规范
* 修复 mybatis-solon-plugin 与 solon-maven-plugin 打包插件的兼容性问题

### v2.1.0
* 新增 activemq-solon-cloud-plugin 插件
* 新增 solon.logging.log4j2（复制于 log4j2-solon-plugin）
* 新增 solon.logging.logback（复制于 logback-solon-plugin）
* 插件 beetlsql-solon-plugin 升级 beetlsql 为 3.20.0
* 插件 sqltoy-solon-plugin 升级 sqltoy 为 5.2.32
* 插件 dbvisitor-solon-plugin 升级 dbvisitor 为 5.2.1
* 插件 sa-token-solon-plugin 添加 SaJsonTemplate 实现类
* 增加 @Condition 注解，提供Com类与Bean函数的过滤支持!!!
* 增加 AppPrestopEndEvent，AppPrestopEndEvent 事件!!!
* 增加 配置元信息 solon-configuration-metadata.json 规范与支持
* 增加 EventBus.pushTry 接口
* 增加 solon.view.beetl 对 -debug=1 的支持
* 增加 solon.view.enjoy 对 -debug=1 的支持
* 优化 安全停止与延时的配置(增加新的启动参数：stop.safe,和应用配置：solon.stop.safe)


### v2.0.0

* 说明：第一个版只删除弃用代码，不加新功能
*
* 调整 solon//
  * 删除 Aop；由 Solon.context() 替代
  * 删除 Bean:attr，Component:attr
  * 删除 BeanLoadEndEvent，PluginLoadEndEvent；由 AppBeanLoadEndEvent，AppPluginLoadEndEvent 替代
  * 删除 Utils.parallel()...等几个弃用接口；由 RunUtil 替代
  * 删除 Solon.global()；由 Solon.app() 替代
  * 删除 SolonApp::port()；由 Solon.cfg().serverPort() 替代
  * 删除 SolonApp::enableSafeStop()；由 Solon.cfg().enableSafeStop() 替代
  * 删除 AopContext::getProps()；由 ::cfg() 替代
  * 删除 AopContext::getWrapAsyn()；由 ::getWrapAsync() 替代
  * 删除 AopContext::subWrap()；由 ::subWrapsOfType() 替代
  * 删除 AopContext::subBean()；由 ::subBeansOfType() 替代
  * 删除 AopContext::getBeanAsyn()；由::getBeanAsync() 替代
  * 删除 Solon.cfg().version()；由 Solon.version() 替代
  * 删除 EventBus::pushAsyn()；由 pushAsync() 替代
  * 删除 PrintUtil::debug()，::info() 等...；由 LogUtil 替代
  * 删除 @Mapping::before,after,index 属性；由 @Before,@After 或 RouterInterceptor 或 Solon.app().before(),after() 替代
  * 删除 "solon.profiles.active" 应用配置（只在某版临时出现过）；由 "solon.env" 替代
  * 删除 "solon.extend.config" 应用配置（只在某版临时出现过）；由 "solon.config" 替代
  * 删除 "solon.encoding.request" 应用配置（只在某版临时出现过）；由 "server.request.encoding" 替代
  * 删除 "solon.encoding.response" 应用配置（只在某版临时出现过）；由 "server.request.response" 替代
  *
  * 调整 DownloadedFile，UploadedFile 字段改为私有；由属性替代
* 调整 solon.i18n//
  * 删除 I18nBundle::toMap()；由 ::toProp() 替代
* 调整 solon.web.staticfiles//
  * 删除 StaticMappings::add(string1,bool2,repository3) 接口；由 StaticMappings::add(string1,repository2) 替代
  * 说明 string1 ，有'/'结尾表示目录，无'/'结尾表示单文件
* 调整 solon.web.cors//
  * 删除 ..extend.cores 包；由 ..web.cors 包替代
* 调整 solon.cloud//
  * 删除 Media::bodyAsByts()..；由 ::bodyAsBytes() 替代
* 调整 solon.cloud.httputils//
  * 删除 cloud.HttpUtils::asShortHttp()..；由 ::timeout() 替代
* 调整 solon.test//
  * 删除 test.HttpUtils::exec2()..；由 ::execAsCode()..替代
* 调整 solon.boot//
  * 删除 SessionStateBase/cookie[SOLONID2]
* 调整 mybatis-solon-plugin//
  * 删除 org.apache.ibatis.ext.solon.Db；由 ..solon.annotation.Db 替代
* 调整 beetlsql-solon-plugin//
  * 删除 org.beetl.sql.ext.solon.Db；由 ..solon.annotation.Db 替代
* 调整 sa-token-solon-plugin//
  * 删除 SaTokenPathFilter 类，由 SaTokenFilter 替代
  * 删除 SaTokenPathInterceptor 类，由 SaTokenInterceptor 替代
* 删除插件 httputils-solon-cloud-plugin；由 solon.cloud.httputils 替代
* 删除插件 solon.extend.stop；由 solon.web.stop 替代
* 删除插件 solon.extend.async；由 solon.scheduling 替代
* 删除插件 solon.schedule；由 solon.scheduling.simple 替代
* 删除插件 solon.extend.retry
* 删除插件 solon.extend.jsr330
* 删除插件 solon.extend.jsr303
* 删除插件 solon.logging.impl；由 solon.logging.simple 替代
*
* 新增插件 powerjob-solon-plugin
* 新增插件 powerjob-solon-cloud-plugin（支持 solon cloud job 标准）
*
* 调整 solon.scheduling/JobManger 添加更多注册时检测
* 调整 solon.banner/banner.txt 自定义默认机制
* 调整 sa-token-solon-plugin/isPrint 处理机制
* 调整 sa-token-solon-plugin 增加对 sso,oauth2 两模块的适配
* 调整 nami 添加 ContentTypes 类，提供便利的 content-type 常量
