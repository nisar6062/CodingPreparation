package coding.arrays;

public class SubArrayMaxProduct {

    public static void main(String[] args) {
        int arr[] = { 2, 3, -2 };
        System.out.println("MaxProd: " + maxProduct(arr));
    }

    public static int maxProduct(int[] arr) {
        int product = arr[0];
        int maxProduct = arr[0];
        int minProduct = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) {
                // product = 1;
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
                // continue;
            }
            maxProduct = Math.max(arr[i], maxProduct * arr[i]);
            minProduct = Math.min(arr[i], minProduct * arr[i]);
            product = Math.max(product, maxProduct);
        }
        return product;
    }

}
