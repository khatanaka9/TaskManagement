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
	@Index(name = "idx_Project_userId")
	@ForeignKey(name = "fk_Project_Users")
	private final Project project;

	@Column(name = "endFlag")
	private final boolean endFlag;

	@Embedded
	private final TodoName todoName;
	@Embedded
	private final String content;

	public Todo(final Project project, final TodoName todoName,
			final boolean endFlag, final String content) {
		this.project = project;
		this.todoName = todoName;
		this.endFlag = endFlag;
		this.content = content;
	}
}
