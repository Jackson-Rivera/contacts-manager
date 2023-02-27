package utils;

public class InputTest {

    // VIEWPORT //
    public static void main(String[] args) {

        Input input = new Input();

        String userStr = input.getString();
        System.out.println(userStr);
        Boolean yesNo = input.yesNo();
        System.out.println(yesNo);
        int userInt = input.getInt(0, 10);
        System.out.println(userInt);
        int userInt2 = input.getInt();
        System.out.println(userInt2);
        double userDouble = input.getDouble(5.15, 50.75);
        System.out.println(userDouble);
        double userDouble2 = input.getDouble();
        System.out.println(userDouble2);
    }
}
