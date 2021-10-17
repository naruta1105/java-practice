import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.Math;

public class GradeStudent {
    private static final Scanner console = new Scanner(System.in);
    private static int[] weight = new int[4];
    public static void main (String[] args) {
        begin();
        double midTermScore = midTerm();
        double finalTermScore = finalTerm();
        double homeworkScore = homework();
        double overall = midTermScore+finalTermScore+homeworkScore;
        report(overall);
    }

    private static void begin() {
        /*
        Hiển thị thông điệp chào mừng.
        */
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
        System.out.println();
    }

    private static double midTerm() {
        /*
        Nhập và tính toán điểm thi giữa kỳ.
        */
        System.out.println("Midterm:");
        return inputGradeTerm(1);
    }

    private static double finalTerm() {
        /*
        Nhập và tính toán điểm thi cuối kỳ. 
        */
        System.out.println("Final:");
        return inputGradeTerm(2);
    }

    private static double homework() {
        /*
        Nhập và tính toán điểm bài tập về nhà.
        */
        System.out.println("Homework:");
        int validWeight = 100-weight[2]-weight[1];

        weight[3] = getValidInput("Weight ("+validWeight+")? ",validWeight,validWeight);
        
        int nAssigmnet = getValidInput("Number of assignments? ",0);
        int[] score = getGradeAssigment(nAssigmnet); // score[0]= total grade, score[1] = total max grade

        int nSectionAttend = getValidInput("How many sections did you attend? ",0);
        int sectionPoint = Math.min(nSectionAttend*5, 30); // if it over 30, it will be 30.
        System.out.printf("Section points = %d / 30\n",sectionPoint);

        score[0] += sectionPoint;
        score[1] += 30 ;
        double scoreWeighted = ((double)score[0]/score[1])*weight[3];

        // Print Total
        printTotalPoint(score[0],score[1],scoreWeighted,weight[3]);
        return scoreWeighted;
    }

    private static void report(double grade) {
        /*
        Tính toán về hiển thị kết quả GPA cũng như thông báo nhận xét tương ứng. 
        */
        System.out.printf("Overall percentage = %.1f\n",grade);
        double minGPA = getMinGPA(grade);
        System.out.printf("Your grade will be at least: %.1f\n",minGPA);
        //thông báo (nhận xét) tuỳ ý dựa vào GPA của sinh viên.
        switch ((int)(minGPA+0.3)) { // change 0.3+0.7 -> int 1
            case 3:
                System.out.println("Good work!!! You are excellent student.");
                break;
            case 2:
                System.out.println("You are working hard!!! You are getting better.");
                break;
            case 1:
                System.out.println("Keep trying harder!!!");
                break;
            default :
                System.out.println("You should pay attention to your studying.");
                break;
        }
        
    }

    private static int getValidInput(String str){
        /*
        Input will not throw error when input string but integer is required.
        min, max not set.
        */
        return getValidInput(str,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private static int getValidInput(String str, int min){
        /*
        Input will not throw error when input string but integer is required.
        Input must not lower than min.
        */
        return getValidInput(str,min,Integer.MAX_VALUE);
    }

    private static int getValidInput(String str,int min,int max){
        /*
        Input will not throw error when input string but integer is required.
        Input must not lower than min and not greater than max.
        If error, It will make user input again.
        */
        int input=0;
        boolean isValid;
        do {
            isValid = true;
            try{
                System.out.print(str);
                input = console.nextInt();
                if ((input<min) || (input>max))
                    isValid = false;
            } catch (InputMismatchException e) {
                isValid = false;
                console.next(); // must use for not cause infi loop
            }
        } while (!isValid);
        return input;
    }

    private static double inputGradeTerm(int n) {
        /*
        Nhập và tính toán điểm thi.
        It only for midTerm and finalTerm
        Input: n=1=> midterm, n=2=> finalterm. n=0 will make weight[n-1] valid
        */
        int validWeight = 100-weight[n-1]; // to make sure total 3 weight =100
        weight[n] = getValidInput("Weight (0-"+validWeight+")? ",0,validWeight);
        int score = getValidInput("Score earned? ",0,100);

        int isShifted = getValidInput("Were scores shifted (1 = yes, 2=no)? ",1,2);
        if (isShifted==1) {
            int shiftAmount = getValidInput("Shift amount? "); // it can be negative
            score = score+shiftAmount;
            score = Math.min(100,score); // if it over 100, it will be = 100;
            score = Math.max(0,score); // if it below 0, it will be = 0;
        }

        // Calculate score weighted
        double scoreWeighted = ((double)score/100)*weight[n];
        printTotalPoint(score,100,scoreWeighted,weight[n]);
        return scoreWeighted;
    }

    private static void printTotalPoint(int point, int totalPoint, double scoreWeighted, int weight) {
        /*
        Print total score and weight score.
        */        
        System.out.printf("Total points = %d / %d\n",point,totalPoint);
        System.out.printf("Weighted score = %.1f / %d\n",scoreWeighted,weight);
        System.out.println();
    }

    private static int[] getGradeAssigment(int n){
        /*
        Input for grade of Assigment.
        this required 2 input for grade and max grade.
        Input: n is number of assigment.
        Return: int[2] with [0] is total grade and [1] is total max grade.
        */
        boolean isValid;
        int grade = 0, maxGrade = 0;
        for (int i=1; i<=n; i++){
            do {
                isValid = true;
                try{
                    System.out.printf("Assignment %d score and max? ",i);
                    int input1 = console.nextInt();
                    int input2 = console.nextInt();
                    if (input1<=input2) {
                        grade+=input1;
                        maxGrade+=input2;
                    } else isValid = false;
                } catch (InputMismatchException e) {
                    isValid = false;
                    console.next(); // must use for not cause infi loop
                }
            } while (!isValid);
        }
        // Cut down to 150 if it greater than 150
        grade = Math.min(150,grade);
        maxGrade = Math.min(150,maxGrade);
        return new int[]{grade, maxGrade};
    }

    private static double getMinGPA(double percent){
        /*
        Get min GPA.
        85% and above: 3.0; 84.99% - 75%: 2.0; 74.99% - 60%: 0.7; under 60%: 0.0
        */
        if (percent >=85)
            return 3.0;
        else if (percent >=75)
            return 2.0;
        else if (percent >=60)
            return 0.7;
        return 0.0;    
    }
}
