package spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * This is intended to present a JPA-free but JPA-like interface;
 * If you have JPA, a real version of this code can be written for you
 * by use of Apache DeltaSpike Data.
 * @author Ian Darwin
 */
@ManagedBean(name="spyList")
@ApplicationScoped
public class SpyList {
	private static final Spy[] data = {
		new Spy(1, "Jack",  "Bauer", "LAPD"),
		new Spy(7, "James", "Bond",  "MI5"),
		new Spy(2, "Jason", "Bourne", "ex-CIA"),
		new Spy(3, "J.", "B.", "Kingman"),
	};

	private List<Spy> resultList = new ArrayList<>();
	
	public SpyList() {
		resultList.addAll( Arrays.asList(data));
	}
	
	public List<Spy> findAll() {
		return resultList;
	}
}
