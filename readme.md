# DEMO 展示
使用 popoto 来将Neo4j的代码知识图谱在html前端显示出来，包括调用关系和节点等等
（数据库已有，前端可视化+可cql查询即可）

## 1. neo4j 导入数据

(1) 将数据库 `codeapi_class.db.zip` 解压后放到 neo4j 的 `data/database` 目录下。

(2) 将 `conf/neo4j.conf` 文件的 `dbms.default_database` 的值改为 `codeapi_class.db`。

```
# The name of the default database
#dbms.default_database=neo4j
dbms.default_database=codeapi_class.db
```

(3) 启动 neo4j 检查数据库是否可以正常访问。

运行 `neo4j.bat console` 的命令，打开 `http://localhost:7474/` 检查数据是否可以正确获取。

> 如果在 neo4j 启动后报错提示 database 的名称中含有 illegal charactrs，说明版本太高，需要 jvm11及以上，因此建议下载 3.5 版本的 commmunity neo4j。

## 2. 使用 popoto

我已经使用 popoto 写好大致的框架，并将 driver 改成了本地数据库。使用浏览器打开本文件夹下的 `demo/index.html` 文件（需要修改为自己的 neo4j 密码），可以看到我们需要做的部分。

目前的任务需求：

- 添加节点：有三个节点，class，method，field。
- 添加类似 neo4j 的命令行，即：可以像 neo4j 一样通过输入命令行得到节点，进行展示。




docs 如下

https://github.com/Nhogs/popoto/wiki