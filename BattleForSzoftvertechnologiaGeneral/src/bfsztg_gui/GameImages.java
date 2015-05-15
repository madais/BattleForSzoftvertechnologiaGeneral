package bfsztg_gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * The class which initalizes and holds the graphic images
 * for the game.
 * @author fhenrir
 *
 */
public class GameImages {
	
	Image desert;
	Image fields;
	Image forest;
	Image hills;
	Image mountains;
	Image sea;
	Image snow;
	Image swamp;
	Image castle;
	
	Image redArcher;
	Image blueArcher;
	Image redInfantry;
	Image blueInfantry;
	Image redCavalry;
	Image blueCavalry;
	
	Image selected;
	Image moveable;
	Image attackable;
	
	
	
	GameImages(){
		this.desert 		= loadImage("desert.png");
		this.fields 		= loadImage("fields.png");
		this.forest 		= loadImage("forest.png");
		this.hills 			= loadImage("hills.png");
		this.mountains 		= loadImage("mountains.png");
		this.sea 			= loadImage("sea.png");
		this.snow 			= loadImage("snow.png");
		this.swamp 			= loadImage("swamp.png");
		this.castle 		= loadImage("castle.png");
		
		this.redArcher 		= loadImage("red_archer.png");
		this.blueArcher  	= loadImage("blue_archer.png");
		this.redInfantry 	= loadImage("red_samurai.png");
		this.blueInfantry  	= loadImage("blue_samurai.png");
		this.redCavalry 	= loadImage("red_knight.png");
		this.blueCavalry  	= loadImage("blue_knight.png");
		
		this.selected 		= loadImage("selected.png");
		this.moveable 		= loadImage("moveable.png");
		this.attackable 	= loadImage("attackable.png");
	}
	

	/**
	 * Loads an image as a stream resource from the given relative path.
	 * @param path The relative path of the image.
	 * @return
	 */
	Image loadImage(String path){
		Image img = null;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			System.out.println(e);
		}
		return img;
	}

	/**
	 * Returns image of the desert terrain type.
	 * @return
	 */
	public Image getDesert() {
		return desert;
	}

	public void setDesert(Image desert) {
		this.desert = desert;
	}
	
	/**
	 * Returns image of the fields terrain type.
	 * @return
	 */
	public Image getFields() {
		return fields;
	}

	public void setFields(Image fields) {
		this.fields = fields;
	}

	/**
	 * Returns image of the forest terrain type.
	 * @return
	 */
	public Image getForest() {
		return forest;
	}

	public void setForest(Image forest) {
		this.forest = forest;
	}
	
	/**
	 * Returns image of the hills terrain type.
	 * @return
	 */
	public Image getHills() {
		return hills;
	}

	public void setHills(Image hills) {
		this.hills = hills;
	}

	/**
	 * Returns image of the mountains terrain type.
	 * @return
	 */
	public Image getMountains() {
		return mountains;
	}

	public void setMountains(Image mountains) {
		this.mountains = mountains;
	}

	/**
	 * Returns image of the sea terrain type.
	 * @return
	 */
	public Image getSea() {
		return sea;
	}

	public void setSea(Image sea) {
		this.sea = sea;
	}

	/**
	 * Returns image of the snowy terrain type.
	 * @return
	 */
	public Image getSnow() {
		return snow;
	}

	public void setSnow(Image snow) {
		this.snow = snow;
	}

	/**
	 * Returns image of the swamps terrain type.
	 * @return
	 */
	public Image getSwamp() {
		return swamp;
	}

	public void setSwamp(Image swamp) {
		this.swamp = swamp;
	}

	/**
	 * Returns image of the selected unit marker.
	 * @return
	 */
	public Image getSelected() {
		return selected;
	}

	public void setSelected(Image selected) {
		this.selected = selected;
	}

	/**
	 * Returns image of the blue archer unit.
	 * @return
	 */
	public Image getBlueArcher() {
		return blueArcher;
	}

	public void setBlueArcher(Image blueArcher) {
		this.blueArcher = blueArcher;
	}

	/**
	 * Returns image of the red infantry unit.
	 * @return
	 */
	public Image getRedInfantry() {
		return redInfantry;
	}

	public void setRedInfantry(Image redInfantry) {
		this.redInfantry = redInfantry;
	}

	/**
	 * Returns image of the blue infantry unit.
	 * @return
	 */
	public Image getBlueInfantry() {
		return blueInfantry;
	}

	public void setBlueInfantry(Image blueInfantry) {
		this.blueInfantry = blueInfantry;
	}

	/**
	 * Returns image of the red cavalry unit.
	 * @return
	 */
	public Image getRedCavalry() {
		return redCavalry;
	}

	public void setRedCavalry(Image redCavalry) {
		this.redCavalry = redCavalry;
	}

	/**
	 * Returns image of the blue cavalry unit.
	 * @return
	 */
	public Image getBlueCavalry() {
		return blueCavalry;
	}

	public void setBlueCavalry(Image blueCavalry) {
		this.blueCavalry = blueCavalry;
	}

	/**
	 * Returns image of marker, which represents an area the unit can move into.
	 * @return
	 */
	public Image getMoveable() {
		return moveable;
	}

	public void setMoveable(Image moveable) {
		this.moveable = moveable;
	}

	/**
	 * Returns image of marker, which represents an area with a unit,
	 * which the selected unit can attack.
	 * @return
	 */
	public Image getAttackable() {
		return attackable;
	}

	public void setAttackable(Image attackable) {
		this.attackable = attackable;
	}

	/**
	 * Returns image of the castle terrain type.
	 * In the end of every round it increases the
	 * owner's victory points counter.
	 * @return
	 */
	public Image getCastle() {
		return castle;
	}

	public void setCastle(Image castle) {
		this.castle = castle;
	}
	
	/**
	 * Returns image of the red archer unit.
	 * @return
	 */
	public Image getRedArcher() {
		return redArcher;
	}

	public void setRedArcher(Image redArcher) {
		this.redArcher = redArcher;
	}

		
}
