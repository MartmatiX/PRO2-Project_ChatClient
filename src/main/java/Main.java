import models.chatClients.ChatClient;
import models.chatClients.InMemoryChatClient;
import models.Message;
import models.chatClients.ToFileChatClient;
import models.chatClients.chatFileOperations.ChatFileOperations;
import models.chatClients.chatFileOperations.JsonChatFileOperation;
import models.chatClients.database.DbInitializer;
import models.gui.MainFrame;

public class Main {

    public static void main(String[] args) {
        String databaseDriver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseUrl = "jdbc:derby:ChatClientDb";

        DbInitializer dbInitializer = new DbInitializer(databaseDriver, databaseUrl);
        //dbInitializer.init(); TODO: overit jestli tabulka existuje, pokud ano nevytvaret novou

        ChatFileOperations chatFileOperations = new JsonChatFileOperation();

        ChatClient client = new ToFileChatClient(chatFileOperations);
        MainFrame window = new MainFrame(800, 600, client);

    }

    private static void test() {
        ChatClient client = new InMemoryChatClient();

        client.login("Malir");
        System.out.println(client.isAuthenticated());
        client.sendMessage("Hello World");
        client.sendMessage("Hello World 2");
        for (Message mes : client.getMessages()) {
            System.out.println(mes.getText());
        }
        client.logout();
        System.out.println(client.isAuthenticated());
    }

}
