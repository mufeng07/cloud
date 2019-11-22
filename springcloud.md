# 一、eureka（服务注册中心）

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

```java
@SpringBootApplication
@EnableEurekaServer
public class StartCloudEureka1Application {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudEureka1Application.class,args);
    }
}
```

服务端：

```yaml
#Eureka实例名，集群中根据这里相互识别
eureka:
  instance:
    hostname: eureka
#客户端
  client:
#是否开启注册服务，因为这里如果为true表示自己注册自己，而自己就是一个服务注册方，没必要自己注册自己
    register-with-eureka: false
#是否拉取服务列表，这里我只提供服务给别的服务。
    fetch-registry: false
#注册中心地址
    service-url:
      defaultZone: http://localhost:8888/eureka/
```

客户端：

```java
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class StartCloudWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudWebApplication.class,args);
        log.info("start success ......");
    }
}
```

```yaml
eureka:
#客户端
  client:
#注册中心地址
    service-url:
      defaultZone: http://localhost:8888/eureka/
```

### Eureka服务端

Eureka服务端，即服务注册中心。它同其他服务注册中心一样，支持高可用配置。依托于强一致性提供良好的服务实例可用性，可以应对多种不同的故障场景。

Eureka服务端支持集群模式部署，当集群中有分片发生故障的时候，Eureka会自动转入自我保护模式。它允许在分片发生故障的时候继续提供服务的发现和注册，当故障分配恢复时，集群中的其他分片会把他们的状态再次同步回来。集群中的的不同服务注册中心通过异步模式互相复制各自的状态，这也意味着在给定的时间点每个实例关于所有服务的状态可能存在不一致的现象。

### Eureka客户端

Eureka客户端，途中的即服务提供者，主要处理服务的注册和发现。客户端服务通过注册和参数配置的方式，嵌入在客户端应用程序的代码中。在应用程序启动时，Eureka客户端向服务注册中心注册自身提供的服务，并周期性的发送心跳来更新它的服务租约。同时，他也能从服务端查询当前注册的服务信息并把它们缓存到本地并周期行的刷新服务状态。

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-boot-starter-netflix-eureka-client</artifactId>
</dependency>
```



##高可用注册中心：

```yaml
server:
  port: 9400
spring:
  application:
    name: eureka-service
#Eureka实例名，集群中根据这里相互识别
eureka:
  #客户端
  client:
    #是否开启注册服务，因为这里如果为true表示自己注册自己，而自己就是一个服务注册方，没必要自己注册自己
    #register-with-eureka: false
     #是否拉取服务列表，这里我只提供服务给别的服务。
    #fetch-registry: false
    #注册中心地址
    service-url:
       defaultZone: http://localhost:9403/eureka/
```

```yaml
server:
  port: 9403
spring:
  application:
    name: eureka-service
#Eureka实例名，集群中根据这里相互识别
eureka:
    client:
      service-url:
        defaultZone: http://localhost:9400/eureka/
```



##Eureka包含四个部分的配置

1. instance：当前Eureka Instance实例信息配置
2. client：Eureka Client客户端特性配置
3. server：Eureka Server注册中心特性配置
4. dashboard：Eureka Server注册中心仪表盘配置

###1.Eureka Instance实例信息配置

Eureka Instance的配置信息全部保存在org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean配置类里，实际上它是com.netflix.appinfo.EurekaInstanceConfig的实现类，替代了netflix的com.netflix.appinfo.CloudInstanceConfig的默认实现。

Eureka Instance的配置信息全部以eureka.instance.xxx的格式配置。

**配置列表**

appname = unknown

应用名，首先获取spring.application.name的值，如果取值为空，则取默认unknown。

appGroupName = null

应用组名

instanceEnabledOnit = false

实例注册到Eureka上是，是否立刻开启通讯。有时候应用在准备好服务之前需要一些预处理。

nonSecurePort = 80

非安全的端口

securePort = 443

安全端口

nonSecurePortEnabled = true

是否开启非安全端口通讯

securePortEnabled = false

是否开启安全端口通讯

leaseRenewalIntervalInSeconds = 30

实例续约间隔时间

leaseExpirationDurationInSeconds = 90

实例超时时间，表示最大leaseExpirationDurationInSeconds秒后没有续约，Server就认为他不可用了，随之就会将其剔除。

virtualHostName = unknown

虚拟主机名，首先获取spring.application.name的值，如果取值为空，则取默认unknown。

instanceId

注册到eureka上的唯一实例ID，不能与相同appname的其他实例重复。

secureVirtualHostName = unknown

安全虚拟主机名，首先获取spring.application.name的值，如果取值为空，则取默认unknown。

metadataMap = new HashMap();

实例元数据，可以供其他实例使用。比如spring-boot-admin在监控时，获取实例的上下文和端口。

dataCenterInfo = new MyDataCenterInfo(DataCenterInfo.Name.MyOwn);

实例部署的数据中心。如AWS、MyOwn。

ipAddress=null

实例的IP地址

statusPageUrlPath = "/actuator/info"

实例状态页相对url

statusPageUrl = null

实例状态页绝对URL

homePageUrlPath = "/"

实例主页相对URL

homePageUrl = null

实例主页绝对URL

healthCheckUrlUrlPath = "/actuator/health"

实例健康检查相对URL

healthCheckUrl = null

实例健康检查绝对URL

secureHealthCheckUrl = null

实例安全的健康检查绝对URL

namespace = "eureka"

配置属性的命名空间（Spring Cloud中被忽略）

hostname = null

主机名,不配置的时候讲根据操作系统的主机名来获取

preferIpAddress = false

是否优先使用IP地址作为主机名的标识

###2.Eureka Client客户端特性配置

Eureka Client客户端特性配置是对作为Eureka客户端的特性配置，包括Eureka注册中心，本身也是一个Eureka Client。

Eureka Client特性配置全部在org.springframework.cloud.netflix.eureka.EurekaClientConfigBean中，实际上它是com.netflix.discovery.EurekaClientConfig的实现类，替代了netxflix的默认实现。

Eureka Client客户端特性配置全部以eureka.client.xxx的格式配置。

**配置列表**

enabled=true

是否启用Eureka client。

registryFetchIntervalSeconds=30

定时从Eureka Server拉取服务注册信息的间隔时间

instanceInfoReplicationIntervalSeconds=30

定时将实例信息（如果变化了）复制到Eureka Server的间隔时间。（InstanceInfoReplicator线程）

initialInstanceInfoReplicationIntervalSeconds=40

首次将实例信息复制到Eureka Server的延迟时间。（InstanceInfoReplicator线程）

eurekaServiceUrlPollIntervalSeconds=300

拉取Eureka Server地址的间隔时间（Eureka Server有可能增减）

proxyPort=null

Eureka Server的代理端口

proxyHost=null

Eureka Server的代理主机名

proxyUserName=null

Eureka Server的代理用户名

proxyPassword=null

Eureka Server的代理密码

eurekaServerReadTimeoutSeconds=8

从Eureka Server读取信息的超时时间

eurekaServerConnectTimeoutSeconds=5

连接Eureka Server的超时时间

backupRegistryImpl=null

Eureka Client第一次启动时获取服务注册信息的调用的回溯实现。Eureka Client启动时首次会检查有没有BackupRegistry的实现类，如果有实现类，则优先从这个实现类里获取服务注册信息。

eurekaServerTotalConnections=200

Eureka client连接Eureka Server的链接总数

eurekaServerTotalConnectionsPerHost=50

Eureka client连接单台Eureka Server的链接总数

eurekaServerURLContext=null

当Eureka server的列表在DNS中时，Eureka Server的上下文路径。如http://xxxx/eureka。

eurekaServerPort=null

当Eureka server的列表在DNS中时，Eureka Server的端口。

eurekaServerDNSName=null

当Eureka server的列表在DNS中时，且要通过DNSName获取Eureka Server列表时，DNS名字。

region="us-east-1"

实例所属区域。

eurekaConnectionIdleTimeoutSeconds = 30

Eureka Client和Eureka Server之间的Http连接的空闲超时时间。

heartbeatExecutorThreadPoolSize=2

心跳（续约）执行器线程池大小。

heartbeatExecutorExponentialBackOffBound=10

心跳执行器在续约过程中超时后的再次执行续约的最大延迟倍数。默认最大延迟时间=10 * eureka.instance.leaseRenewalIntervalInSeconds

cacheRefreshExecutorThreadPoolSize=2

cacheRefreshExecutord的线程池大小（获取注册信息）

cacheRefreshExecutorExponentialBackOffBound=10

cacheRefreshExecutord的再次执行的最大延迟倍数。默认最大延迟时间=10 *eureka.client.registryFetchIntervalSeconds

serviceUrl= new HashMap();serviceUrl.put(DEFAULT_ZONE, DEFAULT_URL);

Eureka Server的分区地址。默认添加了一个defualtZone。也就是最常用的配置eureka.client.service-url.defaultZone=xxx

registerWithEureka=true

是否注册到Eureka Server。

preferSameZoneEureka=true

是否使用相同Zone下的Eureka server。

logDeltaDiff=false

是否记录Eureka Server和Eureka Client之间注册信息的差异

disableDelta=false

是否开启增量同步注册信息。

fetchRemoteRegionsRegistry=null

获取注册服务的远程地区，以逗号隔开。

availabilityZones=new HashMap()

可用分区列表。用逗号隔开。

filterOnlyUpInstances = true

是否只拉取UP状态的实例。

fetchRegistry=true

是否拉取注册信息。

shouldUnregisterOnShutdown = true

是否在停止服务的时候向Eureka Server发起Cancel指令。

shouldEnforceRegistrationAtInit = false

是否在初始化过程中注册服务。

###3.Eureka Server注册中心端配置

Eureka Server注册中心端的配置是对注册中心的特性配置。Eureka Server的配置全部在org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean里，实际上它是com.netflix.eureka.EurekaServerConfig的实现类，替代了netflix的默认实现。

Eureka Server的配置全部以eureka.server.xxx的格式进行配置。

**配置列表**

enableSelfPreservation=true

是否开启自我保护

renewalPercentThreshold = 0.85

自我保护续约百分比阀值因子。如果实际续约数小于续约数阀值，则开启自我保护

renewalThresholdUpdateIntervalMs = 15 * 60 * 1000

续约数阀值更新频率。

peerEurekaNodesUpdateIntervalMs = 10 * 60 * 1000

Eureka Server节点更新频率。

enableReplicatedRequestCompression = false

是否启用复制请求压缩。

waitTimeInMsWhenSyncEmpty=5 * 60 * 1000

当从其他节点同步实例信息为空时等待的时间。

peerNodeConnectTimeoutMs=200

节点间连接的超时时间。

peerNodeReadTimeoutMs=200

节点间读取信息的超时时间。

peerNodeTotalConnections=1000

节点间连接总数。

peerNodeTotalConnectionsPerHost = 500;

单个节点间连接总数。

peerNodeConnectionIdleTimeoutSeconds = 30;

节点间连接空闲超时时间。

retentionTimeInMSInDeltaQueue = 3 * MINUTES;

增量队列的缓存时间。

deltaRetentionTimerIntervalInMs = 30 * 1000;

清理增量队列中过期的频率。

evictionIntervalTimerInMs = 60 * 1000;

剔除任务频率。

responseCacheAutoExpirationInSeconds = 180;

注册列表缓存超时时间（当注册列表没有变化时）

responseCacheUpdateIntervalMs = 30 * 1000;

注册列表缓存更新频率。

useReadOnlyResponseCache = true;

是否开启注册列表的二级缓存。

disableDelta=false。

是否为client提供增量信息。

maxThreadsForStatusReplication = 1;

状态同步的最大线程数。

maxElementsInStatusReplicationPool = 10000;

状态同步队列的最大容量。

syncWhenTimestampDiffers = true;

当时间差异时是否同步。

registrySyncRetries = 0;

注册信息同步重试次数。

registrySyncRetryWaitMs = 30 * 1000;

注册信息同步重试期间的时间间隔。

maxElementsInPeerReplicationPool = 10000;

节点间同步事件的最大容量。

minThreadsForPeerReplication = 5;

节点间同步的最小线程数。

maxThreadsForPeerReplication = 20;

节点间同步的最大线程数。

maxTimeForReplication = 30000;

节点间同步的最大时间，单位为毫秒。

disableDeltaForRemoteRegions = false；

是否启用远程区域增量。

remoteRegionConnectTimeoutMs = 1000;

远程区域连接超时时间。

remoteRegionReadTimeoutMs = 1000;

远程区域读取超时时间。

remoteRegionTotalConnections = 1000;

远程区域最大连接数

remoteRegionTotalConnectionsPerHost = 500;

远程区域单机连接数

remoteRegionConnectionIdleTimeoutSeconds = 30;

远程区域连接空闲超时时间。

remoteRegionRegistryFetchInterval = 30;

远程区域注册信息拉取频率。

remoteRegionFetchThreadPoolSize = 20;

远程区域注册信息线程数。

###4.Eureka Server注册中心仪表盘配置**

注册中心仪表盘的配置主要是控制注册中心的可视化展示。以eureka.dashboard.xxx的格式配置。

path="/"

仪表盘访问路径

enabled=true

是否启用仪表盘

# 二、ribbon（客户端负载均衡）

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

主流的LB方案可分成两类：**

一种是集中式LB, 即在服务的消费方和提供方之间使用独立的LB设施(可以是硬件，如F5, 也可以是软件，如nginx), 由该设施负责把访问请求通过某种策略转发至服务的提供方；

　　另一种是进程内LB，将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。Ribbon就属于后者，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。

```java
@Configuration
public class EurekaRibbonConfig {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

## RestTemplate详解

### Get请求

第一种：getForEntity函数

```java
<T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
url为请求地址，responseType为请求响应body的包装类型，uriVariables为url中的参数绑定 比如：
http://USER-SERVICE/user?name={1}
<T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
url为请求地址，responseType为请求响应body的包装类型，uriVariables为url中的参数绑定 需要在占位符中指定Map中参数的key值，比如：
http://USER-SERVICE/user?name={name}
<T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType)
 url为JDK java.net包下
```

第二种;getForObject函数

```java
<T> T getForObject(String url, Class<T> responseType, Object... uriVariables)
url为请求地址，responseType为请求响应body的包装类型，uriVariables为url中的参数绑定 比如：
http://USER-SERVICE/user?name={1}
<T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
url为请求地址，responseType为请求响应body的包装类型，uriVariables为url中的参数绑定 需要在占位符中指定Map中参数的key值，比如：
http://USER-SERVICE/user?name={name}
<T> T getForObject(URI url, Class<T> responseType)
   url为JDK java.net包下
```

### POST请求

第一种：postForEntity函数

```java
 <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)
  大部分和getForEntity一致，request可以是一个普通对象，也可以是一个HttpEntity对象，若一个普通对象，非HttpEntity对象，将会把请求对象转换为一个HttpEntity对象来处理；若是一个HttpEntity对象，当成一个完成的HTTP请求对象来处理，不仅包含body，还有header
<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables)
<T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType)
```

第二种：postForObject函数

```java
<T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)
  跟getForObject类似
  <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables)
<T> T postForObject(URI url, @Nullable Object request, Class<T> responseType)
```

第三种：postForLocation函数

```java
URI postForLocation(String url, @Nullable Object request, Object... uriVariables)
返回新资源的URI
URI postForLocation(String url, @Nullable Object request, Map<String, ?> uriVariables)
URI postForLocation(URI url, @Nullable Object request)
```

### PUT请求

```java
void put(String url, @Nullable Object request, Object... uriVariables)
void put(String url, @Nullable Object request, Map<String, ?> uriVariables)
void put(URI url, @Nullable Object request)
 没有返回内容
```

### DELETE请求

```java
void delete(String url, Object... uriVariables)
void delete(String url, Map<String, ?> uriVariables)
void delete(URI url)
```



# 三、Hystrix（请求熔断与服务降级）

### Hystrix特性

　　1.请求熔断： 当Hystrix Command请求后端服务失败数量超过一定比例(默认50%), 断路器会切换到开路状态(Open). 这时所有请求会直接失败而不会发送到后端服务. 断路器保持在开路状态一段时间后(默认5秒), 自动切换到半开路状态(HALF-OPEN).

　　　 这时会判断下一次请求的返回情况, 如果请求成功, 断路器切回闭路状态(CLOSED), 否则重新切换到开路状态(OPEN). Hystrix的断路器就像我们家庭电路中的保险丝, 一旦后端服务不可用, 断路器会直接切断请求链, 避免发送大量无效请求影响系统吞吐量, 并且断路器有自我检测并恢复的能力.

　　2.服务降级：Fallback相当于是降级操作. 对于查询操作, 我们可以实现一个fallback方法, 当请求后端服务出现异常的时候, 可以使用fallback方法返回的值. fallback方法的返回值一般是设置的默认值或者来自缓存.告知后面的请求服务不可用了，不要再来了。

　　3.依赖隔离(采用舱壁模式，Docker就是舱壁模式的一种)：在Hystrix中, 主要通过线程池来实现资源隔离. 通常在使用的时候我们会根据调用的远程服务划分出多个线程池.比如说，一个服务调用两外两个服务，你如果调用两个服务都用一个线程池，那么如果一个服务卡在哪里，资源没被释放

　　　后面的请求又来了，导致后面的请求都卡在哪里等待，导致你依赖的A服务把你卡在哪里，耗尽了资源，也导致了你另外一个B服务也不可用了。这时如果依赖隔离，某一个服务调用A B两个服务，如果这时我有100个线程可用，我给A服务分配50个，给B服务分配50个，这样就算A服务挂了，

　　　我的B服务依然可以用。

　　4.请求缓存：比如一个请求过来请求我userId=1的数据，你后面的请求也过来请求同样的数据，这时我不会继续走原来的那条请求链路了，而是把第一次请求缓存过了，把第一次的请求结果返回给后面的请求。

　　5.请求合并：我依赖于某一个服务，我要调用N次，比如说查数据库的时候，我发了N条请求发了N条SQL然后拿到一堆结果，这时候我们可以把多个请求合并成一个请求，发送一个查询多条数据的SQL的请求，这样我们只需查询一次数据库，提升了效率。

# 四、feign(声明式服务调用)

Feign具有如下特性：

- 可插拔的注解支持，包括Feign注解和JAX-RS注解;
- 支持可插拔的HTTP编码器和解码器;
- 支持Hystrix和它的Fallback;
- 支持Ribbon的负载均衡;
- 支持HTTP请求和响应的压缩

# 五、zuul（网关服务）

Zuul可以通过加载动态过滤机制，从而实现以下各项功能：

　　1.验证与安全保障: 识别面向各类资源的验证要求并拒绝那些与要求不符的请求。

　　2.审查与监控: 在边缘位置追踪有意义数据及统计结果，从而为我们带来准确的生产状态结论。

　　3.动态路由: 以动态方式根据需要将请求路由至不同后端集群处。

　　4.压力测试: 逐渐增加指向集群的负载流量，从而计算性能水平。

　　5.负载分配: 为每一种负载类型分配对应容量，并弃用超出限定值的请求。

　　6.静态响应处理: 在边缘位置直接建立部分响应，从而避免其流入内部集群。

　　7.多区域弹性: 跨越AWS区域进行请求路由，旨在实现ELB使用多样化并保证边缘位置与使用者尽可能接近。

# 六、Config分布式配置管理

# 七、bus消息总线

