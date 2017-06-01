package app.core.commands;

import app.domain.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class MakeFriendsCommand extends Command {

    @Autowired
    private UserService userService;

    protected MakeFriendsCommand(String[] data) {
        super(data);
    }
    /**
     * bidirectional adding friends
     * MakeFriends <username1> <username2>
     */
    @Override
    public String execute() {
        String username1 = super.getData()[1];
        String username2 = super.getData()[2];

        User user1 = this.userService.findByUsername(username1);
        User user2 = this.userService.findByUsername(username2);

        user1.getFriends().add(user2);

        return String.format("User %s is friends with user %s", username1, username2);
    }
}
