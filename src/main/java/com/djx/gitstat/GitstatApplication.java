package com.djx.gitstat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class GitstatApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitstatApplication.class, args);

        exec("pwd");

        exec("git --git-dir='/c/Users/dongj/IdeaProjects/github/shell-process/.git' log");
        exec("git log");

    }





    public static String exec(String command) {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            int exitVal = process.waitFor();
            stringBuilder.append("process exit value is ").append(exitVal).append("<br />").append("<br />");
            System.out.println("OUTPUT");
            while ((line = stdoutReader.readLine()) != null) {
                System.out.println(line);
                stringBuilder.append(line).append("<br />");
            }
            System.out.println("ERROR");
            while ((line = stderrReader.readLine()) != null) {
                System.out.println(line);
                stringBuilder.append(line).append("<br />");
            }
            return stringBuilder.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("执行错误");
        }
    }



}
