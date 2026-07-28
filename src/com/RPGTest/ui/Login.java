package com.RPGTest.ui;

import com.RPGTest.domain.Test;
import com.RPGTest.domain.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public void start() {

        ArrayList<User> userList = new ArrayList<>();
        User user = new User("admin", "a123");
        userList.add(user);

        while (true) {
            System.out.println("—————————————————————————————");
            System.out.println("| 👾👾欢迎来到文字冒险游戏👾👾 |");
            System.out.println("—————————————————————————————");
            System.out.println("请选择操作：");
            System.out.println("  1. 登录");
            System.out.println("  2. 注册");
            System.out.println("  3. 退出");
            Scanner sc = new Scanner(System.in);
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
                continue;
            }
            switch (choice) {
                case 1:
                    login(userList);
                    break;
                case 2:
                    register(userList);
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("选择了无效的操作");
                    break;
            }
        }
    }

    // 登录方法
    public void login(ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        if (!contains(userList, username)) {
            System.out.println("用户名不存在，请先注册");
            return;
        }

        System.out.println("请输入密码：");
        String password = sc.nextLine();

        while (true) {
            String testCodes = Test.getCodes();
            System.out.println("验证码为：" + testCodes);
            System.out.println("请输入验证码：");
            String testCode = sc.nextLine();
            if (testCode.equals(testCodes)) {
                break;
            } else {
                System.out.println("验证码错误，请重新输入验证码");
            }
        }

        boolean loggedIn = false;
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("登录成功！");
                Gaming g = new Gaming();
                g.gameStart(user.getUsername());
                loggedIn = true;
                break;
            }
        }
        if (!loggedIn) {
            System.out.println("用户名或密码错误，请重新登录");
        }
    }

    // 注册方法
    public void register(ArrayList<User> userList) {
        User user = new User();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入用户名：");
            String username = sc.nextLine();
            if (username.length() >= 3 && username.length() <= 10) {
                if (contains(userList, username)) {
                    System.out.println("用户名已存在，请重新输入");
                } else {
                    user.setUsername(username);
                    break;
                }
            } else {
                System.out.println("用户名长度必须在3到10个字符之间");
            }
        }

        while (true) {
            int count = 0;
            int num = 0;
            System.out.println("请输入密码：");
            String password = sc.nextLine();
            for (char p : password.toCharArray()) {
                if ((p >= 65 && p <= 90) || (p >= 97 && p <= 122))
                    count++;
                else if (p >= 48 && p <= 57)
                    num++;
            }
            if (count > 0 && num > 0) {
                System.out.println("请确认密码：");
                String confirmPassword = sc.nextLine();
                if (confirmPassword.equals(password)) {
                    user.setPassword(password);
                    break;
                } else
                    System.out.println("两次输入的密码不一致");
            } else
                System.out.println("密码至少由一个字母和数字组成");
        }

        System.out.println("正在注册...");
        userList.add(user);
        System.out.println("注册成功！");
    }

    // 退出方法
    public void exit() {
        System.out.println("正在退出...");
        System.exit(0);
    }

    public boolean contains(ArrayList<User> userList, String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
