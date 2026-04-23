package ejercicio_ocultamiento;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TodaysDate {
    // Atributos con diferentes niveles de ocultamiento
    String time;           // Default (Package-private)
    public int day;        // Público
    private int month;     // Privado (El más restrictivo)
    protected int year;    // Protegido

    public void printDateAndTime() {
        GregorianCalendar calendar = new GregorianCalendar();

        // Formateamos la hora
        time = calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE) + ":" +
                calendar.get(Calendar.SECOND);

        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH) + 1; // +1 porque enero es 0
        year = calendar.get(Calendar.YEAR);

        System.out.println("Time: " + time);
        System.out.println("Date: " + month + " " + day + " " + year);
    }
}