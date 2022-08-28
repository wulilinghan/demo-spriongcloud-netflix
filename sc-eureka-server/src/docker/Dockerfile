FROM openjdk:8-jdk-alpine

#定义标签
LABEL github="https://github.com/wulilinghan/demo-spriongcloud-netflix"
LABEL version="1.0.0"
LABEL maintainer="1902325071@qq.com"

# jvm参数
ENV JVM_OPTS "-Xmx128m -Xms128m -Xmn32m -Xss512k  -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=\$WORKDIR/error -Duser.timezone=Asia/Shanghai"
# 项目参数
ENV JAVA_OPTS ""
ENV WORKDIR "/app/eureka-server"
 # 设置时区
ENV TZ Asia/Shanghai

#挂载error目录
VOLUME /app/eureka-server/error

RUN mkdir -p $WORKDIR

# 设置工作目录
WORKDIR $WORKDIR
# 将tomcat拷贝到工作目录
ADD *.jar sc-eureka-server.jar
# 暴露端口
EXPOSE 9101
# 启动eureka
CMD java $JVM_OPTS $JAVA_OPTS -jar sc-eureka-server.jar