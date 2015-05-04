package bfsztg_gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameImages {

	Image desert;
	Image fields;
	Image forest;
	Image hills;
	Image mountains;
	Image sea;
	Image snow;
	Image swamp;
	
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
	
	public Image getRedArcher() {
		return redArcher;
	}

	public void setRedArcher(Image redArcher) {
		this.redArcher = redArcher;
	}

	Image loadImage(String path){
		Image img = null;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			System.out.println(e);
		}
		return img;
	}

	public Image getDesert() {
		return desert;
	}

	public void setDesert(Image desert) {
		this.desert = desert;
	}

	public Image getFields() {
		return fields;
	}

	public void setFields(Image fields) {
		this.fields = fields;
	}

	public Image getForest() {
		return forest;
	}

	public void setForest(Image forest) {
		this.forest = forest;
	}

	public Image getHills() {
		return hills;
	}

	public void setHills(Image hills) {
		this.hills = hills;
	}

	public Image getMountains() {
		return mountains;
	}

	public void setMountains(Image mountains) {
		this.mountains = mountains;
	}

	public Image getSea() {
		return sea;
	}

	public void setSea(Image sea) {
		this.sea = sea;
	}

	public Image getSnow() {
		return snow;
	}

	public void setSnow(Image snow) {
		this.snow = snow;
	}

	public Image getSwamp() {
		return swamp;
	}

	public void setSwamp(Image swamp) {
		this.swamp = swamp;
	}

	public Image getSelected() {
		return selected;
	}

	public void setSelected(Image selected) {
		this.selected = selected;
	}

	public Image getBlueArcher() {
		return blueArcher;
	}

	public void setBlueArcher(Image blueArcher) {
		this.blueArcher = blueArcher;
	}

	public Image getRedInfantry() {
		return redInfantry;
	}

	public void setRedInfantry(Image redInfantry) {
		this.redInfantry = redInfantry;
	}

	public Image getBlueInfantry() {
		return blueInfantry;
	}

	public void setBlueInfantry(Image blueInfantry) {
		this.blueInfantry = blueInfantry;
	}

	public Image getRedCavalry() {
		return redCavalry;
	}

	public void setRedCavalry(Image redCavalry) {
		this.redCavalry = redCavalry;
	}

	public Image getBlueCavalry() {
		return blueCavalry;
	}

	public void setBlueCavalry(Image blueCavalry) {
		this.blueCavalry = blueCavalry;
	}

	public Image getMoveable() {
		return moveable;
	}

	public void setMoveable(Image moveable) {
		this.moveable = moveable;
	}

	public Image getAttackable() {
		return attackable;
	}

	public void setAttackable(Image attackable) {
		this.attackable = attackable;
	}
	
	

		
}
