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

## 2. 连接前端

