package ingame;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cookie {
	private Image image; // ��Ű �̹���
	
	private int x = 130;
	private int y = 540;
	private int width = 120;
	private int height = 80;
	
	public Cookie(Image image){ // �̹����� �־�����
		this.image = image;
	}
}
