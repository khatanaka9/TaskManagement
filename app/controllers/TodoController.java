package controllers;

import java.util.*;

import models.Project.*;
import models.Todo.*;
import models.Todo.repo.*;
import models.Todo.vo.*;
import play.mvc.*;

public class TodoController extends Controller {

	public static void todoList(final Long projectId) {
		if (projectId == null) {
			throw new RuntimeException("案件のIDを" + "取得できません。");
		}

		final Project project = getProject(projectId);

		// タスク表示用に検索
		final List<Todo> todoList = TodoRepo.findByProject(project);

		render(projectId, todoList);
	}

	private static Project getProject(final Long projectId) {
		final Project project = Project.findById(projectId);
		if (project == null) {
			throw new RuntimeException(String.format(
					"案件が取得できません。[projectId : %s]", projectId));
		}
		return project;
	}

	private static Todo getTodo(final Long todoId) {
		final Todo todo = Todo.findById(todoId);
		if (todo == null) {
			throw new RuntimeException(String.format(
					"タスクが取得できません。[todoId : %s]", todoId));
		}
		return todo;
	}

	// タスク新規登録画面
	public static void create(final Long projectId) {
		render(projectId);
	}

	// タスク新規登録処理

	public static void createExec(final Long projectId, final String todoName,
			final String todoContent) {

		final Project project = getProject(projectId);
		final TodoName todoname = new TodoName(todoName);
		final TodoContent todocontent = new TodoContent(todoContent);

		new Todo(project, todoname, todocontent).save();
		flash.success("登録に成功しました。");
		TodoController.todoList(projectId);
	}

	// タスク編集画面
	public static void update(final Long todoId) {
		final Todo todo = getTodo(todoId);
		final Long projectId = todo.getProject().getId();
		render(projectId, todo);
	}

	// タスク編集処理
	public static void updateExec(final Long todoId, final String todoName,
			final String todoContent, final boolean endFlag) {

		final Todo todo = getTodo(todoId);
		final Long projectId = todo.getProject().getId();
		todo.todoName(new TodoName(todoName));
		todo.todoContent(new TodoContent(todoContent));
		todo.endFlag(endFlag);
		todo.save();
		flash.success("更新に成功しました。");
		TodoController.todoList(projectId);

	}

	// プロジェクト削除処理
	public static void delete(final Long todoId) {

		final Todo todo = getTodo(todoId);
		final Long projectId = todo.getProject().getId();
		todo.delete();
		flash.success("削除に成功しました。");
		TodoController.todoList(projectId);
	}
}
