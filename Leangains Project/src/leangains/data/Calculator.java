package leangains.data;

public class Calculator {

    public static double calCalories(String gender, double age, double weight, double height, double bodyFat,
                                     double muscleMass, double steps) {

        if (gender.equals("Man")) {
            double baseValue = 28;

            if (height >= 185) {
                baseValue += 1;
            } else if (height <= 167) {
                baseValue -= 1;
            }

            if (bodyFat <= 10) {
                baseValue += 0.5;
            } else if (bodyFat >= 20 && bodyFat <= 24) {
                baseValue -= 0.5;
            } else if (bodyFat >= 25 && bodyFat <= 29) {
                baseValue -= 1;
            } else if (bodyFat > 29) {

                double increase;
                double increaseInPercent;
                int x;

                increase = bodyFat - 29;
                increaseInPercent = increase / 29 * 100;
                x = (int) increaseInPercent / 5;
                baseValue -= x;
            }

            muscleAndAge(age, muscleMass, baseValue);

            baseValue = stepsCalc(steps, baseValue);

            System.out.println(baseValue);
            return weight * baseValue;

        } else if (gender.equals("Woman")) {

            double baseValue = 26;

            if (height >= 170) {
                baseValue += 1;
            } else if (height <= 153) {
                baseValue -= 1;
            }

            if (bodyFat <= 18) {
                baseValue += 0.5;
            } else if (bodyFat >= 28 && bodyFat <= 32) {
                baseValue -= 0.5;
            } else if (bodyFat >= 33 && bodyFat <= 37) {
                baseValue -= 1;
            } else if (bodyFat > 37) {

                double increase;
                double increaseInPercent;
                int x;

                increase = bodyFat - 37;
                increaseInPercent = increase / 37 * 100;
                x = (int) increaseInPercent / 5;
                baseValue -= x;
            }

            muscleAndAge(age, muscleMass, baseValue);

            if (steps > 5999) {
                baseValue += 0.5;
            }

            baseValue = stepsCalc(steps, baseValue);

            System.out.println(baseValue);
            return weight * baseValue;
        }

        return 0;

    }

    public static double stepsCalc(double steps, double baseValue) {

        if (steps > 5999) {
            baseValue += 0.5;
        }

        if (steps > 7499) {
            double increase;
            double x;

            increase = steps - 7500;
            x = increase / 1250;
            x = x * 0.5;

            baseValue += x;
        }

        return baseValue;
    }

    public static void muscleAndAge(double age, double muscleMass, double baseValue) {

        if (muscleMass >= 22 && muscleMass <= 24) {
            baseValue += 0.5;
        } else if (muscleMass >= 25) {
            baseValue += 1;
        }

        if (age >= 45) {
            baseValue -= 0.5;
        } else if (age <= 25) {
            baseValue += 0.5;
        }

    }

}

