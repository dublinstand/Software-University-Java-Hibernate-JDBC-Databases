package app.core.commands;

import app.domain.model.Album;
import app.domain.model.Color;
import app.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;

public class ModifyAlbumCommand extends Command {

    @Autowired
    private AlbumService albumService;

    protected ModifyAlbumCommand(String[] data) {
        super(data);
    }
    /**
     * ModifyAlbum <albumId> <property> <new value>
     * For example
     * ModifyAlbum Name <new name>
     * ModifyAlbum BackgroundColor <new color>
     * ModifyAlbum IsPublic <True/False>
     */
    @Override
    public String execute() {
        int albumId = Integer.parseInt(super.getData()[1]);
        String property = super.getData()[2];
        String newValue = super.getData()[3];

        Album album = this.albumService.find(albumId);

        switch (property){
            case ("Name"):
                album.setName(newValue);
                break;
            case("BackgroundColor"):
                album.setBackgroundColor(Color.valueOf(newValue));
                break;
            case ("IsPublic"):
                Boolean isPublic = Boolean.getBoolean(newValue);
                album.setPublic(isPublic);
                break;
            default:
                throw new InvalidParameterException("Incorrect property is passed");
        }

        this.albumService.create(album);

        return "Album modified";
    }
}
