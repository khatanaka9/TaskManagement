package controllers;

import java.util.*;

import models.Project.*;
import models.Todo.*;
import models.Todo.vo.*;
import play.mvc.*;

public class TodoController extends Controller {

	public static void todoList(final Long projectid) {
		if (projectid == null) {
			throw new RuntimeException("案件のIDを" + "取得できません。");
		}

		final Project project = getProject(projectid);

		// タスク表示用に検索
		final List<Todo> todoList = Todo.findAll();

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

	// タスク新規登録画面
	public static void TodoCreate(final Long projectId) {

		render(projectId);
	}

	// タスク新規登録処理
	public static void createRegistration(final Long projectId,
			final String todoName, final String todoContent) {

		System.out.println(projectId);
		final Project project = Project.findById(projectId);
		final TodoName todoname = new TodoName(todoName);
		final TodoContent todocontent = new TodoContent(todoContent);

		final Todo todo = new Todo(project, todoname, todocontent).save();

		TodoController.todoList(projectId);
	}

	// プロジェクト編集画面
	public static void Todoupdate(final Long projectid) {
		final Project project = Project.findById(projectid);

		render(project);
	}

}
