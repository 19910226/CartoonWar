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
* @Description: ��Ϸ�ͻ���
* @author Zpf
* @date 2019��8��18�� ����12:33:39
* @version 0.1
*/


public class GameClient extends Frame {
	//�����ɻ�
	   //  Planes planes=new Planes(150, 200, "plane/hero1.png",this,true);
	//�����ӵ�
//	 Bullet bullet=new Bullet(200,300,"zd.png");
   //�����ҷ�����
	     public List<Planes> planes2=new ArrayList<>();
	     //�����ӵ��ļ���
	   public   List<Bullet> bullets=new ArrayList<>();
	 //����һ������
	 //  Prop prop=new Prop(400, 300,"prop/dao.png");
	   //��������ͼ
	   BackGround backImg=new BackGround(0, -1,"beij.png" );
	 //�������߼���
	   public List<Prop> props=new ArrayList<>();
	   //����һ����ը����
	   public List<Boom> booms=new ArrayList<>();
	  
// �����з�����
	   public List<Planes> enemys=new ArrayList<>();
	//����boss����
	   public List<Planes> bosss=new ArrayList<>();
	  
	   //���ͼƬ����������
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
    //���ɿͻ��˴��ڵķ���
	public void launchFrame()
	{
		SoundPlayer soundPlayer=new SoundPlayer("com/neuedu/sound/Jamesketed1.mp3");
		soundPlayer.start();
		
		
		
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);	
		this.setTitle("�ɻ�����ս ");
		//�������ֳ���
		this.setVisible(true);
		//��ֹ���
		this.setResizable(false);
		//����
		this.setLocationRelativeTo(null);
		//�õ����ͼƬ,���÷���
		Image img = GetImageUtil.getImg("icom.png");
		this.setIconImage(img);
		//�رմ���(���һ��������)
		this.addWindowListener(new WindowAdapter() {
			 @Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		//���ҷ���������Լ�
		  planes=new Planes(410, 700, "plane/hero1.png",this,true);
		planes2.add(planes);
		//���������
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("������");
			}
		} );
		//��Ӽ��̼���
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
		//�����߳�
		new paintThread().start();
		
		  //���з���������ӵз�
		 //����һ���з�1�ų���
		   EnemyPlane enemy1=new EnemyPlane(0, -20, 1,this,false);
			//����2��
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
		//���boss
	//	Boos boss=new Boos(300,300,this);
		bosss.add(new Boos(300, -40, this,false));
	}
	@Override//��д����ķ���    //���洫��һ������ 
	public void paint(Graphics g) {
//		//�Ȼ�һ���߳���
	g.drawLine(0, 400, 600, 400);
	setForeground(Color.YELLOW           );
	//дһ���ַ���������
//		g.drawString("�ɻ�", 120, 120);
//		//������(ǰ��ָ��Զ��
//		g.drawRect(100, 100, 300, 350);
//		//��һ��ͼƬ
	//backImg.draw(g);
		
	
		backImg.draw(g);
	
		//g.drawImage(GetImageUtil.getImg("timg.jpg"),0,0,900,800,null );
		for (int i = 0; i < planes2.size(); i++) {
			Planes planes3=planes2.get(i);
			planes3.draw(g);
			planes3.containItems(props);
		}
		
	//bullet.draw(g); 
	//��ǿfor===ֻ��������====����ÿ���ӵ�
	for (int i=0;i<bullets.size();i++) {
		Bullet bullet=bullets.get(i);
		bullet.draw(g);
		bullet.hiteBoss(enemys);
		bullet.hiteBoss(planes2);
		bullet.hiteBoss(bosss);
	}
	g.drawString("��ǰ�ӵ�������:"+bullets.size(),15,60 );
	g.drawString("��ǰ�л�������:"+enemys.size(), 15, 85);
	g.drawString("��ǰ��ը������:"+booms.size(), 15, 110);
	g.drawString("��ǰ�ҷ�������:"+planes2.size(), 15, 135);
	g.drawString("��ǰ���ߵ�����:"+props.size(), 15, 160);
	
	setForeground(Color.YELLOW);
	//ѭ�����ɻ�
	for (int i = 0; i < enemys.size(); i++) {
		enemys.get(i).draw(g);
	}
	//ѭ����ը
	for (int i = 0; i < booms.size(); i++) {
		if(booms.get(i).isLive()==true) {
			booms.get(i).draw(g);
		}
	
	}
//	prop.draw(g);
	//ѭ��������
	for (int i = 0; i < props.size(); i++) {
		
			props.get(i).draw(g);
	}
	if(enemys.size()==0) {
		//ѭ��boss����
		for (int i = 0; i < bosss.size(); i++) {
			bosss.get(i).draw(g);
		}	
	}

	
	}
	//�����ڲ���
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
