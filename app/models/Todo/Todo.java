package models.Todo;

import javax.persistence.*;
import javax.persistence.Entity;

import models.Project.*;
import models.Todo.vo.*;

import org.hibernate.annotations.*;

import play.db.jpa.*;

@Entity(name = "Todo")
public class Todo extends Model {

	@ManyToOne(optional = true)
	@JoinColumn(name = "project")
	@Index(name = "idx_Todo_projectId")
	@ForeignKey(name = "fk_Todo_projectId")
	private final Project project;

	@Column(name = "endFlag")
	private boolean endFlag = false;

	@Embedded
	private TodoName todoName;
	@Embedded
	private TodoContent todoContent;

	public Todo(final Project project, final TodoName todoName,
			final TodoContent todoContent) {
		this.project = project;
		this.todoName = todoName;
		this.todoContent = todoContent;
	}

	public void todoName(final TodoName todoName) {
		this.todoName = todoName;
	}

	public void endFlag(final boolean endFlag) {
		this.endFlag = endFlag;
	}

	public void todoContent(final TodoContent todoContent) {
		this.todoContent = todoContent;
	}

	public Project getProject() {
		return project;
	}

}
