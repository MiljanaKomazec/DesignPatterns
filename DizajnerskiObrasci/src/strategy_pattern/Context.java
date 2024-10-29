package strategy_pattern;

public class Context {

	private Strategy strategy;
	
	public void save(String filePath) {
		this.strategy.save(filePath);
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
}
