package Entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Bee extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

	public final int screenX;
	public final int screenY;


    public Bee(GamePanel gp, KeyHandler kh) {

        this.gamePanel = gp;
        this.keyHandler = kh;

		this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // default values
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 2;
		speed = 6;
        direction = "down";
        getBeeImage();
    }

    public void getBeeImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed == true) {
			worldY -= speed;
            direction = "up";
        } else if (keyHandler.downPressed == true) {
			worldY += speed;
            direction = "down";
        } else if (keyHandler.leftPressed == true) {
			worldX -= speed;
            direction = "left";
        } else if (keyHandler.rightPressed == true) {
			worldX += speed;
            direction = "right";
        }

        spriteCounter ++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (direction == "up") {
            if (spriteNum == 1) {
                image = up1; 
            } else image = up2;
        } else if (direction == "down") {
            if (spriteNum == 1) {
                image = down1; 
            } else image = down2;
        } else if (direction == "left") {
            if (spriteNum == 1) {
                image = left1; 
            } else image = left2;
        } else if (direction == "right") {
            if (spriteNum == 1) {
                image = right1; 
            } else image = right2;
        }
		g2.drawImage(image, screenX, screenY, 32, 32, null);
    }
}
