package convertisseurmoney;

import contratconvertisseur.Convertisseur;

public class ConvertisseurDoEuImpl implements Convertisseur{

	@Override
	public String fromUnit() {
		return "Dollars";
	}

	@Override
	public String toUnit() {
		return "Euros";
	}

	@Override
	public double u12u2(double f) {
		return f*0.86;
	}
}
