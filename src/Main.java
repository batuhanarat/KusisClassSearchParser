import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    static String dayIdentifier;
    static String day;
    static String time;
    static  String classroom;
    static String temp;

    static String[] blockArray = new String[100];


    static Boolean normal = true;

    static String blockSeparator = "Class\tSection\tDays & Times\tRoom\tInstructor\tMeeting Dates\tStatus";
    static String anotherBlockSeparator = "Bölümü daralt";


    public static void main(String[] args) throws IOException {

        prepareInput();


        String inputPath = "src/Resources/raw" + day + "Data.txt";
        String outputPath = "src/Resources/"+day+"Output.txt";

        File file = new File(inputPath);
        Scanner scan = new Scanner(file);
        FileWriter writer = new FileWriter(outputPath);


        while(scan.hasNextLine()) {

            if (scan.nextLine().equals(blockSeparator)) {

                for (int i = 0; i < 3; i++) {
                    scan.nextLine();
                }

                temp = scan.nextLine();

                int blockIndex = 0;
                while(!(temp.contains(",") || temp.contains("Staff"))) {

                    blockArray[blockIndex] = temp;
                    blockIndex++;
                    temp = scan.nextLine();

                }

                int blockLength = blockIndex;

                String[] timeArray = new String[300];
                String[] classroomArray = new String[300];


                for(int i =0 ; i< blockLength; i++) {

                    if(i > (blockLength/2)-1) {
                        classroomArray[i] = blockArray[i];
                    } else {
                        timeArray[i] = blockArray[i];

                    }
                }

                // make blockArray for after usage

                for(int i =0 ; i< blockLength; i++) {
                    blockArray[i] = null;
                }


                for (int a = 0; (a < blockLength/2); a++) {
                   int newindex = blockLength/2;

                    time = timeArray[a];
                    classroom = classroomArray[newindex+a];

                    if(time.contains(dayIdentifier)) {

                        if(!classroom.equals("TBA")) {
                            if(!time.equals("TBA")) {

                                writer.write(time);
                                writer.write(",");
                                writer.write(classroom);
                                writer.write("\n");

                            }
                        }
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
       System.out.println("Mo for Monday, Tu for Tuesday, We for Wednesday, Th for Thursday, Fr for Friday");
       System.out.println("Enter an identifier: ");

       dayIdentifier =sc.nextLine();
       setTheDate(dayIdentifier);

       while(day == null) {


           System.out.println("Your format is not true!");
           System.out.println("Mo for Monday, Tu for Tuesday, We for Wednesday, Th for Thursday, Fr for Friday");
           System.out.println("Enter an identifier: ");
           dayIdentifier = sc.nextLine();
           setTheDate(dayIdentifier);

       }


   }




}