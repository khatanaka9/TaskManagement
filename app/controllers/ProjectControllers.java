package controllers;

import java.util.*;

import models.Project.*;
import play.mvc.*;

public class ProjectControllers extends Controller {

	public static void index() {
		final List<Project> project = Project.findAll();
		System.out.println(project.size());
		render(project);
	}

}
