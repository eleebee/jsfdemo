package spy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * This is intended to present a JPA-free but JPA-like interface.
 * If you have JPA, you might be able to build this class from
 * the author's darwinsys-ee project, using EntityHome.
 *  * @author Ian Darwin
 */
@ManagedBean
@SessionScoped
public class SpyHome {
	
	@ManagedProperty(value="#{spyList}")
	private SpyList spyList;

	private Spy instance;

	public SpyList getSpyList() {
		return spyList;
	}

	public void setSpyList(SpyList spyList) {
		this.spyList = spyList;
	}

	public Spy getInstance() {
		return instance;
	}
	
	public void wire(String s) {
		if (s == null || s.length() == 0) {
			create();
			return;
		}
		find(Long.parseLong(s));
	}

	public void create() {
		instance = new Spy();
	}

	public Spy find(long id) {
		for (Spy spy : spyList.findAll()) {
			if (spy.id == id) {
				instance = spy;
				return instance;
			} 
		}
		throw new IllegalArgumentException("Can't find Spy # " + id);
	}
	
	/** In JPA an "update"-type method often doesn't have to
	 * do anything except look good, e.g., it will sport
	 * a Transaction attribute that will make the changes
	 * get committed.
	 */
	public String update() {
		if (instance.id == 0) {
			instance.id = findMaxId() + 1;
			spyList.findAll().add(instance);
		}
		return "SpyList.web";
	}

	private long findMaxId() {
		long max = 0;
		for (Spy spy : spyList.findAll()) {
			if (spy.id > max) {
				max = spy.id;
			}
		}
		return max;
	}
}
