package the.anh.service.blog;


import the.anh.model.entity.Blog;
import the.anh.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBlogService extends IService<Blog> {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
    Optional<Blog> findByTitleBlog(@Param("title") String title);

    Iterable<Blog> showOverView();
}
