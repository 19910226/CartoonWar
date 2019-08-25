package com.neuedu.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.constant.Constant;
import com.neuedu.entity.BackGround;
import com.neuedu.entity.Boom;
import com.neuedu.entity.Boos;
import com.neuedu.entity.Bullet;
import com.neuedu.entity.EnemyPlane;
import com.neuedu.entity.Planes;
import com.neuedu.entity.Prop;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SoundPlayer;

/**
* @ClassName: GameClient
* @Description: 游戏客户端
* @author Zpf
* @date 2019年8月18日 下午12:33:39
* @version 0.1
*/


public class GameClient extends Frame {
	//创建飞机
	   //  Planes planes=new Planes(150, 200, "plane/hero1.png",this,true);
	//创建子弹
//	 Bullet bullet=new Bullet(200,300,"zd.png");
   //创建我方集合
	     public List<Planes> planes2=new ArrayList<>();
	     //创建子弹的集合
	   public   List<Bullet> bullets=new ArrayList<>();
	 //创建一个道具
	 //  Prop prop=new Prop(400, 300,"prop/dao.png");
	   //创建背景图
	   BackGround backImg=new BackGround(0, -1,"beij.png" );
	 //创建道具集合
	   public List<Prop> props=new ArrayList<>();
	   //创建一个爆炸集合
	   public List<Boom> booms=new ArrayList<>();
	  
// 创建敌方集合
	   public List<Planes> enemys=new ArrayList<>();
	//创建boss集合
	   public List<Planes> bosss=new ArrayList<>();
	  
	   //解决图片闪缩的问题
	     @Override
	    public void update(Graphics g) {
	    	Image backImg=createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	    	Graphics backg=backImg.getGraphics();
	    	Color color=backg.getColor();
	    	backg.setColor(Color.WHITE);
	    	backg.fillRect(0, 0, Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	    	backg.setColor(color);
	    	paint(backg);
	    	g.drawImage(backImg, 0,0,null);
	    }
	     
	     Planes planes=null;
    //生成客户端窗口的方法
	public void launchFrame()
	{
		SoundPlayer soundPlayer=new SoundPlayer("com/neuedu/sound/Jamesketed1.mp3");
		soundPlayer.start();
		
		
		
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);	
		this.setTitle("飞机大作战 ");
		//窗口显现出来
		this.setVisible(true);
		//禁止最大化
		this.setResizable(false);
		//剧中
		this.setLocationRelativeTo(null);
		//拿到这个图片,调用方法
		Image img = GetImageUtil.getImg("icom.png");
		this.setIconImage(img);
		//关闭窗口(添加一个监听器)
		this.addWindowListener(new WindowAdapter() {
			 @Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		//往我方容器添加自己
		  planes=new Planes(410, 700, "plane/hero1.png",this,true);
		planes2.add(planes);
		//添加鼠标监听
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("鼠标输出");
			}
		} );
		//添加键盘监听
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				planes.keyPressed(e) ;  
			}
			@Override
			public void keyReleased(KeyEvent e) {
				planes.keyReleased(e);
			}
		});
		//启动线程
		new paintThread().start();
		
		  //往敌方容器中添加敌方
		 //创建一个敌方1号出来
		   EnemyPlane enemy1=new EnemyPlane(0, -20, 1,this,false);
			//创建2号
		   EnemyPlane enemy2=new EnemyPlane(120, -80, 1,this,false); 
		   EnemyPlane enemy3=new EnemyPlane(420,-180, 1,this,false);
		   EnemyPlane enemy4=new EnemyPlane(120,-280, 1,this,false);
		   EnemyPlane enemy5=new EnemyPlane(0,-600, 1,this,false);
		  EnemyPlane enemy6=new EnemyPlane(120,-200, 1,this,false);
		   EnemyPlane enemy7=new EnemyPlane(500,-300, 1,this,false);
		   
		   EnemyPlane enemy8=new EnemyPlane(420,-700, 1,this,false);
		   EnemyPlane enemy9=new EnemyPlane(0,-350, 1,this,false);
		   EnemyPlane enemy10=new EnemyPlane(40,-470, 1,this,false);
		   EnemyPlane enemy12=new EnemyPlane(120,-700, 1,this,false);
		  EnemyPlane enemy11=new EnemyPlane(0,-350, 1,this,false);
		enemys.add(enemy1);enemys.add(enemy5);enemys.add(enemy9);
		enemys.add(enemy2);enemys.add(enemy10);enemys.add(enemy11);
		enemys.add(enemy3);enemys.add(enemy7);
		enemys.add(enemy4);enemys.add(enemy12);
		enemys.add(enemy6);enemys.add(enemy8);
		//添加boss
	//	Boos boss=new Boos(300,300,this);
		bosss.add(new Boos(300, -40, this,false));
	}
	@Override//重写父类的方法    //里面传了一个画笔 
	public void paint(Graphics g) {
//		//先画一条线出来
	g.drawLine(0, 400, 600, 400);
	setForeground(Color.YELLOW           );
	//写一个字符串的类型
//		g.drawString("飞机", 120, 120);
//		//画矩形(前面指定远点
//		g.drawRect(100, 100, 300, 350);
//		//传一个图片
	//backImg.draw(g);
		
	
		backImg.draw(g);
	
		//g.drawImage(GetImageUtil.getImg("timg.jpg"),0,0,900,800,null );
		for (int i = 0; i < planes2.size(); i++) {
			Planes planes3=planes2.get(i);
			planes3.draw(g);
			planes3.containItems(props);
		}
		
	//bullet.draw(g); 
	//增强for===只能做遍历====画出每个子弹
	for (int i=0;i<bullets.size();i++) {
		Bullet bullet=bullets.get(i);
		bullet.draw(g);
		bullet.hiteBoss(enemys);
		bullet.hiteBoss(planes2);
		bullet.hiteBoss(bosss);
	}
	g.drawString("当前子弹的数量:"+bullets.size(),15,60 );
	g.drawString("当前敌机的数量:"+enemys.size(), 15, 85);
	g.drawString("当前爆炸的数量:"+booms.size(), 15, 110);
	g.drawString("当前我方的数量:"+planes2.size(), 15, 135);
	g.drawString("当前道具的数量:"+props.size(), 15, 160);
	
	setForeground(Color.YELLOW);
	//循环画飞机
	for (int i = 0; i < enemys.size(); i++) {
		enemys.get(i).draw(g);
	}
	//循环爆炸
	for (int i = 0; i < booms.size(); i++) {
		if(booms.get(i).isLive()==true) {
			booms.get(i).draw(g);
		}
	
	}
//	prop.draw(g);
	//循环画道具
	for (int i = 0; i < props.size(); i++) {
		
			props.get(i).draw(g);
	}
	if(enemys.size()==0) {
		//循环boss集合
		for (int i = 0; i < bosss.size(); i++) {
			bosss.get(i).draw(g);
		}	
	}

	
	}
	//创建内部类
	class paintThread extends Thread
	{
		@Override
		public void run() {
			while(true)
			{
				repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
