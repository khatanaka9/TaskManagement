package controllers;

import java.util.*;

import models.Project.*;
import play.mvc.*;

public class ProjectController extends Controller {

	public static void projectList() {
		// 一覧表示用にプロジェクトを全件取得
		final List<Project> projectList = Project.findAll();
		render(projectList);
	}

}
