package com.zhang.app.String;


// hashCode()方法和equal()方法的作用其实一样，在Java里都是用来对比两个对象是否相等一致，那么equal()既然已经能实现对比的功能了，为什么还要hashCode()呢？
//
//   因为重写的equal（）里一般比较的比较全面比较复杂，这样效率就比较低，而利用hashCode()进行对比，则只要生成一个hash值进行比较就可以了，效率很高，那么hashCode()既然效率这么高为什么还要equal()呢？
//
//   因为hashCode()并不是完全可靠，有时候不同的对象他们生成的hashcode也会一样（生成hash值得公式可能存在的问题），所以hashCode()只能说是大部分时候可靠，并不是绝对可靠，所以我们可以得出：
//
//   1.equal()相等的两个对象他们的hashCode()肯定相等，也就是用equal()对比是绝对可靠的。
//
//   2.hashCode()相等的两个对象他们的equal()不一定相等，也就是hashCode()不是绝对可靠的。
//
//   所有对于需要大量并且快速的对比的话如果都用equal()去做显然效率太低，所以解决方式是，每当需要对比的时候，首先用hashCode()去对比，如果hashCode()不一样，则表示这两个对象肯定不相等（也就是不必再用equal()去再对比了）,如果hashCode()相同，此时再对比他们的equal()，如果equal()也相同，则表示这两个对象是真的相同了，这样既能大大提高了效率也保证了对比的绝对正确性！

public class JichengTest {
    public static void main(String[] args) {
        Cylinder cylinder = new Cylinder(10, 10);
        cylinder.area(5);
        System.out.println("JichengTest main " + cylinder.area(5));
        Cylinder2 cylinder2 = new Cylinder2(10, 10);
        System.out.println("JichengTest main " + cylinder2.area(5));
        System.out.println("JichengTest main " + (cylinder instanceof CircleShape));
    }

    interface CircleShape {
        double PI = 3.14159;

        double area(double radius);
    }

    static class Circle implements CircleShape {
        int radius;

        public Circle(int r) {
            radius = r;
        }

        public double perimeter() {
            return (2 * PI * radius);
        }

        @Override
        public double area(double radius) {
            return (PI * radius * radius);
        }
    }

    static class Cylinder extends Circle implements CircleShape {// 子类会继承父类对于接口的实现

        int heigh;

        int radius;

        public Cylinder(int r, int h) {
            super(r);
            heigh = h;

        }

        public double baseAera(double radius) {
            return area(radius);

        }

        @Override
        public double area(double radius) {
            return super.perimeter() + 2;

        }

        public double volume(double radius) {
            return (heigh * area(radius));
        }

    }

    static class Cylinder2 extends Circle {// 子类会继承父类对于接口的实现

        int heigh;

        int radius;

        public Cylinder2(int r, int h) {
            super(r);
            heigh = h;

        }

        public double baseAera(double radius) {
            return area(radius);

        }

        @Override
        public double area(double radius) {
            return super.perimeter() + 3;

        }

        public double volume(double radius) {
            return (heigh * area(radius));
        }

    }

}

