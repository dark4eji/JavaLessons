package pack;

public class Sportsperson implements Performable {
	private Team team;
	private String name;
	private double baseStamina;
	private double currentStamina;
	
	public Sportsperson(String name, Team team, double baseStamina){
		this.name = name;
		this.team = team;	
		this.baseStamina = baseStamina;
	}	
	
	public String getName(){
		return name;
	}
	
	public void getTeamName(){
		System.out.println(name + " состоит в команде " + this.team.getStringName());
	}

	public void run(double courseCosts) {
		currentStamina = this.baseStamina;
		currentStamina -= courseCosts;
	}
	
	public double getCurrentStamina() {
		return currentStamina;
	}
}