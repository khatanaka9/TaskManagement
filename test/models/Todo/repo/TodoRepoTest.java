package models.Todo.repo;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import models.Project.*;
import models.Project.vo.*;
import models.Todo.*;
import models.Todo.vo.*;

import org.junit.*;

import play.test.*;

public class TodoRepoTest extends UnitTest {

	private TodoName todoname;
	private ProjectName projectname;
	private TodoContent content;
	private Project project;
	private Todo todo;

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		todoname = new TodoName("タスク1");
		projectname = new ProjectName("案件1");
		content = new TodoContent("内容1");
		project = new Project(projectname).save();
		todo = new Todo(project, todoname, content).save();
		;

	}

	@Test
	public void findByProjectTest() {
		final List<Todo> todo1 = Todo.find("project = ? ", project).fetch();

		// 1件のみのはず
		assertThat(todo1.size(), is(1));

	}
}
