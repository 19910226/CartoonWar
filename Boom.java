package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: Boom
* @Description: 爆炸类
* @author pf
* @date 2019年8月21日 下午6:50:08
*
*/
public class Boom extends GameObj implements ActionAble{
    private boolean isLive;
    private GameClient gc;
    
	
	public boolean isLive() {
		return isLive;
	}

	
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}


	//动态初始化
	private static Image[] boomImgs=new Image[16];
	static {
		for (int i = 0; i < 16; i++) {
			boomImgs[i]=GetImageUtil.getImg("boom/"+(i+1)+".png");
		}
	}		
	public Boom() {}

	public Boom(int x , int y,GameClient gc) {
	this.x=x;
	this.y=y;
	this.isLive=true;
	this.gc=gc;
}	

    @Override
	public void move() {
		
		
	}
    int count=0;
	@Override
	public void draw(Graphics g) {
		if(isLive) {
			if(count>15)
			{
			//count=0;
				isLive=false;				
				gc.booms.remove(this);
				return;
			}
			g.drawImage(boomImgs[count++], x, y,null);
		}
		
	}

}
