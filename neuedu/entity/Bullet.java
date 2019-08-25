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
* @Description: 子弹类
* @author pf
* @date 2019年8月20日 上午11:01:11
*
*/
public class Bullet extends GameObj implements ActionAble {
   //单次播放的对象
	 SinglePlay singlePlay=new SinglePlay();
	//创建速度
	private Integer speed;
	//拿到客户端
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
    //画子弹
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
    
    //子弹越界销毁
	public void outOfBounds() {
		
	if(y<40||y>Constant.GAME_HEIGHT ) {
			gc.bullets.remove(this);
	}
	}
	//随机生成道具
	Random random=new Random();
	
    //子弹打一个怪物
	public boolean hitBoss(Planes planes) {
		Boom boom=new Boom(planes.x, planes.y,gc);
		if(this.getRec().intersects(planes.getRec())&&this.isGood!=planes.isGood) {
//			
			if(planes.isGood){
				singlePlay.play("com/neuedu/sound/SOUND_BOSS_WILD_01.mp3");
				//打中我移除
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

					//移除打中敌人
					gc.enemys.remove(planes);
					//移除子弹
					gc.bullets.remove(this);
					//添加爆炸
					gc.booms.add(boom);
					//随机生成一个道具出来
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
	//子弹打多个怪物
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
	//获取到子弹的矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
    
}
