package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskMapperTest {

    @Test
    public void taskMapperTest(){
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto((long) 1, "task Dto", "test task");
        Task task = new Task((long) 1, "task", "test task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        List<TaskDto> mappedTaskList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(mappedTask.getTitle(), "task Dto");
        assertEquals(mappedTaskDto.getTitle(), "task");
        assertEquals(mappedTaskList.size(), 1);
    }

}