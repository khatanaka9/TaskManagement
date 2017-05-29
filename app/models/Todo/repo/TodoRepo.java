package models.Todo.repo;

import java.util.*;

import models.Project.*;
import models.Todo.*;

public class TodoRepo {

	// TODO hatanaka 後で追加予定です

	// TODO hatanaka colletion実装後、collectionで返してあげる

	public static List<Todo> findByProjectId(final Project project) {
		// Select * From Todo Where project_id = "projectId";
		return Todo.find("project = ? ", project).fetch();
	}

}
