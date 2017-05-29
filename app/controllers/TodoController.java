package controllers;

import java.util.*;

import models.Project.*;
import models.Todo.*;
import models.Todo.repo.*;
import play.mvc.*;

public class TodoController extends Controller {

	public static void todoList(final Long projectid) {
		if (projectid == null) {
			throw new RuntimeException("案件のIDを" + "取得できません。");
		}

		final Project project = getProject(projectid);

		// タスク表示用に検索
		final List<Todo> todoList = TodoRepo.findByProjectId(project);

		render(todoList);
	}

	private static Project getProject(final Long projectId) {
		final Project project = Project.findById(projectId);
		if (project == null) {
			throw new RuntimeException(String.format(
					"案件が取得できません。[projectId : %s]", projectId));
		}
		return project;
	}

}
