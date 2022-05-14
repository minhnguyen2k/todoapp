package com.laptrinhjava.project.repositories;

import com.laptrinhjava.project.entities.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    ToDo save (ToDo toDo);
    @Query(value = "SELECT A.* from todo A join user B on A.user_id = B.id where B.username =:username order by A.id asc" ,nativeQuery = true)
    List<ToDo> findByUsername(@Param("username") String username);
    ToDo findOneById(Long id);
    void deleteById(Long id);
    @Query(
            value = "SELECT todo.* from todo join user on todo.user_id = user.id where user.username =:username and replace(todo.title,' ','')like %:value% or replace(todo.content,' ','') like %:value% order by todo.id asc ",nativeQuery = true,
            countQuery = "SELECT count(*) from todo join user  on todo.user_id = user.id where user.username =:username and replace(todo.title,' ','')like %:value% or replace(todo.content,' ','') like %:value% order by todo.id asc"
    )
    Page<ToDo> findByTitleOrContent(@Param("username")String username, @Param("value") String value, Pageable pageable);
    Page<ToDo> findByUser_Id(Long user_id , Pageable pageable);
    long countByUser_Id(Long user_id);
    @Query(value = "SELECT count(*) from todo join user on todo.user_id = user.id where user.username =:username and replace(todo.title,' ','')like %:value% or replace(todo.content,' ','') like %:value% order by todo.id asc",nativeQuery = true)
    long countBySearchValue(@Param("username")String username, @Param("value") String value);
}
