# cultural-platform
njupt文创平台-订单管理系统

## 环境搭建有问题群里call一下

开始：
``` git
git clone https://github.com/Kehuaiiiiii/cultural-platform.git
```
功能开发：
``` git
git checkout master
git fetch && git pull
git checkout -b 分支名

git add 文件
git commit -m 改动说明
git push

合并到master
```

## 前端（client）

运行方式：
``` bash
cnpm install
cnpm run dev
```

到`localhost:8080`查看



## 后端（server）

推荐idea

- jdk1.8
- maven（设置环境变量、setting、repository）

maven阿里云源：
``` xml
<mirror>
	<id>alimaven</id>
	<mirrorOf>*</mirrorOf>
	<name>aliyun maven</name>
	<url>https://maven.aliyun.com/repository/public</url>
</mirror>
```

统一一下mysql
url：localhost:3306
数据库：cultural_platform
账号：root
密码：root
