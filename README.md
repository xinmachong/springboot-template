# template

#### 介绍
Spring Boot框架对常用模块进行集成，加速开发进度

#### 使用说明

1. 下载直接使用命令
    `git clone git@gitee.com:sanchagou-xinmachong/template.git`


2. JWT 配置只有三个参数：
    expire：token 有效时长,单位是秒
    signature：签名，不为外人所道，任意字符串
    excludePaths：放行路由，即不用校验 token 的接口，List<String> 格式，故新增时需要添加序号[x]


3. 全局异常处理
    全局异常处理的功能都放在 exception 目录下，可自行增删


4. MyBatis-plus
    软删除功能可自行增删，对需要软删除的对象，需要在某一属性（删除标志）上添加 @TableLogic 注解


5. Logs
    通过 shell 定时任务，每天删除上一日记录，同时切割正在重新记录的 nohup.out 日志文件，从而获取简短可读日志


6. 魔法值处理，来源主要两个模块，一是数据库中的枚举值，二是系统代码中的常量
    
    
#### 功能介绍
目前主要有以下几个功能：
1. 集成了 JWT，可在配置文件中直接修改参数即可使用
2. 集成了全局异常处理，不用担忧异常问题
3. 集成了后端接口统一回复，controller控制层中接口可以直接返回字符串和对象，也可以返回UnifyResponse
4. 集成了MyBatis-plus，并已经实现了软删除和开启下划线转驼峰
5. 日志分割，防止大文件无法阅读
6. 魔法值处理 消除代码中出现的魔法值