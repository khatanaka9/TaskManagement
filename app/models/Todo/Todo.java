package models.Todo;

import javax.persistence.*;

import models.Project.*;
import models.Todo.vo.*;
import play.db.jpa.*;

@Entity
public class Todo extends Model {

	private final boolean endFlag;

	@ManyToOne
	private Project project;

	private final TodoName todoName;

	public Todo(final TodoName todoName, final boolean endFlag) {
		this.todoName = todoName;
		this.endFlag = endFlag;
	}
}
