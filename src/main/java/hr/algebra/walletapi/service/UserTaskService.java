package hr.algebra.walletapi.service;

import hr.algebra.walletapi.model.UserTask;

import java.util.List;

public interface UserTaskService {

    List<UserTask> getAllUsersTasks();

    List<UserTask> getUserTasks(String username);

    void addUsersTask(UserTask UserTask, String username);
}
