public class Message {
    public input Input;
    public output Output;
}

class input {
    public messageType MsgTyp;
    public String Data;
}

class messageType {
    public int type;
    messageType(int type){
        this.type = type;
    }
}

class output {
    public pair[] pairs;
}

class pair {
    public String Key;
    public String Value;
}




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