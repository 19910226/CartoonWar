package com.neuedu.entity;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
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
    
     public static Image[] imgs1= {
    	GetImageUtil.getImg("ep/plane6.png"),
    	GetImageUtil.getImg("ep/plane7.png"),
    	GetImageUtil.getImg("ep/plane8.png"),
    	GetImageUtil.getImg("ep/plane9.png"),
    	GetImageUtil.getImg("ep/plane10.png")
    	
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
    }
    int count=0;
    @Override
    public void draw(Graphics g) {
    	if(count>4) {
    		count = 0;
    	}
    	g.drawImage(imgs1[count++], x, y,null);
    	move();
    	if(random.nextInt(500)>480) {
    		fire();
    	}
    	
    	
    	
    }
    //随机数
  Random random=new Random();
    //敌军发火
    public void fire() {
    	Bullet bullet=new Bullet(x+imgs1[0].getWidth(null)/2,y+imgs1[0].getHeight(null),"zd.png",gc,false);
    	gc.bullets.add(bullet);
    }
    //获取子弹的矩形
    public Rectangle getRec() {
		return new Rectangle(x,y,this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
	}
}
