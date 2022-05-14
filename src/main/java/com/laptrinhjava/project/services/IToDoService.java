package com.laptrinhjava.project.services;

import com.laptrinhjava.project.dto.ToDoDTO;

import java.util.List;

public interface IToDoService {
    ToDoDTO save(ToDoDTO toDoDTO);
    List<ToDoDTO>  findByUsername(String username);
    ToDoDTO update(ToDoDTO toDoDTO);
    ToDoDTO findOneById(Long id);
    void deleteById(Long id);
    List<ToDoDTO> findByTitleOrContent(String username, String value,int pageNo,int pageSize);
    List<ToDoDTO> findPaginated(int pageNo,int pageSize);
    int totalItem();
    int totalItemBySearchValue(String username, String value);
}
