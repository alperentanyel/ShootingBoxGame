


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game {
	ArrayList<MyThread> list =new ArrayList<>(); 
	// 0 aircraft
	boolean gameOn=true;
	static ArrayList<Integer> xPositions=new ArrayList<>();
	static ArrayList<Integer> yPositions=new ArrayList<>();
	ShootingThread shooting_thread= new ShootingThread();
	DeathThread death_thread= new DeathThread();
	static ArrayList<Shooting> positive_shooting= new ArrayList<>();
	static ArrayList<Shooting> negative_shooting= new ArrayList<>();
	boolean w=false,a=false,s=false,d=false;
	
	JPanel panel= new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				if(!list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						if(list.get(i) instanceof AirCraft &&list.get(i).isExists)
							g.setColor(Color.red);
						if(list.get(i) instanceof Friend &&list.get(i).isExists)
							g.setColor(Color.green);
						if(list.get(i) instanceof Enemy &&list.get(i).isExists)
							g.setColor(Color.black);
						if(list.get(i).isExists)
						g.fillRect(list.get(i).x, list.get(i).y, 10, 10);
					}
				}
				if(!positive_shooting.isEmpty()) {
					for (int i = 0; i < positive_shooting.size(); i++) {
						if(positive_shooting.get(i).type==2 )
							g.setColor(Color.orange);
						if(positive_shooting.get(i).type==0  )
							g.setColor(Color.magenta);
						if(positive_shooting.get(i).type==1  )
							g.setColor(Color.blue);
						if(positive_shooting.get(i).isExists)
						g.fillRect(positive_shooting.get(i).x, positive_shooting.get(i).y, 5, 5);
					}
				}
				if(!negative_shooting.isEmpty()) {
					for (int i = 0; i < negative_shooting.size(); i++) {
						if(negative_shooting.get(i).type==2 )
							g.setColor(Color.orange);
						if(negative_shooting.get(i).type==0  )
							g.setColor(Color.magenta);
						if(negative_shooting.get(i).type==1  )
							g.setColor(Color.blue);
						if(negative_shooting.get(i).isExists)
						g.fillRect(negative_shooting.get(i).x, negative_shooting.get(i).y, 5, 5);
					}
				}
				
				
			}
		};
		Threadder t= new Threadder();
		ExitThread exit= new ExitThread();
		JFrame frame = new JFrame();
		KeyThread keythread=new KeyThread();
		public static void main(String [] args){
			
			
			Game game= new Game();
			
		}
	public Game() {
		AirCraft airc= new AirCraft();
		for (int i = 0; i < 15; i++) {
			Enemy enemy= new Enemy();
			Friend friend= new Friend();
			enemy.start();
			friend.start();
			
		}
		gameOn=true;
		t.start();
		keythread.start();
		exit.start();
		death_thread.start();
		shooting_thread.start();
		// TODO Auto-generated constructor stub
		frame.setLocation(500, 100);
		frame.setResizable(false);
		panel.setSize(500,500);
		
		panel.setBackground(Color.white);
		frame.setSize(516, 539);
		frame.setBackground(Color.white);
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(list.get(0).isExists) {
					if (e.getKeyChar()=='w' || e.getKeyChar()=='W')
					w=true;
						
						
						
						
					
					if (e.getKeyChar()=='a' || e.getKeyChar()=='A') {
						a=true;
						
						
						
					}
					if (e.getKeyChar()=='s' || e.getKeyChar()=='S') {
						s=true;
						
						
					}
					if (e.getKeyChar()=='d' || e.getKeyChar()=='D') {
						d=true;
						
						
					}
				}
				
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				if(list.get(0).isExists) {
					if (e.getKeyChar()=='w' || e.getKeyChar()=='W')
					w=false;
						
						
						
						
					
					if (e.getKeyChar()=='a' || e.getKeyChar()=='A') {
						a=false;
						
						
						
					}
					if (e.getKeyChar()=='s' || e.getKeyChar()=='S') {
						s=false;
						
						
					}
					if (e.getKeyChar()=='d' || e.getKeyChar()=='D') {
						d=false;
						
						
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		panel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(list.get(0).isExists) {
					negative_shooting.add(new Shooting(2, list.get(0).x-10, list.get(0).y));
				positive_shooting.add(new Shooting(2, list.get(0).x+10, list.get(0).y));
				}
				
			}
		});
		
		
		

	}
	class KeyThread extends Thread{
		public KeyThread() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(gameOn) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(s)
					if(list.get(0).y<490)
						for (int i = 0; i < 10; i++) {
							try {
								Thread.sleep(1);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							list.get(0).y+=1;
							panel.repaint();
						}
					if(a)
						if(list.get(0).x>0)
							for (int i = 0; i < 10; i++) {
								try {
									Thread.sleep(1);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								list.get(0).x-=1;
								panel.repaint();
							}
						if(w)
					if(list.get(0).y>0)
									for (int i = 0; i < 10; i++) {
										try {
											Thread.sleep(1);
										} catch (InterruptedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										list.get(0).y-=1;
										panel.repaint();
									}
					
				if(d)
				if(list.get(0).x<490)
					for (int i = 0; i < 10; i++) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						list.get(0).x+=1;
						panel.repaint(); 
					}
			}
		}
	}
	class ExitThread extends Thread{
		int enemynumber=0;
		JOptionPane f= new JOptionPane();
		
		public ExitThread() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			f.setSize(150, 70);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (gameOn) {
				enemynumber=0;
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).isExists)
					{
						if(list.get(i) instanceof Enemy)
							enemynumber++;
						
					}
				}
				if(enemynumber==0)
				{panel.repaint();
				gameOn=false;
					
					f.showMessageDialog(frame, "Oyunu kazandınız");
					frame.dispose();
					
				}
					
				
			}
				
			if(!gameOn)
			{
				if(!list.get(0).isExists) {
					f.showMessageDialog(frame, "Oyunu kaybettiniz");
					frame.dispose();
					
				}
					
				
				
			}
		}
	}
	class DeathThread extends Thread{
		public DeathThread() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(gameOn) {
				
				System.out.print("");
				int limit=list.size();
				for (int i = 0; i < limit; i++) {
					for (int j = 0; j < limit; j++) {
						if(i!=j && list.get(i).isExists && list.get(j).isExists)
						if(list.get(i).x==list.get(j).x && list.get(i).y==list.get(j).y ) {
							
								
							if(i==0 && list.get(j) instanceof Enemy ) {
								gameOn=false;
								list.get(i).isExists=false;
								list.get(j).isExists=false;
								panel.repaint();
								
								
								return;
								}if(j==0 && list.get(i) instanceof Enemy) {
								gameOn=false;
								list.get(i).isExists=false;
								list.get(j).isExists=false;
								panel.repaint();
								
								return;
							}
								if(list.get(i) instanceof Friend && list.get(j) instanceof Enemy)
								{
									list.get(i).isExists=false;
									list.get(j).isExists=false;
								}
							
							panel.repaint();
						}
					}
				}
				
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < positive_shooting.size(); j++) {
						if(  list.get(i).isExists && positive_shooting.get(j).isExists && list.get(i).x>= positive_shooting.get(j).x &&list.get(i).x< positive_shooting.get(j).x+10 && list.get(i).y >= positive_shooting.get(j).y &&list.get(i).y < positive_shooting.get(j).y+10) {
							positive_shooting.get(j).isExists=false;
							if(list.get(i) instanceof AirCraft) 
								if(positive_shooting.get(j).type==1)
								{
									list.get(i).isExists=false;
									gameOn=false;
									panel.repaint();
								}
							if(list.get(i) instanceof Friend)
								if(positive_shooting.get(j).type==1)
								{
									list.get(i).isExists=false;
									
									panel.repaint();
								}
							if(list.get(i) instanceof Enemy)
								if(positive_shooting.get(j).type==0 || positive_shooting.get(j).type==2 )
								{
									list.get(i).isExists=false;
									
									panel.repaint();
								}	
								
							
						}
							
					}
					for (int j = 0; j < negative_shooting.size(); j++) {
						if(  list.get(i).isExists && negative_shooting.get(j).isExists && list.get(i).x>= negative_shooting.get(j).x &&list.get(i).x< negative_shooting.get(j).x+10 && list.get(i).y >= negative_shooting.get(j).y &&list.get(i).y < negative_shooting.get(j).y+10) {
							negative_shooting.get(j).isExists=false;
							if(list.get(i) instanceof AirCraft) 
								if(negative_shooting.get(j).type==1)
								{
									list.get(i).isExists=false;
									gameOn=false;
									panel.repaint();
								}
							if(list.get(i) instanceof Friend)
								if(negative_shooting.get(j).type==1)
								{
									list.get(i).isExists=false;
									
									panel.repaint();
								}
							if(list.get(i) instanceof Enemy)
								if(negative_shooting.get(j).type==0 || negative_shooting.get(j).type==2 )
								{
									list.get(i).isExists=false;
									
									panel.repaint();
								}	
								
							
						}
							
					}
				}
			}
		}
	}
	class  ShootingThread  extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(gameOn) {
				try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < positive_shooting.size(); i++) {
				if(positive_shooting.get(i).x<500 && positive_shooting.get(i).isExists)
				{
					positive_shooting.get(i).x+=1;
					if(positive_shooting.get(i).x>=500)
						positive_shooting.get(i).isExists=false;
				}
				/*else {
					positive_shooting.remove(i);
					i--;
				}*/
				
			}
			for (int i = 0; i < negative_shooting.size(); i++) {
				if(negative_shooting.get(i).x>=0  && negative_shooting.get(i).isExists)
				{
					negative_shooting.get(i).x-=1;
					if(negative_shooting.get(i).x<0)
						negative_shooting.get(i).isExists=false;
				}
				/*else {
					negative_shooting.remove(i);
					i--;
				}*/
				
			}panel.repaint();
			}
			
			
		}
	}
	
	class MyThread extends Thread{
		int x,y;
		
		boolean isExists=true;
		static int UP=0;
		static int DOWN=1;
		static int RIGHT=2;
		static int LEFT=3;
		static int FRIEND=0;
		static int ENEMY=1;
		public void move(int format,int index) {
			
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(format==UP) {
				if(list.get(index).y>0)
					for (int i = 0; i < 10; i++) {
						list.get(index).y-=1;
						panel.repaint();
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				else
					for (int i = 0; i < 10; i++) {
						list.get(index).y+=1;
						panel.repaint();
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				
			}if(format==DOWN) {
				if(list.get(index).y<490)
					for (int i = 0; i < 10; i++) {
						list.get(index).y+=1;
						panel.repaint();
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					else
						for (int i = 0; i < 10; i++) {
							list.get(index).y-=1;
							panel.repaint();
							try {
								Thread.sleep(4);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
			}if(format==RIGHT) {
				if(list.get(index).x<490)
					for (int i = 0; i < 10; i++) {
						list.get(index).x+=1;
						panel.repaint();
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					else
						for (int i = 0; i < 10; i++) {
							list.get(index).x-=1;
							panel.repaint();
							try {
								Thread.sleep(4);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
			}if(format==LEFT) {
				if(list.get(index).x>0)
					for (int i = 0; i < 10; i++) {
						list.get(index).x-=1;
						panel.repaint();
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else
						for (int i = 0; i < 10; i++) {
							list.get(index).x+=1;
							panel.repaint();
							try {
								Thread.sleep(4);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
			}
			panel.repaint();
			
		}
		
	}
	class Threadder extends Thread{
		public Threadder() {
			// TODO Auto-generated constructor stub
			
		}
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			while(gameOn) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i ) instanceof Enemy &&list.get(i).isExists)
						shoot(MyThread.ENEMY, list.get(i ).x, list.get(i ).y);
					if(list.get(i ) instanceof Friend &&list.get(i).isExists)
						shoot(MyThread.FRIEND, list.get(i ).x, list.get(i ).y);
				}	
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i ) instanceof Enemy &&list.get(i).isExists)
						shoot(MyThread.ENEMY, list.get(i ).x, list.get(i ).y);
					if(list.get(i ) instanceof Friend &&list.get(i).isExists)
						shoot(MyThread.FRIEND, list.get(i ).x, list.get(i ).y);
				}
			}
			
		}
		
			
		public void shoot(int type,int x ,int y) {
			// TODO Auto-generated method stub
			
			positive_shooting.add(new Shooting(type, x+10, y));
			negative_shooting.add(new Shooting(type, x-10, y));
			panel.repaint();
		}
	}
	class Shooting{
		int x,y;
		int width=5;
		int height=5;
		int type;
		boolean isExists;
		public Shooting(int type,int x ,int y) {
			// TODO Auto-generated constructor stub
			this.type=type;
			this.x=x;
			this.y=y;
			isExists=true;
			
		}
		// 0 ise friend 1 ise enemy
	}
	class Enemy extends MyThread{
		public Enemy() {
			// TODO Auto-generated constructor stub
			
			
				Random random= new Random();
				x=random.nextInt(50)*10;
				y=random.nextInt(50)*10;
			
				if(!xPositions.isEmpty() && !yPositions.isEmpty())
				{
					for (int i = 0; i < xPositions.size(); i++) {
						while(xPositions.get(i)==x && yPositions.get(i)==y && x!=250 && y!=250) {
							Random random1= new Random();
							x=random1.nextInt(50)*10;
							y=random1.nextInt(50)*10;
							i=0;
						}
					}
					
				}
				xPositions.add(x);
				yPositions.add(y);
				
			list.add(this);
		}
		public void run() {
			// TODO Auto-generated method stub
			Random random= new Random();
			while(isExists && gameOn) {
				move(random.nextInt(4),list.indexOf(this));
				
			
			}
			
		}
	}
	class Friend extends MyThread{
		public Friend() {
			// TODO Auto-generated constructor stub
			
			Random random= new Random();
			x=random.nextInt(50)*10;
			y=random.nextInt(50)*10;
			
			if(!xPositions.isEmpty() && !yPositions.isEmpty())
			{
				for (int i = 0; i < xPositions.size(); i++) {
					while(xPositions.get(i)==x && yPositions.get(i)==y&&x!=250 && y!=250) {
						Random random1= new Random();
						x=random1.nextInt(50)*10;
						y=random1.nextInt(50)*10;
						i=0;
					}
				}
				
			}
			xPositions.add(x);
			yPositions.add(y);
			
			list.add(this);
		}		
		public void run() {
			// TODO Auto-generated method stub
			Random random= new Random();
			
			while(isExists && gameOn)
			{
				move(random.nextInt(4),list.indexOf(this));
				
			}
			
		}
	}
	class AirCraft extends MyThread{

		public AirCraft() {
			// TODO Auto-generated constructor stub
			
			x=250;
			y=250;
			xPositions.add(x);
			yPositions.add(y);
			
			list.add(0, this);
			
		}
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
