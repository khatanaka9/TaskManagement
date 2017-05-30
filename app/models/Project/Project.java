package models.Project;

import javax.persistence.*;

import models.Project.vo.*;
import play.db.jpa.*;

@Entity(name = "Project")
public class Project extends Model {

	@Column(name = "endFlag")
	private final boolean endFlag = false;

	@Embedded
	private final ProjectName projectName;

	public Project(final ProjectName projectName) {
		this.projectName = projectName;
	}

}
