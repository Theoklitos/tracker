package theo.test.tracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * If you are reading this, this probably represents you. A developer is the one to whom stories and bugs are
 * assigned to.
 * 
 * @author theoklitos
 * 
 */
@Entity
public final class Developer {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	/**
	 * returns the unique id of this developer
	 */
	public Long getId() {
		return id;
	}

	/**
	 * what is this developer called?
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the developer's name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
