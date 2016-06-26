package me.hyv.of.game.comp;

import java.util.ArrayList;
import java.util.Collection;

import me.hyv.of.engine.Time;
import me.hyv.of.scene.Component;
import me.hyv.of.shape.HavardCollision;

public class PhysicsPool extends Component {
	private ArrayList<CollitionComponent> components = new ArrayList<>();
	
	public PhysicsPool() {
		
	}
	
	public void registerObject(CollitionComponent cmp) {
		if(!components.contains(cmp))
			components.add(cmp);
	}
	
	public void removeComponent(CollitionComponent cmp) {
		components.remove(cmp);
	}
	
	public Collection<CollitionComponent> getComponents() {
		return components;
	}

	@Override
	public void update() {
		for(int i = 0; i < components.size(); i++) {
			CollitionComponent c1 = components.get(i);
			if(!c1.isSolid())
				continue;
			for(int j = i+1; j < components.size(); j++) {
				CollitionComponent c2 = components.get(j);
				
				if(c1.hasSpeed(0) || c2.hasSpeed(0) && HavardCollision.intersecting(c1, c2)) {
					float normX = -1, normY = 0;
					float hitX = c2.xSpeed - c1.xSpeed;
					float hitY = c2.ySpeed - c1.ySpeed;
					float hitLen = (float)Math.hypot(hitX, hitY);
					float hitXN = hitX / hitLen;
					float hitYN = hitY / hitLen;
					float dot = dot(normX, normY, hitXN, hitYN);
					if(dot > 0) //We don't care
						continue;
					
				}
			}
		}
	}

	@Override
	public void render() {
		
	}
	
	private static float dot(float aX, float aY, float bX, float bY) {
		return aX*bX+aY*bY;
	}
}
