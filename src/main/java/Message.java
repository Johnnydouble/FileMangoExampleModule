import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Message {
    public header Header;
    public input Input;
    public output Output;

    public Message appendToMsg(Message m, String k, String v){
        m.Output.pairs = new ArrayList<pair>();
        m.Output.pairs.add(new pair(k,v));
        return m;
    }
}

class input {
    public header Header;
    public int MsgType;
    public String Data;
}

class output {
    public List<pair> pairs;
}

class pair {
    public String Key;
    public String Value;

    pair(String k, String v){
        this.Key = k;
        this.Value = v;
    }
}

class header {

}

//
//class Header{
//}
//
//class Input{
//    public int type;
//    public String data;
//    public String modPath;
//}
//
//class Output{
//    public Object pairs;
//}
//
//class Root{
//    public Header header;
//    public Input input;
//    public Output output;
//}

//Go Message Structures:
//type message struct {
//    Input  input
//    Output output
//}
//
//    type input struct {
//    Type messageType
//    Data string
//}
//
//    type output struct {
//    Pairs []pair
//}
//
//    type pair struct {
//    Key   string
//    Value string
//}
//
//    type messageType int
//
//        const (
//        noop messageType = iota
//        analyze
//        suspend
//        resume
//        stop
//        queryUsage
//        )