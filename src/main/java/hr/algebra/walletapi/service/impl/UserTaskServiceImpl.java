package hr.algebra.walletapi.service.impl;

import hr.algebra.walletapi.model.UserTask;
import hr.algebra.walletapi.repository.UserRepository;
import hr.algebra.walletapi.repository.UserTaskRepository;
import hr.algebra.walletapi.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserTaskServiceImpl implements UserTaskService {

    private UserTaskRepository userTaskRepository;

    private UserRepository userRepository;

    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository, UserRepository userRepository) {
        this.userTaskRepository = userTaskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserTask> getAllUsersTasks() {
        return userTaskRepository.findAll();
    }

    @Override
    public List<UserTask> getUserTasks(String username) {
        Stream<UserTask> userContentStream = getAllUsersTasks().stream().filter((userContent -> userContent.getUser().getEmail().equals(username)));
        return userContentStream.collect(Collectors.toList());
    }

    @Override
    public void addUsersTask(UserTask UserTask, String username) {
        UserTask.setUser(userRepository.findByEmail(username).orElse(UserTask.getUser()));
        userTaskRepository.save(UserTask);
    }
}
