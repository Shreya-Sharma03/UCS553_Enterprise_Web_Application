public class GenericArraySum{
    public static <T extends Number> double calculateSum( T[] arr){
        double sum=0.0;
        for(T value: arr){
            sum=sum+value.doubleValue();
        }
        return sum;
    }
    public static void main(String[] args){
        Integer[] integers={10,20,30};
        Float[] floats={1.5f, 2.5f, 3.0f};
        Double[] doubles={10.5, 20.5, 30.5};
        System.out.println("Sum ofInteger array: "+ calculateSum(integers));
        System.out.println("Sum of Float array: "+ calculateSum(floats));
        System.out.println("Sum of Double array: "+ calculateSum(doubles));
    }
}