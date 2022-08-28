package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControlPostTest {
    @MockBean
    private PostService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenAddPostAndGetDefault() throws Exception {
        this.mockMvc.perform(post("/add")
                        .param("name", "test")
                        .param("description", "test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).savePost(argument.capture());
        assertThat(argument.getValue().getName(), is("test"));
    }

    @Test
    @WithMockUser
    public void whenEditPostAndGetDefault() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .param("name", "test")
                        .param("description", "test")
                        .param("date", LocalDateTime.now().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).edit(argument.capture());
        assertThat(argument.getValue().getName(), is("test"));
    }

    @Test
    @WithMockUser
    public void whenDeletePostAndGetDefault() throws Exception {
        this.mockMvc.perform(post("/delete/{postId}", 1))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
