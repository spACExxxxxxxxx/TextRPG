//启动类，不写逻辑

import com.RPGTest.domain.User;
import com.RPGTest.ui.Gaming;

public class Main {
    public static void main(String[] args) {
        //Login login = new Login();

        //login.start();

        User test = new User("勇者(测试)", "123456");
        Gaming g = new Gaming();
        g.gameStart(test.getUsername());

    }
}