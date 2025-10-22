#使用说明
usage(){
 echo "Usage: sh 执行脚本.sh [backup|start|stop|status|rollback [backup下tar日期]]"
}


#启动应用
start(){
    pid=$(ps -ef | grep easyapp.jar | grep -v grep | awk '{print $2}')
    if [ -z "${pid}" ]
    then
      cd "$(dirname "$0")/.."
      export JAVA_OPTS="-Xms512m -Xmx2048m -Dloader.path=config"
      nohup java  $JAVA_OPTS -jar easyapp.jar > /dev/null 2>&1 &
      echo easyapp is started successful!
    else
      echo WARN! easyapp already started!
      echo process is $pid.
    fi
}
#停止应用
stop(){
    pid=$(ps -ef | grep easyapp.jar | grep -v grep | awk '{print $2}')
    if [ -z "$pid" ]
    then
      echo easyapp is already stopped!
    else
      kill -9 $pid
      echo easyapp is stopped successful!
      echo process is $pid.
    fi
}

#备份应用
backup(){
    mkdir -p /app/backup/easyapp/`date +%Y%m%d`
    cp /app/easyapp/easyapp.jar  /app/backup/easyapp/`date +%Y%m%d`
    cp -r /app/easyapp/config   /app/backup/easyapp/`date +%Y%m%d`
    echo "easyapp应用备份成功！"
}


#回退应用

rollback(){
  backupfile="/app/backup/easyapp/$1"
  echo  正在回退$backupfile ...
  cp $backupfile/easyapp.jar /app/easyapp/easyapp.jar
  cp -r $backupfile/config /app/easyapp/config
  echo "easyapp应用回退成功！"
}

#查看状态
status(){
  echo 正在检查状态,请稍后...
  pid=$(ps -ef | grep easyapp.jar | grep -v grep | awk '{print $2}')
  if [ -z "$pid" ]
  then
    echo easyapp应用未启动!
  else
    echo easyapp应用正在运行!
    echo 应用当前线程为 $pid.
  fi
}


case "$1" in
  	"backup")
        backup
        ;;
  	"start")
        start
        ;;
    "stop")
        stop
        ;;
  	"rollback")
        if [ -z $2 ]
        then
                echo "rollback操作需要输入备份日期(yyyyMMdd)";
        else
                rollback $2
        fi
        ;;
    "status")
        status
        ;;
 	  *)
        usage
        ;;
esac

exit 0
