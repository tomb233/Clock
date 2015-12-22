package clock;

import jdk.nashorn.internal.runtime.ParserException;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//Class file code originally taken from http://stackoverflow.com/questions/31238492/writing-ics-ical-file-using-java
public class iCal {

    private String version =    "VERSION:1.0 \r\n";
    private String prodid =     "PRODID://Elara/lofy/tanare/delp/314sum2015// \n";
    private String calBegin =   "BEGIN:VCALENDAR \n";
    private String calEnd =     "END:VCALENDAR \n";
    private String eventBegin = "BEGIN:VEVENT \n";
    private String eventEnd =   "END:VEVENT \n";
    protected Calendar date2 ;
    public void iCal(){

    }

    public void write( String name ,SortedArrayPriorityQueue t ){
       Long timestoinsert[] = new Long[9];
        timestoinsert = t.returnPriorities();
        try {

            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(".ics");

            File file = new File(builder.toString());
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            for (int x = 0; x < timestoinsert.length; x++) {
                date2 = Calendar.getInstance();
                Long timeNow = date2.getTimeInMillis();
                System.out.println(timeNow);
                if(timestoinsert[x] == null){
                    break;
                }
                Long timeStart = timestoinsert[x];
                String testExample = "UID:uid1@example.com\nDTSTAMP:" + timeNow + "\nORGANIZER;CN=JavaClock:MAILTO:NA\nDTSTART:" + timeStart + "\nDTEND:" + timeStart + "\nSUMMARY:Alarm\n";
                fw = new FileWriter(file.getAbsoluteFile());

                bw.write(calBegin);
                bw.write(version);
                bw.write(prodid);
                bw.write(eventBegin);
                bw.write(testExample);
                bw.write(eventEnd);
                bw.write(calEnd);


            }


            bw.close();


            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long[] read() throws IOException {
        FileReader reader = new FileReader("alarmfile.ics");
        BufferedReader textReader = new BufferedReader(reader);
        Long alarms[];

        String[] textData = new String[9];
        int BufferIndex = 0;
        String line;

        while ((line = textReader.readLine()) != null) {
            if (line.trim().startsWith("DTSTART")) {
                textData[BufferIndex] = textReader.readLine();
                BufferIndex = BufferIndex + 1;
                System.out.println(textData[BufferIndex-1]);
            }
        }
        alarms = new Long[textData.length];
        for (int x = 0; x < textData.length; x++) {

            if(textData[x] == null){
                break;
            }
            String test1 = textData[x].replaceAll("[^\\d.]", "");
            alarms[x] = Long.parseLong(test1);




        }
        // close the line-by-line reader and return the data
        textReader.close();
        return alarms;



    }

}