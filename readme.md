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



##### 2. CrudRepository

`CrudRepository`实现基本的 CRUD 操作，包括 count，delete，deleteById，save，saveAll，findById 和 findAll。

- save(entity):添加一条数据
- save(entities)：添加多条数据entities为集合
- findOne(id)：根据id查询一条数据
- exists(id)：判断id是否存在
- findAll()：查询全部数据
- delete(id)：根据id删除数据
- delete(entity)：根据一条数据的信息删除数据
- delete(entities)：根据多条数据的信息删除数据
- deleteAll()：删除全部信息





#### 测试的数据名称

##### 1. 农贸市场

绿地农贸市场：Green Land Market

红土农贸市场：Red Land Market

蓝天农贸市场：Blue Sky Market

##### 2. 专家

Lucy

Lily

Dino

##### 3. 农产品

蔬菜类 Vegetable

水产品 Aquatic

畜禽肉类 Meat



 





