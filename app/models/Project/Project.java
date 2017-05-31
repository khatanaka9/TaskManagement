package models.Project;

import javax.persistence.*;

import models.Project.vo.*;
import play.db.jpa.*;

@Entity(name = "Project")
public class Project extends Model {

	@Column(name = "endFlag")
	private boolean endFlag = false;

	@Embedded
	private ProjectName projectName;

	public Project(final ProjectName projectName) {
		this.projectName = projectName;
	}

	public void projectName(final ProjectName projectName) {
		this.projectName = projectName;
	}

	public void endFlag(final boolean endFlag) {
		this.endFlag = endFlag;
	}
}
