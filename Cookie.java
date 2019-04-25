package cookie;
import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Cookie - a robot by (your name here)
 */
public class Cookie extends AdvancedRobot
{
	int count = 0;

	/**
	 * run: Cookie's default behavior
	 */
	public void run() {
		// Couleur
		setColors(Color.black,Color.black,Color.black); // body,gun,radar

		// Vitesse		
		setMaxVelocity(Rules.MAX_VELOCITY);

		// Robot main loop
		while(true) {
			count++;
			// Se déplace si rien ne se passe
			if (count / 100 == 0) {
				ahead(100);
				setTurnRightRadians(360);
			}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Direction vers le robot scanné
		setTurnRightRadians(normalRelativeAngleDegrees((getHeading() - e.getHeading())));

		// Tir
		if (e.getDistance() < 200 && getEnergy() > 50) {
			fire(Rules.MAX_BULLET_POWER);
		} else {
			fire(Rules.MAX_BULLET_POWER / 2);
		}

		scan();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
	    if (getEnergy() < 75){
	        turnRight(e.getBearing() * -1);
	    }
	    else {
	        turnRight(360);
		}

        ahead(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		back(100);
	}
}
