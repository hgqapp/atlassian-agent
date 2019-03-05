# Atlassian Agent

#### Support (almost any version, include 8.0):
* JIRA Software
* JIRA Core
* JIRA Service Desk
* JIRA plugin: Capture
* JIRA plugin: Training
* JIRA plugin: Portfolio
* Confluence
* Confluence plugin: Questions
* Confluence plugin: Team Calendars
* Bamboo
* Bitbucket
* FishEye
* Crowd
* Crucible
* Third party plugins

## 使用说明

### 优势
* 支持Atlassian家几乎所有产品，同时支持插件（包括插件市场的第三方插件）。
* 支持DataCenter模式。
* 相比较于传统的crack来说可以很容易的升级你的服务，而不用重新再次破解。
* 提供基于java的命令行 keygen，更方便在终端环境使用。
* 开源项目，你知道破解时都做了什么。

### 直接下载
* 直接下载本项目[release](https://github.com/pengzhile/atlassian-agent/releases)包。

### 自行编译
* Clone本项目源码，pom.xml同级目录执行`mvn package`后即可进行编译。
* 使用`target`目录产出的`atlassian-agent-jar-with-dependencies.jar`，而非`atlassian-agent.jar`！
* *如果你不知道我在说什么，最好还是直接下载我编译好的包。*

### 使用帮助
* 如果你已经获得`atlassian-agent.jar`，可以试着执行`java -jar atlassian-agent.jar`看看输出的帮助。
* 这里的帮助以Atlassian家的Confluence服务为例。

### 配置Agent
1. 将`atlassian-agent.jar`放在一个你不会随便删除的位置（你服务器上的所有Atlassian服务可共享同一个`atlassian-agent.jar`）。
2. 设置环境变量`JAVA_OPTS`（这其实是Java的环境变量，用来指定其启动java程序时附带的参数），把`-javaagent`参数附带上。具体可以这么做：
   * 你可以把：`export JAVA_OPTS="-javaagent:/path/to/atlassian-agent.jar ${JAVA_OPTS}"`这样的命令放到`.bashrc`或`.bash_profile`这样的文件内。
   * 你可以把：`export JAVA_OPTS="-javaagent:/path/to/atlassian-agent.jar ${JAVA_OPTS}"`这样的命令放到服务安装所在`bin目录`下的`setenv.sh`或`setenv.bat（供windows使用）`中。
   * 你还可以直接命令行执行：`JAVA_OPTS="-javaagent:/path/to/atlassian-agent.jar" /path/to/start-confluence.sh`来启动你的服务。
   * 或者你所知的其他修改环境变量的方法，但如果你机器上有无关的服务，则不建议修改全局`JAVA_OPTS`环境变量。
   * 总之你想办法把`-javaagent`参数附带到要启动的java进程上。
3. 配置完成请重启你的Confluence服务。
4. 如果你想验证是否配置成功，可以这么做：
   * 执行类似命令：`ps aux|grep java` 找到对应的进程看看`-javaagent`参数是否正确附上。
   * 在软件安装目录类似：`/path/to/confluence/logs/catalina.out`Tomcat日志内应该能找到：`========= agent working =========`的输出字样。

### 使用KeyGen
* 你得确认已经配置好agent，参考上面说明。
* 当你试着执行`java -jar /path/to/atlassian-agent.jar`时应该可以看到输出的KeyGen参数帮助。
* 请仔细看看每个参数的作用，特别是`-p`参数的取值范围。
* 第三方插件将其`应用密钥`作为`-p`参数。如：`-p com.gliffy.integration.confluence`
* 在Atlassian服务安装时你应该能看到类似：`AAAA-BBBB-CCCC-DDDD`的server id，请留意。
* 提供了正确的参数运行KeyGen会在终端输出计算好的激活码。
* 将生成的激活码复制出来去激活你要使用的服务。
* 举个栗子：`java -jar atlassian-agent.jar -p conf -m aaa@bbb.com -n my_name -o https://zhile.io -s ABCD-1234-EFGH-5678`

### 申明
* 本项目只做个人学习研究之用，不得用于商业用途！
* 商业使用请向[Atlassian](https://www.atlassian.com)购买正版，谢谢合作！
* 本项目使用`GNU General Public License v3.0`开源许可！
* 不允许说我代码写的糟糕，对我来说`PHP`才是世界上最好的语言（不服来辩）。

### 交流
* 给本项目发issue。
* 欢迎你来一起完善这个项目，请发PR。
* 你可以加入QQ群：30347511 和我实时交流。
* 访问网站：[https://zhile.io](https://zhile.io) 给我留言。
