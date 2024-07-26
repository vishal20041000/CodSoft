public class Student {
    private double[] marks;

    public Student(double[] marks) {
        this.marks = marks;
    }

    public double getTotalMarks() {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    public double getAveragePercentage() {
        return getTotalMarks() / marks.length;
    }

    public String getGrade() {
        double average = getAveragePercentage();
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}

