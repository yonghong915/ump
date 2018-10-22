package com.ump.sys.user.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.ump.core.base.entity.BaseObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @date 2018-08-09 22:18:09
 * @version 1.0.0
 */
public class Resource extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***/
	@Setter
	@Getter
	private Integer resourceId;

	/***/
	@Setter
	@Getter
	private String resourceName;

	/***/
	@Setter
	@Getter
	private String resourceDesc;

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Resource)) {
			return false;
		}
		Resource res = (Resource) o;

		return new EqualsBuilder().append(resourceId, res.resourceId).append(resourceName, res.resourceName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(resourceId).append(resourceName).toHashCode();
	}

}
