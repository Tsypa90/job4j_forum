package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
class PostControlGetTest {
    @Autowired
    private PostService service;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenRequestGetAddAndGetDefault() throws Exception {
        this.mockMvc
                .perform(get("/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    @Test
    @WithMockUser
    public void whenRequestGetPostsPostIdAndGetDefault() throws Exception {
        Post post = service.savePost(Post.of("test", "test"));
        this.mockMvc.perform(get("/posts/{postId}", post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void whenRequestGetUpdatePostIdAndGetDefault() throws Exception {
        Post post = service.savePost(Post.of("test", "test"));
        this.mockMvc.perform(get("/update/{postId}", post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void whenRequestGetAddMsgPostIdAdnGetDefault() throws Exception {
        Post post = service.savePost(Post.of("test", "test"));
        this.mockMvc.perform(get("/addmsg/{postId}", post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addmsg"));
    }
}