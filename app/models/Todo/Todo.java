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
	@Index(name = "idx_ProjectId")
	@ForeignKey(name = "fk_ProjectId")
	private final Project project;

	@Column(name = "endFlag")
	private final boolean endFlag = false;

	@Embedded
	private final TodoName todoName;
	@Embedded
	private final TodoContent todoContent;

	public Todo(final Project project, final TodoName todoName,
			final TodoContent todoContent) {
		this.project = project;
		this.todoName = todoName;
		this.todoContent = todoContent;
	}
}
