package ukol2.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageManager {
    ArrayList<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    private void removeMessageByUserTo(User userTo) {
        this.messages.
                removeIf(message -> message.getUserTo().equals(userTo.getName()));
    }

    private List<Message> getMessageByUserTo(User userTo) {
        return this.messages.stream().
                filter(message -> message.getUserTo().equals(userTo.getName())).collect(Collectors.toList());

    }

    public String getMessagesString(User userTo) {
        List<Message> messages = getMessageByUserTo(userTo);
        removeMessageByUserTo(userTo);

        StringBuilder sb = new StringBuilder();
        messages.forEach(message -> sb.append("FROM ")
                .append(message.getUserFrom())
                .append(": ")
                .append(message.getMessage()).append("\n"));

        return sb.toString();
    }
}
