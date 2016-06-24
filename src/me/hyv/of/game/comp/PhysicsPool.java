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
					float intersect = -1;
					do {
						c1.xSpeed*=0.5f;
						c1.ySpeed*=0.5f;
						c2.xSpeed*=0.5f;
						c2.ySpeed*=0.5f;
						c1.getParent().x+=c1.xSpeed*Time.getDelta() * intersect; 
						c1.getParent().y+=c1.ySpeed*Time.getDelta() * intersect; 
						c2.getParent().x+=c2.xSpeed*Time.getDelta() * intersect; 
						c2.getParent().y+=c2.ySpeed*Time.getDelta() * intersect;
						intersect = HavardCollision.intersecting(c1, c2) ? -1 : 1;
					} while(c1.hasSpeed(0.2f) || c2.hasSpeed(0.2f));
				
					c1.xSpeed = 0;
					c1.ySpeed = 0;
					c2.xSpeed = 0;
					c2.ySpeed = 0;
				}
			}
		}
	}

	@Override
	public void render() {
		
	}
}
