package hr.algebra.walletapi.controller;

import hr.algebra.walletapi.model.UserTask;
import hr.algebra.walletapi.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/user-task")
public class UserTaskController {

    private final UserTaskService UserTaskService;

    @Autowired
    public UserTaskController(hr.algebra.walletapi.service.UserTaskService userTaskService) {
        UserTaskService = userTaskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserTask>> getAllContents() {
        return new ResponseEntity<>(UserTaskService.getAllUsersTasks(), HttpStatus.OK);
    }

    @GetMapping("/task")
    public ResponseEntity<List<UserTask>> getUsersContents() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(UserTaskService.getUserTasks(principal.getUsername()), HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<Void> addUserTask(@RequestBody UserTask userTask) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserTaskService.addUsersTask(userTask, principal.getUsername());
        return ResponseEntity.ok().build();
    }
}
