package db.interfaces;

import java.util.List;
import pojos.Dissability;

public interface DissabilityManager {
	
	public List<Dissability> searchByName (String name);
	public Dissability searchById (Integer id);
	public void add (Dissability disability);
	public void deleteById (Integer id);
	public void updateDissability(Dissability disability);
}