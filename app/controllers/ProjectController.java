package controllers;

import java.util.*;

import models.Project.*;
import models.Project.vo.*;
import play.mvc.*;

public class ProjectController extends Controller {

	// プロジェクト一覧表示
	public static void projectList() {
		// 一覧表示用にプロジェクトを全件取得
		final List<Project> projectList = Project.findAll();
		render(projectList);
	}

	// プロジェクト新規登録画面
	public static void create() {
		render();
	}

	// プロジェクト新規登録処理
	public static void createExec(final String projectName) {

		final ProjectName projectname = new ProjectName(projectName);

		final Project project = new Project(projectname).save();

		ProjectController.projectList();
	}

	// プロジェクト編集画面
	public static void update(final Long projectId) {
		final Project project = Project.findById(projectId);
		System.out.println(projectId);
		render(project);
	}

	// プロジェクト編集処理
	public static void updateExec(final Long projectId,
			final String projectName, final boolean endFlag) {

		final Project project = Project.findById(projectId);

		project.projectName(new ProjectName(projectName));
		project.endFlag(endFlag);
		project.save();

		ProjectController.projectList();
	}
}
