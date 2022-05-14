package com.laptrinhjava.project.api;
import com.laptrinhjava.project.dto.ToDoDTO;
import com.laptrinhjava.project.services.IToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ToDoAPI {
    @Autowired
    private IToDoService toDoService;
    @PostMapping("/todo")
    public ToDoDTO createToDo(@RequestBody ToDoDTO toDoDTO){
        return toDoService.save(toDoDTO);
    }
    @GetMapping("/todo")
    public List<ToDoDTO> getToDo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return toDoService.findByUsername(name);
    }
    @GetMapping("/todo/{id}")
    public ToDoDTO getToDo(@PathVariable(name = "id") Long id){
        return toDoService.findOneById(id);
    }
    @PutMapping("/todo")
    public ToDoDTO updateToDo(@RequestBody ToDoDTO toDoDTO){
        return toDoService.update(toDoDTO);
    }
    @DeleteMapping("/todo/{id}")
    public void deleteToDo(@PathVariable(name = "id") Long id){
        toDoService.deleteById(id);
    }
    @GetMapping("/todo/search/{value}/{pageNo}/{pageSize}")
    public List<ToDoDTO> searchToDo(@PathVariable(name = "value") String value, @PathVariable(name = "pageNo") int pageNo, @PathVariable(name = "pageSize") int pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return toDoService.findByTitleOrContent(name,value,pageNo,pageSize);
    }
    @GetMapping("/todo/{pageNo}/{pageSize}")
    public List<ToDoDTO> getPaginatedToDo(@PathVariable(name = "pageNo") int pageNo, @PathVariable(name = "pageSize") int pageSize){
        return toDoService.findPaginated(pageNo,pageSize);
    }
    @GetMapping("/todo/totalItem")
    public int getTotalItem(){
        return toDoService.totalItem();
    }
    @GetMapping("/todo/totalItem/{value}")
    public int getTotalItemBySearchValue(@PathVariable(name = "value") String value){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return toDoService.totalItemBySearchValue(name,value);
    }
}
