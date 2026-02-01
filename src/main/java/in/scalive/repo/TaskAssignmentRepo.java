package in.scalive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.scalive.model.TaskAssignment;
import in.scalive.model.TaskEntity;
import in.scalive.model.UsersDet;
import java.util.List;
import java.util.Optional;


public interface TaskAssignmentRepo extends JpaRepository<TaskAssignment, Integer> {

	boolean existsByTasksId(TaskEntity taskEntity);
	
	boolean existsByTasksIdAndUser(TaskEntity task,UsersDet  user);
	Optional<TaskAssignment> findByTasksIdAndUser(TaskEntity task,UsersDet  user);
	
	TaskAssignment findByUser(UsersDet user);
	
	List<TaskAssignment> findByTasksId(TaskEntity tasksId);
	
	public void deleteByTasksId(TaskEntity tasksId);
	
public 	void deleteByTasksIdAndUser(TaskEntity task,UsersDet user);
}

