package bfsztg_gui;

public class GraphicCell {

	Terrain terrain;
	Unit unit;
	Marker marker;
	
	public enum Terrain {
	    DESERT, FIELDS, FOREST, HILLS,
	    MOUNTAINS, SEA, SNOW, SWAMP 
	}
	
	public enum Unit {
	    NONE, BLUE_INFANTRY, BLUE_ARCHER, BLUE_CAVALRY, 
	    RED_INFANTRY, RED_ARCHER, RED_CAVALRY	    
	}
	
	public enum Marker {
	    NONE, SELECTED, MOVEABLE, ATTACKABLE	    
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public GraphicCell(Terrain terrain, Unit unit, Marker marker) {
		super();
		this.terrain = terrain;
		this.unit = unit;
		this.marker = marker;
	}

	public GraphicCell() {
		super();
		this.terrain = Terrain.HILLS;
		this.unit = Unit.NONE;
		this.marker = Marker.NONE;
	}
	
	
	

	
	
}
