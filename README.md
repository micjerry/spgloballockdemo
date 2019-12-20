验证Spring Global Lock方案


global-lock-demo 验证基于redis集群的全局锁;

   
验证：
   
    (1) 执行global-lock-demo 实例1，端口8080
   
    (2) 执行global-lock-demo 实例2，端口8090
   
    (3) 快速连续执行
        curl http://localhost:8080/globallock/test
        curl http://localhost:8090/globallock/test
        可以看到一个获取锁成功，一个失败
   
 
