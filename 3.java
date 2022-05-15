import java.util.*;

public class Main {
    public static void main(String[] args) {
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }
    }

    static String toHex(int n) {
        Deque<String> stack = new ArrayDeque<>();
        while (n > 0) {
            stack.push(Integer.toHexString(n % 16));
            n /= 16;
        }
        StringBuilder answer = new StringBuilder();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            answer.append(stack.pop());
        }
        return answer.toString();
    }
}