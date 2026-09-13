public class NestedTry {
    public static void main(String[] args){
        try{
            System.out.println("Outer try starts");
            try{
                int a=10;
                int b=0;
                System.out.println(a/b);
            }
            catch(ArithmeticException e){
                System.out.println("Inner catch: Cannot divide by zero");
            }
            int[] arr={10,20,30};
            System.out.println(arr[5]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Outer catch: Invalid array index");
        }
        System.out.println("Program continues");
    }
}
