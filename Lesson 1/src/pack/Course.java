package pack;


import java.util.ArrayList;
import java.util.List;

public class Course{
	private double trampolineHeight;
	private double trackLength;
	private List<Team> teams = new ArrayList<>();
	
	private int trampolinesAmount = 6;

	public Course(double trampolineHeight, double trackLength) {
		this.trampolineHeight = trampolineHeight;
		this.trackLength = trackLength;		
	}

	public void addNewTeam(Team team){
		teams.add(team);
	}

	public void startCompetition() {
		for (int i = 0; i < teams.size(); i++) {
			System.out.println("**** Выступает команда " + teams.get(i).getStringName()
					+ " ****");
			for (Team team : teams) {
				team.startAction(getCourseCosts());
			}

			teams.get(i).showResults();
			System.out.println();
		}
	}

	private double getCourseCosts(){
		return (trampolineHeight * trampolinesAmount) + trackLength;
	}
	
}
