package cn.youmay;

public class DataProvider {

	private double[] inValues = getRandomValues(24);
	private double[] outValues = getRandomValues(24);

	public DataTotal getTotal() {
		DataTotal total = new DataTotal();
		total.setInAvg(avg(inValues));
		total.setInMax(max(inValues));
		total.setOutAvg(avg(outValues));
		total.setOutMax(max(outValues));
		return total;
	}

	public double[] getInValues() {
		return inValues;
	}

	public double[] getOutValues() {
		return outValues;
	}

	private double max(double[] values) {
		double max = Double.MIN_VALUE;
		for (int i = 0; i < values.length; i++) {
			max = Math.max(max, values[i]);
		}
		return max;
	}

	private double min(double[] values) {
		double min = Double.MIN_VALUE;
		for (int i = 0; i < values.length; i++) {
			min = Math.min(min, values[i]);
		}
		return min;
	}

	private double avg(double[] values) {
		double sum = 0;
		int count = values.length;
		for (int i = 0; i < count; i++) {
			sum += values[i];
		}
		return sum / count;
	}

	private double[] getRandomValues(int length) {
		double[] doubleValues = new double[length];
		for (int i = 0; i < length; i++) {
			doubleValues[i] = Math.random() * 3;
		}
		return doubleValues;
	}
}
