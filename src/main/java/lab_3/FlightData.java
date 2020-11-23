package lab_3;

import java.io.Serializable;
import java.util.Iterator;

public class FlightData implements Serializable {
    private String delay;
    private String isCancelled;

    FlightData(String delay, String isCancelled){
        this.delay = delay;
        this.isCancelled = isCancelled;
    }

    FlightData(){
        this.delay = "";
        this.isCancelled = "";
    }

    public String getDelay(){
        return this.delay;
    }

    public String getIsCancelled(){
        return this.isCancelled;
    }

    public FlightData calculations(Iterable<FlightData> flights){

        double maxDelay = 0;
        int counterCanceled = 0;
        int counterSize = 0;
        for (FlightData flight: flights){
            counterSize++;
            Double cIsCancelled = Double.parseDouble(flight.getIsCancelled());
            if (cIsCancelled == 1){
                counterCanceled++;
                
            }
            Double cDelay = Double.parseDouble(flight.getDelay());
            if (cDelay > maxDelay) maxDelay = cDelay;
            if (cDelay > 0) counterCanceled++;

        }
        FlightData tmp = new FlightData(String.valueOf(maxDelay), String.valueOf(100 * (double)counterCanceled/(double)counterSize));
        return tmp;
    }

    public String toString(){
        return "DELAY: " + delay + ", PERCENT = " + isCancelled;
    }
}
