package utils;

public class FrameComparisson {
    public static double getEuclideanDistance(double[] img1, double[] img2){
        double sum = 0;
        for(int i = 0; i < img1.length; i++){
            sum += Math.pow(img1[i] - img2[i], 2);
        }
        System.out.println(Math.sqrt(sum));
        return Math.sqrt(sum);
    }
}
