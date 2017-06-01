package app.core.commands;

import app.domain.model.*;
import app.service.AlbumService;
import app.service.TagService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAlbumCommand extends Command {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TagService tagservice;

    @Autowired
    private UserService userService;

    protected CreateAlbumCommand(String[] data) {
        super(data);
    }

    /**
     *CreateAlbum <username> <albumTitle> <BackgroundColor> <tag1> <tag2>...<tagN>
     */
    @Override
    public String execute() {
        String userName = super.getData()[1];
        String albumTitle = super.getData()[2];
        Color backgroundColor = Color.valueOf(getData()[3]);

        User user = this.userService.findByUsername(userName);
        AlbumRole albumRole = new AlbumRole();
        albumRole.setUser(user);

        Album album = new Album();
        album.setName(albumTitle);
        album.setBackgroundColor(backgroundColor);
        album.getAlbumRoles().add(albumRole);

        for (int i = 4; i < super.getData().length; i++) {
            String tagName = super.getData()[i];
            Tag tag = this.tagservice.findByName(tagName);
            album.getTags().add(tag);
        }

        albumService.create(album);

        return String.format("Album was created for username: %s, with album title: %s and names: %s",
                userName, albumTitle, backgroundColor);
    }


}
