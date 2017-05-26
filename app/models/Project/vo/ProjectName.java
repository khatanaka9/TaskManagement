package models.Project.vo;

import javax.persistence.*;

import models.base.vo.*;

public class ProjectName extends AbstractStringValueObject {

	@Column(name = "projectName", nullable = false, length = 255, columnDefinition = "nvarchar(255)")
	private final String projectName;

	public ProjectName(final String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String value() {
		return projectName;
	}

}
