package robot;

import action.Action;
import config.Constants;
import plateau.Axis;
import team.Team;
import team.View;

import java.util.List;

/**
 * @author boinc
 */

public abstract class Robot {

	private Axis axis, objective;
	private View view;
	private Team team;
	private int energy;

	public Robot(View view, Team team) {
		this.view = view;
		this.team = team;
		this.axis = new Axis(calculateAxisXByTeam(), calculateAxisYByTeam());
	}

	public Axis getAxis() {
		return this.axis;
	}

	public void setAxis(Axis axis) {
		this.axis = axis;
	}

	public Axis getObjective() {
		return this.objective;
	}

	public void setObjective(Axis objective) {
		this.objective = objective;
	}

	public View getView() {
		return this.view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Team getTeam() {
		return this.team;
	}

	public int getNumberTeam() {
		return this.team.getTeam();
	}

	public int getEnergy() {
		return this.energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void losesEnergyAfterAction() {
		this.energy = this.energy - getDamageByAction();
	}

	public void losesEnergyAfterMove() {
		this.energy = this.energy - getDamageByMove();
	}

	public void revoke() {
		this.view.getPlateau().getCell(this.axis).clearCell();
		this.team.revokeRobot(this);
	}

	public boolean isDead() {
		return this.energy <= 0;
	}

	public boolean isBased() {
		return this.axis.equals(new Axis(calculateAxisXByTeam(),
			calculateAxisYByTeam()));
	}

	public abstract void suddenByShoot(Robot robot);

	public abstract void suddenByMine();

	public abstract String getType();

	public abstract int getDamageByAction();

	public abstract int getDamageByShoot();

	public abstract int getDamageByMove();

	public abstract int getDamageByMine();

	public abstract List<Axis> getMoves();

	public abstract void isHeals();

	public abstract Action selectedAction();

	private int calculateAxisXByTeam() {
		switch (this.team.getTeam()) {
			case Constants.FIRST_TEAM:
				return 0;
			case Constants.SECOND_TEAM:
				return view.getPlateau().getWidth() - 1;
			default:
				return -1;
		}
	}

	private int calculateAxisYByTeam() {
		switch (this.team.getTeam()) {
			case Constants.FIRST_TEAM:
				return 0;
			case Constants.SECOND_TEAM:
				return view.getPlateau().getLength() - 1;
			default:
				return -1;
		}
	}

	public abstract Action chosesDisplacement(List<Axis> displacement);
}
