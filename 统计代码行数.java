// 1. 异常初探
public class Main {
    public static void main(String[] args) {
      try {
        byte[] bs = toGBK("中文");
        System.out.println(Arrays.toString(bs));
      } catch (UnsupportedEncodingException e) {
        System.out.println(e);
      }
}

    static byte[] toGBK(String s) throws UnsupportedEncodingException {
        // 用指定编码转换String为byte[]:
        return s.getBytes("GBK");
    }
}
// 2. 抛出异常
public class Main {
    public static void main(String[] args) {
        try {
            process1();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    static void process1() {
        try {
            process2();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static void process2() {
        throw new NullPointerException();
    }
}
// 3. 从Java 14开始，如果产生了NullPointerException，JVM可以给出详细的信息告诉我们null对象到底是谁。我们来看例子：
// 这也是一个 has a那学科什么累的时候，不知道第一行要写什么，其实认真想一想就知道这里面肯定是一个实例变量
// 对比编码法，看代码时，有一行一行地解释，不错，继续保持
public class Main
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.address.city.toLowerCase());
    }
}

class Person {
    String[]  = new String[2];
    Address address = new Address();
}

class Address {
    String city;
    String street;
    String zipcode;
}
// 4. Commons Logging优先使用Log4j:
LogFactory factory = null;
if (isClassPresent("org.apache.logging.log4j.Logger")) {
    factory = createLog4j();
} else {
    factory = createJdkLog();
}

boolean isClassPresent(String name) {
    try {
        Class.forName(name);
        return true;
    } catch (Exception e) {
        return false;
    }
}
// 5. 反射初探
// 哪怕就是一个标点符号，我都要搞对
// 发现自己的想像力和编码能力越来越强大！
public class Main {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score":
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("name"));
        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));
    }
}

class Student extends Person {
    public int score;
    private int grade;
}

class Person {
    public String name;
}
// 6. 反射进阶
// 获取字段值
// 利用反射拿到字段的一个Field实例只是第一步，我们还可以拿到一个实例对应的该字段的值。
// 例如，对于一个Person实例，我们可以先拿到name字段对应的Field，再获取这个实例的name字段的值：
public class Main {

    public static void main(String[] args) throws Exception {
      Object p = new Person("Xiao Ming");
      Class c = p.getClass();
      Field f = c.getDeclaredField("name");
      Object value = f.get(p);
      System.out.println(value);
    }

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
// 7. 反射进阶2
// 通过Field实例既然可以获取到指定实例的字段值，自然也可以设置字段的值。
// 设置字段值是通过Field.set(Object, Object)实现的，其中第一个Object参数是指定的实例，第二个Object参数是待修改的值。
public class Main {

    public static void main(String[] args) throws Exception {
        Person p = new Person("Xiao Ming");
        System.out.println(p.getName());
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        f.set(p, "Xiao Hong");
        System.out.println(p.getName());
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
// 反射：有 Field ，就有 Method
public class Main {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("getScore", String.class));
        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int:
        System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));
        
        // String对象:
        String s = "Hello world";
        // 获取String substring(int)方法，参数为int:
        Method m = String.class.getMethod("substring", int.class);
        // 在s对象上调用该方法并获取结果:
        String r = (String) m.invoke(s, 6);
        // 打印调用结果:
        System.out.println(r);
    }
}
// 以下是类的定义
class Student extends Person {
    public int getScore(String type) {
        return 99;
    }
    private int getGrade(int year) {
        return 1;
    }
}

class Person {
    public String getName() {
        return "Person";
    }
}
// 反射：获取构造方法和继承关系
public class Main {
    public static void main(String[] args) throws Exception {
        // 获取构造方法Integer(int):
        Constructor cons1 = Integer.class.getConstructor(int.class);
        // 调用构造方法:
        Integer n1 = (Integer) cons1.newInstance(123);
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = (Integer) cons2.newInstance("456");
        System.out.println(n2);

        // 获取父类的 class ，再获取父类的 class的父类的class
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n);
        Class o = n.getSuperclass();
        System.out.println(o);
        System.out.println(o.getSuperclass());

        // 获取 Integer 实现的接口
        Class s = Integer.class;
        Class[] is = s.getInterfaces();
        for (Class i : is) {
            System.out.println(i);
        }
    }
}
// 2022年4月2日 注解，是一种特殊的注释，是一种放在 java 源码的类、字段、方法和参数前的一种特殊的注解。
// this is a component:
@Resource("hello")
public class Hello {
    @Inject
    int n;

    @PostConstruct
    public void hello(@Param String name) {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "Hello";
    }
}
// 注解 参数 定义一个注解时， 还可以定义注解的参数
// 代码，文字，逻辑，都是相互关联的
public class Hello {
    // 定义一个注解时， 还可以配置多个参数
    @Check(min=0, max=100, value=55)
    public int n;
    // 核心的参数是 value
    @Check(value=99)
    public int p;
    // 省略掉 value 行不行？
    @Check(99)
    public int x;
    // 不写行不行？
    @Check
    public int y;
}
// 定义一个 @Report 注解，分为三步骤
// 第三步：定义元注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// 第一步：使用 @interface 定义注解
public @interface Report {
    // 第二步：添加参数的默认值
    int type() default 0;
    String level() default "info";
    String value() default "";
}
// 定义了注解，本身对程序逻辑没有任何影响。我们必须自己编写代码来使用注解。
void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
    // 遍历所有Field:
    for (Field field : person.getClass().getFields()) {
        // 获取Field定义的@Range:
        Range range = field.getAnnotation(Range.class);
        // 如果@Range存在:
        if (range != null) {
            // 获取Field的值:
            Object value = field.get(person);
            // 如果值是String:
            if (value instanceof String) {
                String s = (String) value;
                // 判断值是否满足@Range的min/max:
                if (s.length() < range.min() || s.length() > range.max()) {
                    throw new IllegalArgumentException("Invalid field: " + field.getName());
                }
            }
        }
    }
}
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Person[] ps = new Person[] {
            new Person("Bob", 61),
            new Person("Alice", 88),
            new Person("Lily", 75),
        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));
    }
}


class Person implements Comparable<Person> {
    String name;
    int score;
    Person(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
    public String toString() {
        return this.name + "," + this.score;
    }
}
// 泛型的extends通配符
public class Main {
    public static void main(String[] args) {
        Pair<Integer> p = new Pair<>(123, 456);
        int n = add(p);
        System.out.println(n);
    }

    static int add(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }
}
// 编写 Pair 泛型
class Pair<T> {
    private T first;
    private T last;
    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }
}
// 集合使用泛型
public class Collections {
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i=0; i<src.size(); i++) {
            T t = src.get(i);
            dest.add(t);
        }
    }
}