package models.Todo.vo;

import javax.persistence.*;

import models.base.vo.*;

public class TodoName extends AbstractStringValueObject {

	@Column(name = "todoName", nullable = false, length = 255, columnDefinition = "nvarchar(255)")
	private final String todoName;

	public TodoName(final String todoname) {
		this.todoName = todoname;
	}

	@Override
	public String value() {
		return todoName;
	}

}
