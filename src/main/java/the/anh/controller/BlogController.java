package the.anh.controller;

import the.anh.model.entity.Blog;
import the.anh.model.entity.Catalog;
import the.anh.service.blog.IBlogService;
import the.anh.service.catalog.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogController")
@CrossOrigin("*")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICatalogService catalogService;

    @ModelAttribute("catalog")
    public Iterable<Catalog> provinces() {
        return catalogService.findAll();
    }

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getList(@RequestParam("action") String action) {
        List<Blog> blogs;
        if (action.equals("all")){
            blogs = (List<Blog>) blogService.findAll();
        }else {
            blogs = (List<Blog>) blogService.showOverView();
        }
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping({"/show","/"})
    public ModelAndView showBlog() {
        ModelAndView modelAndView = new ModelAndView("/blog/blog");
        modelAndView.addObject("blog", blogService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> delete(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateCustomer(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(blogOptional.get().getId());
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Blog> searchProduct(@RequestParam("search") String search) {
        Optional<Blog> blogOptional = blogService.findByTitleBlog(search);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
    }

}
