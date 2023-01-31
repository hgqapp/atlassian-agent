# FishEye & Crucible FAQ

### 明明按照文档中说的配置了agent，并且成功激活了confluence，但 FishEye or Crucible 激活时提示生成的密钥无效
这种情况往往是 `JAVA_OPTS` 设置的问题，如果是按照下边方式设置的 `JAVA_OPTS` ，会出现激活失败
   * 你可以把：`export JAVA_OPTS="-javaagent:/path/to/atlassian-agent.jar ${JAVA_OPTS}"` 这样的命令放到 `.bashrc` 或 `.bash_profile` 这样的文件内。

原因即是 FishEye 和 Crucible 没有直接使用这个环境变量，使用的是 `FISHEYE_OPTS` 这一环境变量

所以我们打开bin目录中的fisheyectl.sh文件，将其中的

```FISHEYE_CMD="$JAVACMD $FISHEYE_OPTS -Dfisheye.library.path=$FISHEYE_LIBRARY_PATH -Dfisheye.inst=$FISHEYE_INST -Djava.awt.headless=true -Djava.endorsed.dirs=$FISHEYE_HOME/lib/endorsed -jar $FISHEYE_HOME/fisheyeboot.jar"```

添加成

```FISHEYE_CMD="$JAVACMD $FISHEYE_OPTS -javaagent:/path/to/atlassian-agent-v1.2.2/atlassian-agent.jar -Dfisheye.library.path=$FISHEYE_LIBRARY_PATH -Dfisheye.inst=$FISHEYE_INST -Djava.awt.headless=true -Djava.endorsed.dirs=$FISHEYE_HOME/lib/endorsed -jar $FISHEYE_HOME/fisheyeboot.jar"```

再重新尝试激活即可。
