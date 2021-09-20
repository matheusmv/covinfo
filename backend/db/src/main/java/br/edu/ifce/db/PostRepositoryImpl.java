package br.edu.ifce.db;

import br.edu.ifce.domain.exceptions.DataIntegrityException;
import br.edu.ifce.domain.exceptions.ObjectNotFoundException;
import br.edu.ifce.db.jpa.PostJpaRepository;
import br.edu.ifce.domain.entities.Post;
import br.edu.ifce.domain.ports.driven.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public void create(Post post) {
        postJpaRepository.save(post);
    }

    @Override
    public List<Post> listAll() {
        return postJpaRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", Post.class.getSimpleName(), id)));
    }

    @Override
    public void update(Long id, Post post) {
        findById(id);
        postJpaRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            postJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}
