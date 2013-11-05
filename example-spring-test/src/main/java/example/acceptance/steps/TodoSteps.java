package example.acceptance.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;

import example.domain.todo.domain.TodoRepository;

@Steps
public class TodoSteps {

	@Autowired
	private TodoRepository todoRepository;

	@Given("a stock of symbol $symbol and a threshold of $threshold")
	public void aStock(String symbol, double threshold) {
		todoRepository.load(-1L);
	}

	@When("the stock is traded at $price")
	public void theStockIsTradedAt(double price) {
	}

	@Then("the alert status should be $status")
	public void theAlertStatusShouldBe(String status) {
	}

}
