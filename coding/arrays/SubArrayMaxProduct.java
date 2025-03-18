package coding.arrays;

public class SubArrayMaxProduct {

    public static void main(String[] args) {
        int arr[] = { 2, 3, -2, 4, 5 };
        System.out.println("MaxProd: " + maxProduct(arr));
    }

    public static int maxProduct(int[] arr) {
        int product = 1;
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 1) {
                product = 1;
                continue;
            } else {
                product *= arr[i];
            }
            maxProduct = Math.max(maxProduct, product);
        }
        return maxProduct;
    }
}
