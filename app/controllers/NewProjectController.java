package controllers;

import models.Project.*;
import models.Project.vo.*;
import play.mvc.*;

public class NewProjectController extends Controller {

	public static void newProject() {

		render();
	}

	public static void newProjectRegistration(final String projectName) {

		// TODO hatanaka 入力値なしの場合
		// TODO hatanaka メッセージ表示

		if (projectName.equals("")) {
			NewProjectController.newProject();
		}

		final ProjectName projectname = new ProjectName(projectName);

		final Project project = new Project(projectname).save();

		ProjectController.projectList();
	}

}
