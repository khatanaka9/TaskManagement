package models.Todo;

import javax.persistence.*;

import models.Project.*;
import models.Todo.vo.*;
import play.db.jpa.*;

@Entity(name = "Todo")
public class Todo extends Model {

	@Column(name = "endFlag")
	private final boolean endFlag;

	@ManyToOne
	private Project project;

	@Embedded
	private final TodoName todoName;

	public Todo(final TodoName todoName, final boolean endFlag) {
		this.todoName = todoName;
		this.endFlag = endFlag;
	}
}
