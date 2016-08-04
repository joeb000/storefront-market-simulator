package storefront.services;

import storefront.dao.SimulatorDAO;

public class SimulatorService {
	
	private static SimulatorService instance = null;
	protected SimulatorService() {
		// Exists only to defeat instantiation.
	}
	public static SimulatorService getInstance() {
		if(instance == null) {
			instance = new SimulatorService();
		}
		return instance;
	}

	private SimulatorDAO dao = new SimulatorDAO();

}
