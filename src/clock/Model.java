package clock;

import javafx.scene.layout.Priority;

import javax.swing.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

//import java.util.GregorianCalendar;

        public class Model extends Observable {
            int priority = 0;
            int hour = 0;
            int minute = 0;
            int second = 0;
            PriorityQueue<Alarm> q = new SortedArrayPriorityQueue<>(8);

            String[] list4 = { "00", "01", "02", "03", "04", "05", "06", "07", "08",

                    "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",

                    "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",

                    "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",

                    "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52",

                    "53", "54", "55", "56", "57", "58", "59", "60" };
            String[] list3 = { "00", "01", "02", "03", "04", "05", "06", "07", "08",

                    "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",

                    "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",

                    "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",

                    "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52",

                    "53", "54", "55", "56", "57", "58", "59", "60" };
            int oldSecond = 0;

            public Model() {
                update();
            }

            public void update() {
        Calendar date = Calendar.getInstance();

        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
        try{

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


            //get current date time with Calendar()
            Calendar cal1 = Calendar.getInstance();
            //System.out.println(dateFormat.format(cal1.getTime()));

            long time = cal1.getTimeInMillis();
           // System.out.println("Testing alarm");
            Alarm hello;
            hello = q.head();
            //System.out.println(dateFormat.format(hello.cal.getTime()));   Testing code
            long calTime = hello.getPriority();
           // System.out.println(calTime);
           // System.out.println(time);
            if(calTime <= time){
                //System.out.println("RING RING RING"); //Insert ringing method here
                q.remove();
                ringAlarm();
                //delete old head from priority queue
            }else if(calTime >= time){
                //System.out.println("Not yet");
            }
        }catch(QueueUnderflowException e) {
            //System.out.println("Error underflow in alarm check");
        }

    }



    public void addAlarm(Alarm a,Long p){
        try{
            q.add(a,p);
        } catch (QueueOverflowException e){
            System.out.println("Add operation failed: " + e);
        }

    }

    public String getHour(){
        String[] list2 = { "01", "02", "03", "04", "05", "06", "07", "08", "09",

                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",

                "21", "22", "23", "24" };
        String h = (String) JOptionPane.showInputDialog(
                null,
                "Please choose the hour for new alarm",
                "Add Alarm- Hour",
                JOptionPane.PLAIN_MESSAGE,
                null,
                list2,
                "00");
        return h;
    }

    public String getMinutes(){


        String m = (String)JOptionPane.showInputDialog(
                null,
                "Please choose the minutes for new alarm",
                "Add Alarm- Minutes",
                JOptionPane.PLAIN_MESSAGE,
                null,
                list3,
                "00");

        return m;
    }

    public String getSeconds(){



        String s = (String)JOptionPane.showInputDialog(
                null,
                "Please choose the seconds for new alarm",
                "Add Alarm- Seconds",
                JOptionPane.PLAIN_MESSAGE,
                null,
                list4,
                "00");
        return s;
    }
    public void addalarmdialogue(){
        String hours =  getHour();
        String minutes = getMinutes();

        int Hours = Integer.parseInt(hours);
        int Minutes = Integer.parseInt(minutes);

        System.out.println(Hours);
        System.out.println(Minutes);

            Alarm newAlarm = new Alarm(Hours,Minutes);

            addAlarm(newAlarm, newAlarm.getPriority());
    }

    public String printAlarms(){
       String answer =  q.toString();
        return answer;

    }
    public void ringAlarm(){
        // create a jframe
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");

        // show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(frame,
                "RING RING RING");

    }
    public void editAlarmDialogue(){
       Alarm Choice = returnEditChoice();
       Long deleteLong= Choice.getPriority();
        q.deleteByPriority(deleteLong);
        this.bringUpEditForm();
    }

    public Alarm returnEditChoice(){
        Alarm[] list5 = q.returnAlarms();
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");

        Alarm s = (Alarm)JOptionPane.showInputDialog(
                null,
                "Please choose the seconds for new alarm",
                "Add Alarm- Seconds",
                JOptionPane.PLAIN_MESSAGE,
                null,
                list5,
                "00");
        return s;
    }
    public String getNextAlarm(){
        String answer = "";

        try{
           Alarm answer1 = q.head();
            answer = answer1.toString();
        }catch(QueueUnderflowException e){
            System.out.println(e);
        }

        return answer;
    }
    public void bringUpEditForm(){


        String h = (String)JOptionPane.showInputDialog(
                null,
                "Please choose the Hours for  alarm",
                "Edit Alarm- Hours",
                JOptionPane.PLAIN_MESSAGE,
                null,
                list3,"00");
        String s  = (String)JOptionPane.showInputDialog(
                null,
                "Please choose the Minutes for  alarm",
                "Edit Alarm- Minutes",
                JOptionPane.PLAIN_MESSAGE,
                null,
                list4,
                "00");


        int H = Integer.parseInt(h);
        int S = Integer.parseInt(s);
        Alarm newAlarm = new Alarm(H,S);

        addAlarm(newAlarm, newAlarm.getPriority());


    }
    public void loadAlarms() throws IOException {
        iCal test = new iCal();

        Long test2[] = test.read();
        for (int x = 0; x < test2.length; x++) {
            if(test2[x] == null){
                break;
            }
            Alarm newAlarm = new Alarm(test2[x]);
           
            addAlarm(newAlarm,test2[x]);
        }


    }
    public void saveAlarms(){
       iCal test = new iCal();

        test.write("alarmfile", (SortedArrayPriorityQueue) q);
    }

}