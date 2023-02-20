class Test
{
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();

        System.out.println(c instanceof A);
        System.out.println(c instanceof B);


    }
}

class A{

}
class B extends A{


}

class C extends B{

}