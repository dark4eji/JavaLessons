package pack;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private String name;
	private List<Sportsperson> members = new ArrayList();
	private int teamSize;

	public Team(String name, int teamSize) {
		this.name = name;
		this.teamSize = teamSize;
	}

	public void addToTeam (Sportsperson sportsperson) {
		if (members.size() < teamSize) {
			members.add(sportsperson);
		} else {
			System.out.println("В команде больше нет места");
		}
	}

	public void getName () {
		System.out.println("Название команды — " + name);
	}

	public void getTeamSize () {
		System.out.println("Размер команды — " + teamSize);
	}

	public String getStringName() {
		return name;
	}

	public void getTeamMembers () {
		System.out.println("Состав команды:");
		for (Sportsperson sportsperson : members) {
			System.out.println(sportsperson.getName());
		}
	}

	public void startAction(double courseCosts){
		for (Sportsperson sportsperson : members) {
			sportsperson.run(courseCosts);
		}
	}

	public List<Sportsperson> getTeamArray(){
		return members;
	}

	public void showResults () {
		System.out.println("Результаты соревнований:");
		for (Sportsperson sportsperson : members) {
			if (sportsperson.getCurrentStamina() >= 0) {
				System.out.println(sportsperson.getName() + " — дистанция пройдена");
			} else {
				System.out.println(sportsperson.getName() + " — дистанция не пройдена");
			}
		}
	}
}

