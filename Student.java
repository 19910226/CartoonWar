package day1.demo1;

public class Student {
    String name;
    int age;
    String address;

    public void study() {
        System.out.println("学生爱学习");

    }

    public void sleep() {
        System.out.println("学习累了，要睡觉");
    }
}
//学生的测试类
//如何使用成员变量呢?
//        对象名.变量名
//        如何使用成员方法呢?
//        对象名.方法名(...)
//类（成员变量，位置不同，在类中方法外
// 成员方法，
// 构造方法）
class StudentDemo{
    public static void main(String[] args) {
        //类名 对象名=new 类名（）;
        Student s=new Student();
        System.out.println(s.name+"---"+s.age+"-----"+s.address);
        s.name="小明";
        s.address="天津";
        s.age=8;
        System.out.println(s.name+"---"+s.age+"-----"+s.address);
        s.sleep();
        s.study();
    }

        }
