package example.acceptance.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;

import example.domain.example.ExampleRepository;

@Steps
public class ExampleSteps {

	@Autowired
	private ExampleRepository exampleRepository;

	@Given("a stock of symbol $symbol and a threshold of $threshold")
	public void aStock(String symbol, double threshold) {
	}

	@When("the stock is traded at $price")
	public void theStockIsTradedAt(double price) {
	}

	@Then("the alert status should be $status")
	public void theAlertStatusShouldBe(String status) {
	}

}
