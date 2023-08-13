package org.example.modules.tools.timedTasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * Created by Dou-Fu-10 2023/8/13
 *
 * @author Dou-Fu-10
 * @date 2023/8/13
 * @Description 定时备份数据库
 */
@Slf4j
//@Component
public class DatabaseBackupTask {

    // 每天凌晨3点执行备份任务
//    @Scheduled(cron = "0 0 3 * * ?")
    public void backupDatabase() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Windows系统下的备份命令
            // 使用mysqldump命令备份数据库到指定路径
            // 假设MySQL安装目录为C:\mysql，并且将备份保存在C:\backup\database.sql
            String command = "C:\\mysql\\bin\\mysqldump -u username -p password database > C:\\backup\\database.sql";
            executeCommand(command);
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux和Mac系统下的备份命令
            // 使用mysqldump命令备份数据库到指定路径
            // 假设MySQL安装目录为/usr/local/mysql，并且将备份保存在/home/backup/database.sql
            String command = "/usr/local/mysql/bin/mysqldump -u username -p password database > /home/backup/database.sql";
            executeCommand(command);
        } else {
            log.info("不支持的操作系统");
        }
    }

    private void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                log.info("数据库备份成功");
            } else {
                log.info("数据库备份失败");
            }
        } catch (IOException | InterruptedException e) {
            log.info(e.getMessage());
        }
    }
}