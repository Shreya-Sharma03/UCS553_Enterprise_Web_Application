public class ExceptionDemo{
    public static void main(String[] args){
        try{
            int a=10;
            int b=0;
            System.out.println(a/b);
        }
        catch(ArithmeticException e){
            System.out.println("ArithmeticException: Cannot divide by zero");
        }
        try{
            String name=null;
            System.out.println(name.length());
        }
        catch(NullPointerException e){
            System.out.println("NullPointerException: Object is null");
        }
        try{
            int[] arr={10,20,30};
            System.out.println(arr[3]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException: Invalid array index");
        }
        try{
            String str="abc";
            int num=Integer.parseInt(str);
            System.out.println(num);
        }
        catch(NumberFormatException e){
            System.out.println("NumberFormatException: Invalid number format");
        }
    }
}