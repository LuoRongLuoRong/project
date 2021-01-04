# OOAD Project

文件结构



```
pom.xml
src
├───main
│   ├───java
│   │   └───com.ooad.project
│   │       │   ProjectApplication.java
│   │       ├───controller
│   │       │       MyController.java
│   │       ├───model
│   │       │       User.java
│   │       ├───repository
│   │       │       UserRepository.java
│   │       └───service
│   │               UserService.java
│   └───resources
└───test
    └───java
```



##### 1. Maven 构建文件：pom.xml

`spring-boot-starter-web`是使用 Spring MVC 构建 Web（包括 RESTful）应用的入门程序。

`spring-boot-starter-data-jpa`是将 Spring Data JPA 与 Hibernate 结合使用的入门工具。

`spring-boot-starter-test` 是测试用。

`lombok` 简化部分方法，比如 getter、setter 和 toString 等。



`UserRepository`从`CrudRepository`延伸。 它提供了实体的类型及其主键。







