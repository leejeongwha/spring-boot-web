application.properties 파일 key값 리스트
- http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

1. 배포 참고 스크립트
http://stackoverflow.com/questions/11203483/run-a-java-application-as-a-service-on-linux

2. jar 파일 복사
rcp demo-0.0.1-SNAPSHOT.jar irteam@10.99.197.76:/home1/irteam/deploy/spring-boot-web

3. jar 파일 실행
nohup /home1/irteam/apps/jdk/bin/java -jar /home1/irteam/deploy/spring-boot-web/demo-0.0.1-SNAPSHOT.jar /tmp 2>> /dev/null >> /dev/null & echo $! > /home1/irteam/deploy/spring-boot-web/service.pid

5. 아파치(서비스의 경우 mod_proxy_ajp 사용하면 될듯)
LoadModule proxy_module       modules/mod_proxy.so
LoadModule proxy_balancer_module   modules/mod_proxy_balancer.so
LoadModule proxy_http_module   modules/mod_proxy_http.so

NameVirtualHost *:80
<VirtualHost *:80>
    ServerName example.com

    ProxyRequests Off
    <Proxy *>
        Order deny,allow
        Allow from all
    </Proxy>

    ProxyPass / http://localhost:8080/
    ProxyPassReverse / http://localhost:8080/
    <Location />
        Order allow,deny
        Allow from all
    </Location>
</VirtualHost>
