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

	private Project project;
	private Todo todo;

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		final TodoName todoname = new TodoName("タスク1");
		final ProjectName projectname = new ProjectName("案件1");
		final TodoContent content = new TodoContent("内容1");
		project = new Project(projectname).save();

		todo = new Todo(project, todoname, content).save();

	}

	@Test
	public void findByProjectTest() {

		final List<Todo> todoList = TodoRepo.findByProject(project);

		// 1件のみのはず
		assertThat(todoList.size(), is(1));
		// 想定されているレコードが取得できているか
		assertThat(todoList.get(0), is(todo));
	}
}
