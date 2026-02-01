package in.scalive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.scalive.model.TaskEntity;

public interface TaskEntityRepo  extends JpaRepository<TaskEntity,Integer>{

}
