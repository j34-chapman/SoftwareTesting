public class BMICalculator {

    public double calculateBMI(double weightInKg, double heightInMeters) {
        if (weightInKg <= 0 || heightInMeters <= 0) {
            throw new IllegalArgumentException("Weight and height must be greater than zero");
        }
        double bmi = weightInKg / (heightInMeters * heightInMeters);
        return Math.round(bmi * 100.0) / 100.0; // to avoid rounding issues
    }

    public String classifyBMI(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 24.9 && bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
