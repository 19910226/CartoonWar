package com.neuedu.entity;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;

/**
* @ClassName: Bullet
* @Description: �ӵ���
* @author pf
* @date 2019��8��20�� ����11:01:11
*
*/
public class Bullet extends GameObj implements ActionAble {
   //���β��ŵĶ���
	 SinglePlay singlePlay=new SinglePlay();
	//�����ٶ�
	private Integer speed;
	//�õ��ͻ���
	public GameClient gc;
	public boolean isGood;
	public Bullet() {
		}
    public Bullet(int x,int y,String imgName,GameClient gc,boolean isGood)
    {
    	this.x=x;
    	this.y=y;
    	this.img=GetImageUtil.getImg(imgName);
	    this.isGood=isGood;
    	this.speed=10;
        this.gc=gc;
    }
    //���ӵ�
	@Override
	public void move() {
		if(isGood) {
			y-=speed;	
		}else {
			y+=speed;
		}
		
		outOfBounds();
	}
	@Override
	public void draw(Graphics g) {
	    g.drawImage(img,x,y,null);
		move(); 
	}
    
    //�ӵ�Խ������
	public void outOfBounds() {
		
	if(y<40||y>Constant.GAME_HEIGHT ) {
			gc.bullets.remove(this);
	}
	}
	//������ɵ���
	Random random=new Random();
	
    //�ӵ���һ������
	public boolean hitBoss(Planes planes) {
		Boom boom=new Boom(planes.x, planes.y,gc);
		if(this.getRec().intersects(planes.getRec())&&this.isGood!=planes.isGood) {
//			
			if(planes.isGood){
				singlePlay.play("com/neuedu/sound/SOUND_BOSS_WILD_01.mp3");
				//�������Ƴ�
				planes.blood-=5;
				if(planes.blood==0) {
					singlePlay.play("com/neuedu/sound/SOUND_BOSS_WILD_01.mp3");
					gc.planes2.remove(planes);
					gc.booms.add(boom);
				}
				gc.bullets.remove(this);
				//boom.setLive(false);
			}else {
				singlePlay.play("com/neuedu/sound/SOUND_BOSS_WILD_01.mp3");
				
				if(planes instanceof Boos) {
					planes.blood -=100;
					gc.bullets.remove(this);
					gc.booms.add(boom);
					
					if(planes.blood<=0) {
						singlePlay.play("com/neuedu/sound/VOICE_INGAME_PLAY_04.mp3");
						gc.bosss.remove(planes);
						gc.bullets.remove(this);
						gc.booms.add(boom);
					}
					
				}else {

					//�Ƴ����е���
					gc.enemys.remove(planes);
					//�Ƴ��ӵ�
					gc.bullets.remove(this);
					//��ӱ�ը
					gc.booms.add(boom);
					//�������һ�����߳���
					if(random.nextInt(500)>200) {
						if(planes instanceof EnemyPlane) {
							EnemyPlane enemyPlane=(EnemyPlane)planes;
							Prop prop=new Prop(planes.x+enemyPlane.imgs1[0].getWidth(null)/2,planes.y+enemyPlane.imgs1[0].getHeight(null)/2,"prop/dao.png");  
						    gc.props.add(prop);
						    gc.bullets.remove(this);
						}
					}
				}
				
				
			}
			
			return true;
		}
		
		return false;
	}
	//�ӵ���������
	public boolean hiteBoss(List<Planes> Planers) {
		if(Planers==null) {
			return false;
		}
		for (int i = 0; i < Planers.size() ; i++) {
			if(hitBoss(Planers.get(i))) {
				return true;
			}
			
		}
		return false;
	}
	//��ȡ���ӵ��ľ���
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
    
}
