package models.Todo.vo;

import javax.persistence.*;

import models.base.vo.*;

public class TodoContent extends AbstractStringValueObject {

	@Column(name = "todoContent", nullable = false, length = 255, columnDefinition = "nvarchar(255)")
	private final String todoContent;

	public TodoContent(final String todocontent) {
		this.todoContent = todocontent;
	}

	@Override
	public String value() {
		return todoContent;
	}

}
