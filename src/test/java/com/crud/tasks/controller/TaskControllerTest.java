package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TaskController taskController;

    @Test
    public void getTasksTest() throws Exception {
        //Given
        List<TaskDto> tasksDtoLists = new ArrayList<>();
        tasksDtoLists.add(new TaskDto((long) 1, "task title", "content"));

        when(taskController.getTasks()).thenReturn((ResponseEntity<List<TaskDto>>) tasksDtoLists);

        //When & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("task title")))
                .andExpect(jsonPath("$[0].content", is("content")));
    }


    @Test
    public void createTaskTest() throws Exception {
        //Given
        TaskDto newTaskDto = new TaskDto((long) 1, "task title", "content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newTaskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());
    }


/*    @Test
    public void updateTaskTest() throws Exception {
        //Given
        TaskDto newTaskDto = new TaskDto((long) 1, "task title", "content");
        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(newTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newTaskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("task title")));

    }*/

}