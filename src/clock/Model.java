package clock;

import javafx.scene.layout.Priority;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Observable;

//import java.util.GregorianCalendar;

public class Model extends Observable {
    int priority = 0;
    int hour = 0;
    int minute = 0;
    int second = 0;
    PriorityQueue<Alarm> q = new SortedArrayPriorityQueue<>(8);



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
        String[] list3 = { "00", "01", "02", "03", "04", "05", "06", "07", "08",

                "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",

                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",

                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",

                "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52",

                "53", "54", "55", "56", "57", "58", "59", "60" };

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

        String[] list4 = { "00", "01", "02", "03", "04", "05", "06", "07", "08",

                "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",

                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",

                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",

                "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52",

                "53", "54", "55", "56", "57", "58", "59", "60" };

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
}