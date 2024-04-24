package com.thebigfun.user_service.service;import com.thebigfun.user_service.dto.EventAndUserDTO;import com.thebigfun.user_service.dto.EventDTO;import com.thebigfun.user_service.dto.UserDTO;import com.thebigfun.user_service.model.User;import reactor.core.publisher.Mono;import java.util.List;public interface UserService {    public abstract User createUser(User user);    public abstract User getUserById(Long user_id);    public abstract User updateUser(User user);    public abstract void deleteUser(Long user_id);    public abstract boolean existsUserByUserId(Long user_id);    public abstract List<User> getAllUsers();    Mono<EventAndUserDTO> getUserAndEventsById(Long user_id);}