# Bitbucket FAQ

### 明明按照文档中说的配置了agent，并且成功激活了confluence，但Bitbucket激活时提示生成的密钥无效
这种情况往往是 `JAVA_OPTS` 设置的问题，如果是按照下边方式设置的 `JAVA_OPTS` ，会出现激活失败
   * 你可以把：`export JAVA_OPTS="-javaagent:/path/to/atlassian-agent.jar ${JAVA_OPTS}"` 这样的命令放到 `.bashrc` 或 `.bash_profile` 这样的文件内。

原因即是 Bitbucket 并没有使用全局设置的这个变量，主要还是依赖的其自身目录中 `安装目录下/bin/_start-webapp.sh` 中的配置，所以编辑这个文件，修改 

`JAVA_OPTS="-classpath $INST_DIR/app $JAVA_OPTS $BITBUCKET_ARGS $JMX_OPTS $JVM_REQUIRED_ARGS $JVM_SUPPORT_RECOMMENDED_ARGS"` 

处为 

`JAVA_OPTS="-javaagent:/atlassian-agent存放目录/atlassian-agent.jar -classpath $INST_DIR/app $JAVA_OPTS $BITBUCKET_ARGS $JMX_OPTS $JVM_REQUIRED_ARGS $JVM_SUPPORT_RECOMMENDED_ARGS"`

再重新尝试激活即可
