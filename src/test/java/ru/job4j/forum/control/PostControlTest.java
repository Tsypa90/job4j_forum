package ru.job4j.forum.control;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
class PostControlTest {
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
        this.mockMvc.perform(get("/posts/{postId}", "9"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void whenRequestGetUpdatePostIdAndGetDefault() throws Exception {
        this.mockMvc.perform(get("/update/{postId}", 9))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void whenRequestGetAddMsgPostIdAdnGetDefault() throws Exception {
        this.mockMvc.perform(get("/addmsg/{postId}", 9))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addmsg"));
    }
}