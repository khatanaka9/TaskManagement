package controllers;

import java.util.*;

import models.Project.*;
import models.Project.vo.*;
import models.Todo.*;
import models.Todo.repo.*;
import play.mvc.*;

public class ProjectController extends Controller {

	// プロジェクト一覧表示
	public static void projectList() {
		// 一覧表示用にプロジェクトを全件取得
		final List<Project> projectList = Project.findAll();
		render(projectList);
	}

	private static Project getProject(final Long projectId) {
		final Project project = Project.findById(projectId);
		if (project == null) {
			throw new RuntimeException(String.format(
					"案件が取得できません。[projectId : %s]", projectId));
		}
		return project;
	}

	// プロジェクト新規登録画面
	public static void create() {
		render();
	}

	// プロジェクト新規登録処理
	public static void createExec(final String projectName) {

		final ProjectName projectname = new ProjectName(projectName);

		new Project(projectname).save();
		flash.success("登録に成功しました。");
		ProjectController.projectList();
	}

	// プロジェクト編集画面
	public static void update(final Long projectId) {
		final Project project = getProject(projectId);
		render(project);
	}

	// プロジェクト編集処理
	public static void updateExec(final Long projectId,
			final String projectName, final boolean endFlag) {

		final Project project = getProject(projectId);

		project.projectName(new ProjectName(projectName));
		project.endFlag(endFlag);
		project.save();
		flash.success("更新に成功しました。");
		ProjectController.projectList();
	}

	// プロジェクト削除画面
	public static void delete(final Long projectId) {

		final Project project = getProject(projectId);
		final List<Todo> todoList = TodoRepo.findByProject(project);
		if (todoList.isEmpty()) {
			project.delete();
			flash.success("削除に成功しました。");
			ProjectController.projectList();
		} else {
			flash.error("関連したタスクがあるため削除できません");

			ProjectController.projectList();
		}
	}
}
