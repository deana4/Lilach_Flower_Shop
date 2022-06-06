package il.server;

public class TestControl {
    public static void testUserControl(){
        try{
            System.out.println("call: testUserControl");
            UserControl.setName(1, "test2", true);
            UserControl.setName(1, "test2", false);
            UserControl.setPhone(1, "test2", false);
            UserControl.setCreditCard(1, "test2", false);
            UserControl.setMail(1, "test2", false);
            UserControl.setPassword(1, "test2", false);
            System.out.println("finish: testUserControl");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("fail: testUserControl");
        }

    }
}
