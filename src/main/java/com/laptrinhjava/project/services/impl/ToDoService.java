package com.laptrinhjava.project.services.impl;

import com.laptrinhjava.project.converter.ToDoConverter;
import com.laptrinhjava.project.dto.ToDoDTO;
import com.laptrinhjava.project.entities.ToDo;
import com.laptrinhjava.project.entities.User;
import com.laptrinhjava.project.repositories.ToDoRepository;
import com.laptrinhjava.project.repositories.UserRepository;
import com.laptrinhjava.project.services.IToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class ToDoService implements IToDoService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoConverter toDoConverter;

    @Override
    public ToDoDTO save(ToDoDTO toDoDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findOneByUsername(name);
//        DateFormat dateFormat = new SimpleDateFormat();
//        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
//        try {
//            Date date = dateFormat.parse(dateFormat.format(toDoDTO.getDate()));
//            toDoDTO.setDate(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        ToDo toDo = toDoConverter.toEntity(toDoDTO);
        toDo.setUser(user);
        toDo = toDoRepository.save(toDo);
        return toDoConverter.toDTO(toDo);

    }

    @Override
    public List<ToDoDTO> findByUsername(String username) {
        List<ToDo> toDoList = toDoRepository.findByUsername(username);
        List<ToDoDTO> toDoDTOList= new ArrayList<>();
        for (ToDo todo : toDoList) {
            ToDoDTO toDoDTO = toDoConverter.toDTO(todo);
            toDoDTOList.add(toDoDTO);
        }
        return  toDoDTOList;
    }

    @Override
    public ToDoDTO update(ToDoDTO toDoDTO) {
       ToDo toDo = toDoRepository.findOneById(toDoDTO.getId());
       toDo.setTitle(toDoDTO.getTitle());
       toDo.setContent(toDoDTO.getContent());
       toDo.setDate(toDoDTO.getDate());
       toDo.setStatus(toDoDTO.getStatus());
       User user = userRepository.findOneById(toDoDTO.getUserId());
       toDo.setUser(user);
       toDo = toDoRepository.save(toDo);
       return toDoConverter.toDTO(toDo);

    }

    @Override
    public ToDoDTO findOneById(Long id) {
        ToDo toDo = toDoRepository.findOneById(id);
        return toDoConverter.toDTO(toDo);
    }

    @Override
    public void deleteById(Long id) {
        toDoRepository.deleteById(id);
    }


    @Override
    public List<ToDoDTO> findByTitleOrContent(String username, String value, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Page<ToDo> pageToDo= toDoRepository.findByTitleOrContent(username,value,pageable);
        List<ToDo> toDoList = pageToDo.toList();
        List<ToDoDTO> toDoDTOList= new ArrayList<>();
        for (ToDo todo : toDoList) {
            ToDoDTO toDoDTO = toDoConverter.toDTO(todo);
            toDoDTOList.add(toDoDTO);
        }
        return  toDoDTOList;

    }

    @Override
    public List<ToDoDTO> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findOneByUsername(name);
        Page<ToDo> pageToDo= toDoRepository.findByUser_Id(user.getId(),pageable);
        List<ToDo> toDoList = pageToDo.toList();
        List<ToDoDTO> toDoDTOList= new ArrayList<>();
        for (ToDo todo : toDoList) {
            ToDoDTO toDoDTO = toDoConverter.toDTO(todo);
            toDoDTOList.add(toDoDTO);
        }
        return  toDoDTOList;

    }

    @Override
    public int totalItem() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findOneByUsername(name);
        return (int) toDoRepository.countByUser_Id(user.getId());
    }

    @Override
    public int totalItemBySearchValue(String username, String value) {
        return (int)toDoRepository.countBySearchValue(username,value);
    }


}
