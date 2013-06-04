package example.acceptance.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;

import example.sample.domain.SampleRepository;

@Steps
public class ExampleSteps {

	@Autowired
	private SampleRepository sampleRepository;

	@Given("a stock of symbol $symbol and a threshold of $threshold")
	public void aStock(String symbol, double threshold) {
		sampleRepository.findOne(-1L);
	}

	@When("the stock is traded at $price")
	public void theStockIsTradedAt(double price) {
	}

	@Then("the alert status should be $status")
	public void theAlertStatusShouldBe(String status) {
	}

}
