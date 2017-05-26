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

		final Project project = new Project(projectname, false).save();
		final List<Project> project1 = Project.findAll();

		// 1件のみのはず
		assertThat(project1.size(), is(1));

	}
}
