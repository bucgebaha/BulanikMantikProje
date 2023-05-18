package paket;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Turbin yuksekligi(m): ");
		double yukseklik = in.nextDouble();
		
		System.out.print("Turbin kanat boyu(m): ");
		double kanatBoyu = in.nextDouble();

		System.out.print("Ruzgar hizi(m/s): ");
		double ruzgarHizi = in.nextDouble();
		
		try {
			Turbin turbin = new Turbin(ruzgarHizi, kanatBoyu, yukseklik);
			System.out.println(turbin);
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
	}
}