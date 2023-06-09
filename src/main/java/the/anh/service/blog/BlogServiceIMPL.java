package the.anh.service.blog;

import the.anh.model.entity.Blog;
import the.anh.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BlogServiceIMPL implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable findAll() {
        return blogRepository.findAll();
    }


    @Override
    public Optional findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }


    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllByTitleContaining(String title, Pageable pageable) {
        return blogRepository.findAllByTitleContaining(title,pageable);
    }

    @Override
    public Optional<Blog> findByTitleBlog(String title) {
        return blogRepository.findByTitleBlog(title);
    }

    @Override
    public Iterable<Blog> showOverView() {
        return blogRepository.showOverView();
    }
}
