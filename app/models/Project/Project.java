package models.Project;

import javax.persistence.*;

import models.Project.vo.*;
import play.db.jpa.*;

@Entity
public class Project extends Model {

	private final boolean endFlag;

	private final ProjectName projectName;

	public Project(final ProjectName projectName, final Boolean endFlag) {
		this.projectName = projectName;
		this.endFlag = endFlag;
	}

}
