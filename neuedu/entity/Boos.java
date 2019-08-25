package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;

public class Boos extends Planes implements ActionAble{
   private int speed=1;
	private boolean up=true;
//	private boolean isGood;
	SinglePlay play=new SinglePlay();
   public Boos() {
		// TODO Auto-generated constructor stub
	}
    public Boos(int x,int y,GameClient gc,boolean isGood) {
    	this.x=x;
		this.y=y;
		this.gc=gc;
		this.isGood=isGood;
		this.blood=10000;
		
	}
	//定义一个数组
	private static Image[] imgs=new Image[5]; 
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i]=GetImageUtil.getImg("ep/plane7.png");
		}
	}
	int count=0;
	@Override
		public void draw(Graphics g) {
		   if(count>4) {
			   count=0;
		   }
		
		g.drawImage(imgs[count++], x, y, null);
		move();
	//	g.drawString("当前血量为:"+blood,x , y);
	}	
	@Override
	public void move() {
		
		y+=speed;
		if(y<80) {
			play.play("com/neuedu/sound/SOUND_BOSS_ALETT_01.mp3");
		}
		if(y>150) {
			
			y=150;
			if(up) {
				x-=speed;
			}if (!up) {
				x+=speed;
			}
			
			
			if(x>Constant.GAME_WIDTH-imgs[0].getWidth(null))
			{up=true;}
		if(x<20) {up=false;}
		}
		if(random.nextInt(500)>450) {
			fire();
		}
		
	}
	//随机数
	Random random=new Random();
	//获取到子弹的矩形
			public Rectangle getRec() {
				return new Rectangle(x,y,imgs[0].getWidth(null),imgs[0].getHeight(null));
			}
		@Override
		public void fire() {
			play.play("com/neuedu/sound/SOUND_AVIS_SPECIAL_ENEMYDEATH_01.mp3");
			Bullet b=new Bullet(x+imgs[0].getWidth(null)/2-10, y+imgs[0].getHeight(null)/2-10,"bullet2.png",gc,false);
			gc.bullets.add(b);
		}
}
