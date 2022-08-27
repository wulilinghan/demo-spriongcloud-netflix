# Spring Cloud Config 配置文件命名

|  key   | 描述  |
| ---|---|
| {label}         |分支名称|
| {application} |对应本地配置"spring.application.name"|
| {profile}     |对于本地配置"spring.profiles.active"（逗号分隔列表）|

```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

# demo-sc-config-server
- http://localhost:8888/sc-config-server/dev/main
- http://localhost:8888/main/sc-config-server-dev.properties
# sc-client-hello
- http://localhost:8888/sc-client-hello/dev/main
- http://localhost:8888/main/sc-client-hello-dev.properties