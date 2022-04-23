import java.util.*;
// 给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字
// 刚开始出来工作，就有遇到同样的场景，找出缺失的试管号
// 算法：用空间换取时间
// 平铺直叙是最基本的一种方法，你还可以倒序
// 作差法也是其中一种非常直观的方法，
// 自由发挥，最后能够出来正确的结果就OK
public class Main {
    public static void main(String[] args) {
        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        int removed = list.remove((int) (Math.random() * list.size()));
        int found = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        int found = 0;
        for (int i = start; i <= end; i++) {
            if (list.indexOf(i) == -1) {
                found = i;
                break;
            }
        }
        return found;
    }
}