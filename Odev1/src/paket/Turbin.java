package paket;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.io.File;
import java.net.URISyntaxException;

public class Turbin {
	private FIS fis;
	private double ruzgarHizi;
	private double kanatBoyu;
	private double yukseklik;
	
	public Turbin(double ruzgarHizi, double kanatBoyu, double yukseklik) throws URISyntaxException {
		this.kanatBoyu = kanatBoyu;
		this.ruzgarHizi = ruzgarHizi;
		this.yukseklik = yukseklik;
		
		File dosya = new File(getClass().getResource("model.fcl").toURI());
		fis = fis.load(dosya.getPath());
		fis.setVariable("yukseklik", yukseklik);
		fis.setVariable("ruzgarHizi", ruzgarHizi);
		fis.setVariable("kanatBoyu", kanatBoyu);
		fis.evaluate();
	}
	
	public Turbin() throws URISyntaxException {
		File dosya = new File(getClass().getResource("model.fcl").toURI());
		fis = fis.load(dosya.getPath());
	}
	
	public FIS getModel() {
		return fis;
	}
	
	@Override
	public String toString() {
		String cikti = "Turbinin kanat boyu: " + kanatBoyu + "\nTurbinin yuksekligi: " + yukseklik + "\nRuzgar hizi: " + ruzgarHizi + "\nURETILEN ENERJI: " + fis.getVariable("uretilenEnerji").getValue() + " kW";
		
		for( Rule r : fis.getFunctionBlock("model").getFuzzyRuleBlock("kuralblok1").getRules() ) {
			if(r.getDegreeOfSupport() > 0) cikti += "\n" + r;
		}
		
		Variable uretilenEnerji = fis.getFunctionBlock("model").getFuzzyRuleBlock("kuralblok1").getVariable("uretilenEnerji");
		JFuzzyChart.get().chart(uretilenEnerji, uretilenEnerji.getDefuzzifier(), true);
		return cikti;
	}
}