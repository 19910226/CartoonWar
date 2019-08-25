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
* @Description: �����ɻ���
* @author pf
* @date 2019��8��19�� ����3:32:42
*
*/
public class Planes extends GameObj implements ActionAble{
	SinglePlay play=new SinglePlay();
	//�ٶ�
	private int speed;
	//���򲼶�����
	private boolean left,up,right,down;
    //�ͻ����ù���
	public GameClient gc;
	//�ж��Ƿ����Ҿ����ǵо�
	 public boolean isGood;
	 //��ӷɻ��ӵ��ȼ�����
	 public int BulletLevel=1;
	 //���Ѫֵ
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
	//�ƶ��ķ���
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
	//��һ���ɻ�
	@Override
	public void draw(Graphics g) {
	g.drawImage(img, x,y,null);
		move();	
		g.drawString("��ǰ�ҷ���Ѫ��:"+blood, 15,185);
	}
	//����ɻ��ı߽�����
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
	//�������
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
	//�ͷż���
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
	//�ҷ��ɻ�����
	public void fire() {
		play.play("com/neuedu/sound/SOUND_AVIS_SPECIAL_ENEMYDEATH_01.mp3");
		Bullet b=new Bullet(x+this.img.getWidth(null)/2-10, y,"bullet/bu"+BulletLevel+".png",gc,true);
		gc.bullets.add(b);
		outofBound();
	}
	//��ȡ���ӵ��ľ���
		public Rectangle getRec() {
			return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
		}
	//����ҷ���ɫ��ײ������
		public void containItem(Prop prop) {
			if(this.getRec().intersects(prop.getRect()))
			{   play.play("com/neuedu/sound/SOUND_BUYSCCUCESS_01.mp3");
				//�Ƴ�����
				gc.props.remove(prop);
				//�����ӵ��ĵȼ�
				if(BulletLevel>7) {
					BulletLevel=8;
					return;
				}
				this.BulletLevel+=1;
			}
		}
		//����ҷ���ɫ��ײ�������ɫ
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
