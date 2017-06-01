package app.core.commands;

import app.domain.model.Album;
import app.domain.model.Tag;
import app.service.AlbumService;
import app.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTagToCommand extends Command {

    @Autowired
    private TagService tagService;

    @Autowired
    private AlbumService albumService;

    protected AddTagToCommand(String[] data) {
        super(data);
    }

    /**
     * AddTagTo <albumName> <tag>
     */
    //we get the data from the class that is extended, using super
    @Override
    public String execute() {
        String albumName = super.getData()[1];
        String tagName = super.getData()[2];
        Album album = this.albumService.findByName(albumName);
        Tag tag = this.tagService.findByName(tagName);
        album.getTags().add(tag);
        return String.format("Tag %s is added to album %s", tagName, albumName);
    }
}
