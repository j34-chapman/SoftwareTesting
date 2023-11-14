public class Cyclinder {

    double radius;
    double height;

  /*  Multiply by 100.0:
    volume * 100.0 is 123.456789 * 100.0, which equals 12345.6789.

    Round to the nearest integer:
    The Math.round function rounds 12345.6789 to the nearest integer, which is 12346.

    Divide by 100.0:
            12346 / 100.0 is 123.46*/
    public static double calcualteVolume(double radius, double height){
        double volume = Math.PI * Math.pow(radius, 2) * height;
        //Round volume to two decimal points
        return Math.round(volume * 100.0) / 100.0;
    }
}
