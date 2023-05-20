import java.util.ArrayList;


/**
 * Write a description of class SmogApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class SmogApp
{

    // manage a list of readings
    private ArrayList<Reading> readings;

    //constructor    
    public SmogApp()
    {
        readings = new ArrayList<Reading>();
    }

    // Add a new reading to the list
    public void addReading(double distance, double smoglevel)
    {
        readings.add(new Reading(distance, smoglevel));
    }

    // Clear the log of readings
    public void clearLog()
    {
        readings = new ArrayList<Reading>();
    }

    // toString of readings
    public String toString()
    {
        String result = "Smog Level Readings:\n";

        for (Reading r : readings)
        {
            result += r.toString() + "\n";
        }

        return result;
    }

    // Tester for SmogApp
    public static void main(String[] args)
    {

        SmogApp smapp = new SmogApp();

        for (int i = 0; i<5; i++)
        {
            smapp.addReading(i, i * 2);
        }

        System.out.println(smapp);
    }

}   // end of class SmogApp


// A class that knows how to store a reading
class Reading
{
    private double distance;
    private double smoglevel;

    public Reading(double distance, double smoglevel)
    {
        this.distance = distance;
        this.smoglevel = smoglevel;
    }

    public String toString()
    {
        return this.smoglevel + " micrograms " + "at " + this.distance + "km.";
    }
}