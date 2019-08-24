package com.neuedu.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description:��ȡͼƬ�Ĺ�����
* @author Zpf
* @date 2019��8��18�� ����1:05:57
*
*/
public class GetImageUtil {
   //��ȡͼƬ�ķ���
	public static Image getImg(String imgName) {
		//����(���������ͣ�  //���������
		URL resource = GetImageUtil.class.getClassLoader().getResource("com/neuedu/img/"+imgName); 
		BufferedImage bufferedImage=null;
		try {
			bufferedImage  = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return bufferedImage;
	}
	
}
