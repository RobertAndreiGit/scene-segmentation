package utils;

public class FrameComparisson {
    public static double getEuclideanDistance(double[] img1, double[] img2){
        double sum = 0;
        for(int i = 0; i < img1.length; i++){
            sum += Math.pow(img1[i] - img2[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static double[] normalize(double[] arr) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;


        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] - min) / (max - min);
        }

        return arr;
    }
}
