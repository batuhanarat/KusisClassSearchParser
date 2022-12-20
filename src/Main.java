import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    static String dayIdentifier;
    static String day;
    static String time;
    static  String classroom;
    static String blockSeparator = "Class\tSection\tDays & Times\tRoom\tInstructor\tMeeting Dates\tStatus";



    public static void main(String[] args) throws IOException {


        prepareInput();



        String inputPath = "src/Resources/raw" + day + "Data.txt";
        String outputPath = "src/Resources/"+day+"Output.txt";

        File file = new File(inputPath);
        Scanner scan = new Scanner(file);
        FileWriter writer = new FileWriter(outputPath);


        while(scan.hasNextLine()) {

            if(scan.nextLine().equals(blockSeparator) ) {

                for(int i = 0 ; i<3 ; i++) {
                    scan.nextLine();
                }

                 time =scan.nextLine();
                 classroom = scan.nextLine();


                 //we do not want data with no classroom specified.
                //our database should have information about classrooms and time period


                 if(!classroom.equals("TBA")) {

                     //In kusis data, there are block sections that includes other days class schedule.
                     //So if I want to examine specific day's schedule, I do not want information about other days.
                     if(time.contains(dayIdentifier)) {
                         writer.write(time);
                         writer.write(",");
                         writer.write(classroom);
                         writer.write("\n");

                     }
                 }

            }

        }

        writer.close();

    }



   private static void setTheDate(String dayIdentifier) {


       switch (dayIdentifier) {
           case "Mo":
               day = "Monday";
               break;
           case "Tu":
               day = "Tuesday";
               break;
           case "We":
               day = "Wednesday";
               break;
           case "Th":
               day = "Thursday";
               break;
           case "Fr":
               day = "Friday";
               break;
           default:
               break;

       }

   }

   private static void prepareInput() {

       Scanner sc= new Scanner(System.in); //System.in is a standard input stream
       System.out.println("Enter a day identifier for wanted day.");
       System.out.println("Mo for Monday, Tu for Tuesday, We for Wednesday, Thu for Thursday, Fri for Friday");
       System.out.println("Enter an identifier: ");

       dayIdentifier =sc.nextLine();
       setTheDate(dayIdentifier);

       while(day == null) {


           System.out.println("Your format is not true!");
           System.out.println("Mo for Monday, Tu for Tuesday, We for Wednesday, Thu for Thursday, Fri for Friday");
           System.out.println("Enter an identifier: ");
           dayIdentifier = sc.nextLine();
           setTheDate(dayIdentifier);

       }


   }




}