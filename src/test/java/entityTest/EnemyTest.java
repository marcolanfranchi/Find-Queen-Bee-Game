package entityTest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import entity.Enemy;
import entity.Node;
import main.GamePanel;

public class EnemyTest {
    
    private Enemy enemy;
    private Node[][] node;
    private Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    int tempGoalCol;
    int tempGoalRow;
    int tempStartCol;
    int tempStartRow;
    int nextX;
    int nextY;
    int enLeftX;
    int enRightX;
    int enTopY;
    int enDownY;
    boolean collision = false;
    boolean enemyCollision = false;
    int tileNumUp;
    int tileNumDown;
    int tileNumLeft;
    int tileNumRight;
	GamePanel gp;

    @BeforeEach
    void setup(){
		gp = new GamePanel();
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        enemy = new Enemy(gp);
    }

    @Test
    void testEnemyIsPlacedOnMap(){
        enemy.setRandomStartPoint();
        assertTrue(enemy.worldX > 0 && enemy.worldX <= 23 * enemy.gamePanel.tileSize);
        assertTrue(enemy.worldY > 0 && enemy.worldY <= 23 * enemy.gamePanel.tileSize);
    }

    @Test
    void testNodeIsInstantiated(){
        GamePanel gamePanel = new GamePanel();
        node = new Node[gamePanel.worldWidth][gamePanel.worldHeight];
		int col = 0;
		int row = 0;
		while (col < gamePanel.worldWidth && row < gamePanel.worldHeight) {
			node[col][row] = new Node(col, row);
			col++;
			if (col == gamePanel.worldWidth) {
				col = 0;
				row++;
			}
		}
        assertNotNull(node);
    }

    @Test
    void testNodeIsSetted(){
        GamePanel gamePanel = new GamePanel();
        testNodeIsInstantiated();
        int goalCol = gamePanel.bee.worldX;
        int goalRow = gamePanel.bee.worldY;
        int startCol = enemy.worldX / gamePanel.tileSize;
        int startRow = enemy.worldY / gamePanel.tileSize;

        tempGoalCol = goalCol;
        tempGoalRow = goalRow;
        tempStartCol = startCol;
        tempStartRow = startRow;

        // Enemy's start position
        startNode = node[startCol][startRow];
        assertNotNull(startNode);

		// Current enemies' position 
		currentNode = node[startCol][startRow];
        assertNotNull(currentNode);

		// Bee's current position
		goalNode = node[goalCol][goalRow];
		assertNotNull(goalNode);

        // Add nodes to a list
		openList.add(currentNode);
        assertNotNull(openList);      
    }

    @Test
    void testGetCostFunction(){
        testNodeIsInstantiated();
        testNodeIsSetted();
        // G Cost:
		int xDistance = Math.abs(tempStartCol - startNode.col);
		int yDistance = Math.abs(tempStartRow - startNode.row);
		int gCost = xDistance + yDistance;
        assertNotNull(gCost);


		// H cost: Sum of X distance between the Bee and the current check node and 
		//			Y distance between the Be and the current check node
		xDistance = Math.abs(tempStartCol - goalNode.col);
		yDistance = Math.abs(tempStartRow - goalNode.row);
        int hCost = xDistance + yDistance;
        assertNotNull(hCost);

		// F cost: Sun of G cost and H Cost
        assertNotNull(gCost + hCost);
    }

    @Test
    void testEnemyCanMoveUp(){
        int tileNumUp = 0;
        if (tileNumUp == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "up";
        }
        assertNotNull(enemy.direction);
        assertFalse(enemyCollision);
    }

    @Test
    void testEnemyCanNotMoveUp(){
        int tileNumUp = 1;
        if (tileNumUp == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "up";
        }
        assertNull(enemy.direction);
        assertTrue(enemyCollision);
    }

    @Test
    void testEnemyCanMoveDown(){
        int tileNumDown = 0;
        if (tileNumDown == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "down";
        }
        assertNotNull(enemy.direction);
        assertFalse(enemyCollision);
    }

    @Test
    void testEnemyCanNotMoveDown(){
        int tileNumDown = 1;
        if (tileNumDown == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "down";
        }
        assertNull(enemy.direction);
        assertTrue(enemyCollision);
    }

    @Test
    void testEnemyCanMoveLeft(){
        int tileNumLeft = 0;
        if (tileNumLeft == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "left";
        }
        assertNotNull(enemy.direction);
        assertFalse(enemyCollision);
    }

    @Test
    void testEnemyCanNotMoveLeft(){
        int tileNumLeft = 1;
        if (tileNumLeft == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "left";
        }
        assertNull(enemy.direction);
        assertTrue(enemyCollision);
    }

    @Test
    void testEnemyCanMoveRight(){
        int tileNumRight = 0;
        if (tileNumRight == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "right";
        }
        assertNotNull(enemy.direction);
        assertFalse(enemyCollision);
    }

    @Test
    void testEnemyCanNotMoveRight(){
        int tileNumRight = 1;
        if (tileNumRight == 1) {
            enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "right";
        }
        assertNull(enemy.direction);
        assertTrue(enemyCollision);
    }

    @Test
    void testEnemyMovesUp(){
        enTopY = 10;
        nextY = 1;
        enLeftX = 10;
        nextX = 1;
        enRightX = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + enemy.gamePanel.tileSize) {
            enemy.worldY -= enemy.speed;
            enemy.direction = "up";
        }else{
            enemy.direction = null;
        }

        assertEquals(5, enemy.worldY);
        assertEquals(10, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesDown(){
        enTopY = 1;
        nextY = 10;
        enLeftX = 10;
        nextX = 1;
        enRightX = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + enemy.gamePanel.tileSize) {
            enemy.worldY += enemy.speed;
            enemy.direction = "down";
        }else{
            enemy.direction = null;
        }

        assertEquals(15, enemy.worldY);
        assertEquals(10, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesLeft(){
        enTopY = 10;
        nextY = 1;
        enLeftX = 10;
        nextX = 1;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY >= nextY && enDownY < nextY + enemy.gamePanel.tileSize) {
            if (enLeftX > nextX) {
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(5, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesRight(){
        enTopY = 10;
        nextY = 1;
        enLeftX = 1;
        nextX = 10;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY >= nextY && enDownY < nextY + enemy.gamePanel.tileSize) {
            if (enLeftX < nextX) {
                enemy.worldX += enemy.speed;
                enemy.direction = "right";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(15, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesUp2(){
        enTopY = 10;
        nextY = 1;
        enLeftX = 10;
        nextX = 1;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY > nextY && enLeftX > nextX){
            enemy.worldY -= enemy.speed;
            enemy.direction = "up";
            if(collision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(5, enemy.worldY);
        assertEquals(10, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesLeft2(){
        enTopY = 10;
        nextY = 1;
        enLeftX = 10;
        nextX = 1;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        collision = true;
        if (enTopY > nextY && enLeftX > nextX){
            enemy.worldY -= enemy.speed;
            enemy.direction = "up";
            if(collision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(5, enemy.worldY);
        assertEquals(5, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesUp3(){
        enTopY = 10;
        nextY = 1;
        enLeftX = 1;
        nextX = 10;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY > nextY && enLeftX < nextX){
            enemy.direction = "up";
            if(collision == true){
                enemy.worldX += enemy.speed;
                enemy.direction = "right";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(10, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesRight2(){
        enTopY = 10;
        nextY = 1;
        enLeftX = 1;
        nextX = 10;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        collision = true;
        if (enTopY > nextY && enLeftX < nextX){
            enemy.direction = "up";
            if(collision == true){
                enemy.worldX += enemy.speed;
                enemy.direction = "right";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(15, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesDown2(){
        enTopY = 1;
        nextY = 10;
        enLeftX = 10;
        nextX = 1;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY < nextY && enLeftX > nextX){
            enemy.direction = "down";
            if(collision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(10, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesLeft3(){
        enTopY = 1;
        nextY = 10;
        enLeftX = 10;
        nextX = 1;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        collision = true;
        if (enTopY < nextY && enLeftX > nextX){
            enemy.direction = "down";
            if(collision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(5, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesDown3(){
        enTopY = 1;
        nextY = 10;
        enLeftX = 1;
        nextX = 10;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        if (enTopY < nextY && enLeftX < nextX){
            enemy.direction = "down";
            if(collision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "right";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(10, enemy.worldX);
        assertNotNull(enemy.direction);
    }

    @Test
    void testEnemyMovesRight3(){
        enTopY = 1;
        nextY = 10;
        enLeftX = 1;
        nextX = 10;
        enRightX = 1;
        enDownY = 1;
        enemy.worldX = 10;
        enemy.worldY = 10;
        enemy.speed = 5;
        collision = true;
        if (enTopY < nextY && enLeftX < nextX){
            enemy.direction = "down";
            if(collision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "right";
            }
        }else{
            enemy.direction = null;
        }

        assertEquals(10, enemy.worldY);
        assertEquals(5, enemy.worldX);
        assertNotNull(enemy.direction);
    }

	@Test
	void testDrawUp() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "up";

		enemy.draw(g2);
		assertNotNull(enemy);
	}

	@Test
	void testDrawDown() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "down";

		enemy.draw(g2);
		assertNotNull(enemy);
	}

	@Test
	void testDrawLeft() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "left";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawRight() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawNone() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "none";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg1() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = -600;
		enemy.worldY = 10;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg2() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 680;
		enemy.worldY = 10;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg3() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = -365;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg4() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 470;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

}
