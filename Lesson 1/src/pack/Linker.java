package pack;

public class Linker{
	public void createWorld(){
		Team eagles = new Team("Eagles", 4);
		Team lions = new Team("Lions", 1);
		Course course = new Course(1.5, 110);
		
		Sportsperson person1 = new Sportsperson("Jake", eagles, 160);
		Sportsperson person2 = new Sportsperson("Mary", eagles, 130);
		Sportsperson person3 = new Sportsperson("Jerry", eagles, 110);
		Sportsperson person4 = new Sportsperson("Sarah", eagles, 100);

		Sportsperson person5 = new Sportsperson("Jarvis", lions, 200);

		eagles.addToTeam(person1);
		eagles.addToTeam(person2);
		eagles.addToTeam(person3);
		eagles.addToTeam(person4);

		lions.addToTeam(person5);

		course.addNewTeam(eagles);
		course.addNewTeam(lions);

		person1.getTeamName();
		person5.getTeamName();

		System.out.println();

		eagles.getName();
		eagles.getTeamSize();
		eagles.getTeamMembers();

		System.out.println();
		
		course.startCompetition();
	}
}