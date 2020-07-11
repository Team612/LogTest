import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logtest {

        public static String filename; // Name/Location of File; Time Robot started Logging (When we enable) is in the file name 
        public static Path filepath; // A special path object used for File writing 
        public static DateTimeFormatter date; // Helper class for date format
    
   

    public static void main(String[] args) throws IOException { // Main Method for Testing
        init(); // Instantiate things needed for logging
        record("105,Shooter,Velocity:2400RPM"); // test
        record("30,Climb,Low Piston: Out, High Piston: In"); // test
            
          

    }

    public static void record(String str) throws IOException { // Used to append a string into CSV
        String msg = date.format(LocalDateTime.now())+","+str; // Start with timestamp
        
        Files.write(filepath, (msg + System.lineSeparator()).getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND); // New Line
    }

    public static void init() throws IOException { // This would be done in Robot/Teleop/Auto Init
        
        date = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss");  // Set Date formatting to whatever the string is
        filename = "Log-"+date.format(LocalDateTime.now())+".csv";  // Create CSV file name

        createFile(); // Create File

        filepath = Paths.get(filename); // Set Path Object

        String header = "Timestamp,Match Time, Subsystem, Value,"; // Comma separated header to put into a CSV file
        
        Files.write(filepath, header.getBytes(),StandardOpenOption.APPEND); // Write String header into first line of CSV file
        Files.write(filepath, (""+System.lineSeparator()).getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND); // New Line
       
    }

    public static void createFile(){
        try{
            File file = new File(/*"C:\\Users\\sriha\\Desktop\\FileTest\\"*/filename); // Replace with path on roborio
            file.createNewFile(); // Create actual file using Filename
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
