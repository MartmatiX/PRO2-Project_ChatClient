import org.apache.derby.tools.ij;

public class RunDbConsole {

    public static void main(String[] args) {
        // connect and create:
            // connect 'jdbc:derby:ChatClientDb;create=true';
        // connect
            // connect 'jdbc:derby:ChatClientDb';

        try {
            ij.main(args);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
