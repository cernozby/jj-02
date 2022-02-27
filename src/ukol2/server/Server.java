package ukol2.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class Server {
    private UserManager userManager = new UserManager();
    private MessageManager messageManager = new MessageManager();

    public static final String OK_MESSAGE = "OK";
    public static final String ERROR_MESSAGE = "ERROR";


    public void startServer() throws IOException {
        ServerSocket srvSocket = new ServerSocket(4242);
        messageManager.addMessage(new Message("bobbobbob", "bob", "tom"));

        try (Socket clientSocket = srvSocket.accept();
            BufferedReader rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            processRequests(rd, wr);
        }

        srvSocket.close();
    }

    private String connectCase(String[] words) {
        return userManager.connectUSer(words[1], words[2]) ? OK_MESSAGE : ERROR_MESSAGE;
    }

    private String msgCase(String[] words) {
        String userName = words[2].substring(0, words[2].length() - 1);
        if (words[1].equals("FOR") && userManager.existUSerName(userName) && userManager.getActualUser().isPresent()) {

            StringBuilder message = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                if (i > 2) {
                    message.append(words[i]);
                }
            }
            messageManager.addMessage(new Message(message.toString(), userManager.getActualUser().get().getName(), userName));
            return OK_MESSAGE;
        }
        return ERROR_MESSAGE;

    }

    private String readCase() {
        if (userManager.getActualUser().isPresent()) {
            return messageManager.getMessagesString(userManager.getActualUser().get())+ "\n" + OK_MESSAGE;
        }
        return ERROR_MESSAGE;

    }

    private String logoutCase() {
        userManager.setActualUser(Optional.empty());
        return OK_MESSAGE;
    }

    private void processRequests(BufferedReader rd, BufferedWriter wr) throws IOException {
        String resp = "";
        while (true) {
            String line = rd.readLine();
            if (line == null) break;

            String[] words = line.split(" ");
            switch (words[0].toUpperCase()) {
                case "CONNECT" -> {resp = connectCase(words);}
                case "MSG" -> { resp = msgCase(words);}
                case "READ" -> {resp = readCase();}
                case "LOGOUT" -> {resp = logoutCase();}
                default -> {resp = ERROR_MESSAGE;}
            }

            wr.write(resp);
            wr.write('\n');
            wr.flush();

            if (words[0].equalsIgnoreCase("logout")) {
                break;
            }
        }
    }
}
