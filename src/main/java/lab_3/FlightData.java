package lab_3;

import java.io.Serializable;

public class FlightData implements Serializable {
    private String delay;
    private String isCancelled;

    FlightData(String delay, String isCancelled){
        this.delay = delay;
        this.isCancelled = isCancelled;
    }
}
