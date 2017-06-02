package models.Project;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import models.Project.vo.*;

import org.junit.*;

import play.test.*;

public class ProjectTest extends UnitTest {

	private ProjectName projectname;

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		projectname = new ProjectName("案件1");
	}

	@Test
	public void testConstructor() {

		final Project project = new Project(projectname).save();
		final List<Project> projectList = project.findAll();

		// 1件のみのはず
		assertThat(projectList.size(), is(1));

	}
}
