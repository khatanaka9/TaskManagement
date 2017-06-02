package models.Todo;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import models.Project.*;
import models.Project.vo.*;
import models.Todo.vo.*;

import org.junit.*;

import play.test.*;

public class TodoTest extends UnitTest {

	private TodoName todoname;
	private ProjectName projectname;
	private TodoContent content;
	private Project project;

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
		todoname = new TodoName("タスク1");
		projectname = new ProjectName("案件1");
		content = new TodoContent("内容");

		project = new Project(projectname).save();

	}

	@Test
	public void testConstructor() {

		final Todo todo = new Todo(project, todoname, content).save();
		final List<Todo> todoList = todo.findAll();

		assertThat(todoList.size(), is(1));
	}

}
