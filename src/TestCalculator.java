import java.util.Scanner;

/**
 * @author 闫亮23
 * @version 1.0
 */
public class TestCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String str = scanner.next();
        // 从键盘一次性全部读入 计算式
        Calculator calculator = new Calculator(str);
        calculator.scanner();
    }


}
