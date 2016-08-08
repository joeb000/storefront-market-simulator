package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import storefront.dao.MachineDAO;
import storefront.entities.Customer;
import storefront.entities.Machine;

public class MachineService {

	private static MachineService instance = null;
	protected MachineService() {
		// Exists only to defeat instantiation.
	}
	public static MachineService getInstance() {
		if(instance == null) {
			instance = new MachineService();
		}
		return instance;
	}

	private ArrayList<Machine> machineList = new ArrayList<Machine>();
	private MachineDAO dao = new MachineDAO();
	public int defaultMachineCapacity = 4;


	public Machine getMachineByID(int id){
		for (Machine l : machineList){
			if(l.getMachineID()==id){
				return l;
			}
		}
		return null;
	}

	public void readMachinesFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			Machine loc = parseStringToMachine(line);
			int machineid = commitNewMachine(loc);
			System.out.println("New Machine "+ loc.getMachineName() + "(#"+machineid + " ) added to DB");
		}
	}

	private Machine parseStringToMachine(String machineString){
		Machine machine = new Machine();
		String[] split = machineString.split("\"");
		String[] attributes = split[0].split(",");
		String[] pList = split[1].split(",");
		machine.setMachineID(Integer.parseInt(attributes[0]));
		machine.setMachineName(attributes[1]);
		machine.setLatitude(Double.parseDouble(attributes[2]));
		machine.setLongitude(Double.parseDouble(attributes[3]));
//		for (int i = 0; i < pList.length; i++) {
//			machine.addProuduct(ProductService.getInstance().getProductsByID(Integer.parseInt(pList[i])));
//		}
		return machine;
	}
	
	public void addMachineIDToMachine(Machine l, int mID){
		l.addMachineID(mID);
	}
	

	
	public int commitNewMachine(Machine l){
		return dao.insertNewMachine(l.getMachineName(),l.getLatitude(),l.getLongitude());
	}
	
	public ArrayList<Machine> retrieveAllMachines() {
		return dao.readAllMachinesFromDB();
	}
}
