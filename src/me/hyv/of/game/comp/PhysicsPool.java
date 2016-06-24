package me.hyv.of.game.comp;

import java.util.ArrayList;
import java.util.Collection;

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
		System.out.println(components.size());
		for(int i = 0; i < components.size(); i++) {
			for(int j = i+1; j < components.size(); j++) {
				if(HavardCollision.intersecting(components.get(i), components.get(j))) {
					components.get(i).getParent().kill();
					System.out.println("Test");
				}
			}
		}
	}

	@Override
	public void render() {
		
	}
}
