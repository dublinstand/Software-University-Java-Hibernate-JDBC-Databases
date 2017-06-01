package app.serviceimpl;

import app.domain.model.Album;
import app.repository.AlbumRepository;
import app.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void create(Album album) {
        this.albumRepository.save(album);
    }

    @Override
    public Album findByName(String name) {
        return this.albumRepository.findByName(name);
    }

    @Override
    public Album find(int id) {
        return this.albumRepository.findOne(id);
    }
}
