package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.naming.InitialContext;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;

/**
* @ClassName: Planes
* @Description: 创建飞机类
* @author pf
* @date 2019年8月19日 下午3:32:42
*
*/
public class Planes extends GameObj implements ActionAble{
	SinglePlay play=new SinglePlay();
	//速度
	private int speed;
	//方向布尔变量
	private boolean left,up,right,down;
    //客户端拿过来
	public GameClient gc;
	//判断是否是我军还是敌军
	 public boolean isGood;
	 //添加飞机子弹等级变量
	 public int BulletLevel=1;
	 //添加血值
	 public int blood;
	public Planes() {
		
	}
	public Planes(int x,int y,String imgName ,GameClient gc, boolean isGood) {
		
		this.x=x;
		this.y=y;
		this.img=GetImageUtil.getImg(imgName);
		this.speed=20;
		this.gc=gc;
		this.isGood=isGood;
		this.blood=100;
		
		
	}
	//移动的方法
	@Override
	public void move() {
		if(left) 
		{
		x-=speed;	
		}
		if(up) 
		{
			y-=speed;
		}
		if(right)
		{
			x+=speed;
		}
		if(down)
		{
			y+=speed;
		}
		outofBound();
	}
	//画一个飞机
	@Override
	public void draw(Graphics g) {
	g.drawImage(img, x,y,null);
		move();	
		g.drawString("当前我方的血量:"+blood, 15,185);
	}
	//处理飞机的边界问题
	public void outofBound()
	{
		if(y<=10) 
		{
			y=10;
		}
		if(x<=10) 
		{
		x=10;	
		}
		if(x>=Constant.GAME_WIDTH-img.getWidth(null)-7)
		{
			x=Constant.GAME_WIDTH-img.getWidth(null)-7;
		}
		if(y>=Constant.GAME_HEIGHT-img.getHeight(null))
		{
			y=Constant.GAME_HEIGHT-img.getHeight(null);
		}
		
	}
	//鼠标摁下
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left=true;
			break;
		case KeyEvent.VK_W:
			up=true;
			break;
		case KeyEvent.VK_D:
			right=true;
			break;
		case KeyEvent.VK_S:
			down=true;
			break;
	
		
		default:
			break;
		}
		
	}
	//释放键盘
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left=false;
			break;
		case KeyEvent.VK_W:
			up=false;
			break;
		case KeyEvent.VK_D:
			right=false;
			break;
		case KeyEvent.VK_S:
			down=false;
			break;
		case KeyEvent.VK_M:
			fire();
			break;
		default:
			break;
		}		
	}
	//我方飞机开火
	public void fire() {
		play.play("com/neuedu/sound/SOUND_AVIS_SPECIAL_ENEMYDEATH_01.mp3");
		Bullet b=new Bullet(x+this.img.getWidth(null)/2-10, y,"bullet/bu"+BulletLevel+".png",gc,true);
		gc.bullets.add(b);
		outofBound();
	}
	//获取到子弹的矩形
		public Rectangle getRec() {
			return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
		}
	//检测我方角色碰撞到道具
		public void containItem(Prop prop) {
			if(this.getRec().intersects(prop.getRect()))
			{   play.play("com/neuedu/sound/SOUND_BUYSCCUCESS_01.mp3");
				//移除道具
				gc.props.remove(prop);
				//更改子弹的等级
				if(BulletLevel>7) {
					BulletLevel=8;
					return;
				}
				this.BulletLevel+=1;
			}
		}
		//检测我方角色碰撞到多个角色
		public void containItems(List<Prop> props) {
			if(props==null) {
				return;
			}else {
				for(int i=0;i<props.size();i++) {
					containItem(props.get(i));
				}
			}
		}
}
