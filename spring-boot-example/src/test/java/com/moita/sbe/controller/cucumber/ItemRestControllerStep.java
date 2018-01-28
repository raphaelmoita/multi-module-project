package com.moita.sbe.controller.cucumber;

import static org.assertj.core.api.Assertions.assertThat;

import org.moita.sbe.SpringbootApplication;
import org.moita.sbe.model.Item;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.moita.sbe.controller.util.ItemRestControllerUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { SpringbootApplication.class })
public class ItemRestControllerStep extends ItemRestControllerUtil {
		
	private Item item;
	private int statusCode;
		
	@Given("^the Item with id=(\\d+) and description=(.*)$")
	public void createItem(int id, String description) throws Throwable{
		this.item = new Item(id, description);
	}	
	
	@When("^the client sends the item to (.*) endPoint$")
	public void sendRequestTo(String endPoint) throws Throwable{
		this.statusCode = post(this.item, endPoint).getStatusCodeValue();
	}
	 
	@Then("^the client receives status code of (\\d+)$")
	public void receiveResponse(int statusCode) throws Throwable {
		assertThat(this.statusCode).isEqualTo(statusCode);
	}
	
	
}
