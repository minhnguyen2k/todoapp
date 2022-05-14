package com.laptrinhjava.project.converter;

import com.laptrinhjava.project.dto.ToDoDTO;
import com.laptrinhjava.project.entities.ToDo;
import org.springframework.stereotype.Component;

@Component
public class ToDoConverter {
    public ToDo toEntity(ToDoDTO toDoDTO){
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoDTO.getTitle());
        toDo.setContent(toDoDTO.getContent());
        toDo.setDate(toDoDTO.getDate());
        toDo.setStatus(toDoDTO.getStatus());
        return toDo;
    }
    public ToDoDTO toDTO(ToDo toDo){
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(toDo.getId());
        toDoDTO.setTitle(toDo.getTitle());
        toDoDTO.setContent(toDo.getContent());
        toDoDTO.setDate(toDo.getDate());
        toDoDTO.setStatus(toDo.getStatus());
        toDoDTO.setUserId(toDo.getUser().getId());
        return toDoDTO;
    }
}
