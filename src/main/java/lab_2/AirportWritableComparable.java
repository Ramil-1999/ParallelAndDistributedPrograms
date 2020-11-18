package lab_2;


import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;

import java.io.IOException;


public class AirportWritableComparable implements WritableComparable<AirportWritableComparable> {
    // Some data
    private String code;
    private String index;

    public AirportWritableComparable(){

    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(code);
        out.writeUTF(index);
    }

    public AirportWritableComparable(String a, String b){
        this.code = a;
        this.index = b;
    }

    public String getCode(){
        return this.code;
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.code = in.readUTF();
        this.index = in.readUTF();
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public int compareTo(AirportWritableComparable o) {
        int value = code.compareTo(o.code);
        if (value == 0) {
            return index.compareTo(o.index);
        }
        return value;
    }
}