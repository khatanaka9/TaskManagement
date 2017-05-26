package models.Todo;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import models.Todo.vo.*;

import org.junit.*;

import play.test.*;

public class TodoTest extends UnitTest {

	private TodoName todoname;

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
		todoname = new TodoName("タスク1");
	}

	@Test
	public void testConstructor() {

		final Todo todo = new Todo(todoname, false).save();
		final List<Todo> todo1 = todo.findAll();

		assertThat(todo1.size(), is(1));
	}

}
