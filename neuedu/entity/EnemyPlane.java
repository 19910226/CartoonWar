package com.neuedu.entity;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: EnemyPlane
* @Description: 敌军飞机 
* @author pf
* @date 2019年8月20日 下午11:46:17
*
*/
public class EnemyPlane extends Planes implements ActionAble {
     private Integer enemyType;
     private int speed;
     private GameClient gc;
     private boolean up=true;
     public static Image[] imgs1= {
    	GetImageUtil.getImg("plane/army5.png"),
    	GetImageUtil.getImg("plane/army5.png"),
    	GetImageUtil.getImg("plane/army5.png"),
    	GetImageUtil.getImg("plane/army5.png"),
    	GetImageUtil.getImg("plane/army5.png")
    	
     };
	public EnemyPlane() {
		 
	}
	
    public  EnemyPlane(int x,int y,int enemyType,GameClient gc,boolean isGood) {
    	this.x=x;
    	this.y=y;
    	this.enemyType=enemyType;
    	this.speed=1;
    	this.gc=gc;
    	this.isGood=isGood;
    } 
    
    @Override
    public void move() {
    	y+=speed;
		if(y<80) {
			//play.play("com/neuedu/sound/SOUND_BOSS_ALETT_01.mp3");
		}
		if(y>50) {
			
			//y=150;
			if(up) {
				x-=speed;
			}if (!up) {
				x+=speed;
			}
			
			
		if(x>Constant.GAME_WIDTH-imgs1[0].getWidth(null))
			{up=true;}
		if(x<20) {up=false;}
		}
		if(random.nextInt(500)>450) {
			fire();
		}
    }
    int count=0;
    @Override
    public void draw(Graphics g) {
    	if(count>4) {
    		count = 0;
    	}
    	g.drawImage(imgs1[count++], x, y,null);
    	
    	
    	move();
    	if(random.nextInt(500)>400) {
    		fire();
    	}
    	
    	
    	
    }
    //随机数
  Random random=new Random();
    //敌军发火
    public void fire() {
    	Bullet bullet=new Bullet(x+imgs1[0].getWidth(null)/2,y+imgs1[0].getHeight(null),"zd.png",gc,false);
    	if(y>=0) {
    		
        	gc.bullets.add(bullet);
    }
    //	/Bullet bullet=new Bullet(x+imgs1[0].getWidth(null)/2,y+imgs1[0].getHeight(null),"zd.png",gc,false);
    //	gc.bullets.add(bullet);
    }
    //获取子弹的矩形
    public Rectangle getRec() {
		return new Rectangle(x,y,this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
	}
}
