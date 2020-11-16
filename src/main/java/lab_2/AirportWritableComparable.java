package lab_2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;

import java.io.IOException;


public class AirportWritableComparable implements WritableComparable {
    // Some data
    Integer code;
    String name;

    public void write(DataOutput out) throws IOException {
        out.writeInt(code);
        out.writeUTF(name);
    }

    public void readFields(DataInput in) throws IOException {
        this.code = in.readInt();
        this.name = in.readUTF();
    }

    public int compareTo(AirportWritableComparable o) {
        int thisValue = this.code;
        int thatValue = o.code;
        return (thisValue < thatValue ? -1 : (thisValue==thatValue ? 0 : 1));
    }

    public int hashCode() {
        return code.hashCode();
    }
}