package hashcode;

public class Main {
    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "imooc";
        System.out.println(((String)d).hashCode());

        Student stu = new Student(3,2,"bruski","wang");
        System.out.println(stu.hashCode());

        Student stu2 = new Student(3,2,"bruski","wang");
        System.out.println(stu.equals(stu2));
    }
}
