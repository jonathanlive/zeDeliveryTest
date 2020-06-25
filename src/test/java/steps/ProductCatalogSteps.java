package steps;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.api.Item;
import model.api.ProductsResponse;
import org.junit.Assert;
import support.util.ExtentReportUtil;

import java.util.Map;

public class ProductCatalogSteps {

    private RequestSpecification requestSpecification;
    private Response response;
    private String email = "zedeliverytest2@outlook.com";
    private String password = "ZeTest123";
    private String token;
    private String refreshToken;

    @Given("the user has openned the ZeDeliveri's app")
    public void theUserHasOpennedTheZeDeliveriSApp() {
        requestSpecification = RestAssured.given().baseUri("https://api.ze.delivery")
                .contentType("application/json")
                .header("Connection", " keep-alive")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
    }

    @When("user logs using correct credencials")
    public void userLogsUsingCorrectCredencials() {
        requestSpecification.body("{\"operationName\":\"login\",\"variables\":{\"email\":\"" + email + "\",\"password\":\"" +
                password + "\",\"source\":\"USER_PASSWORD\"},\"query\":\"mutation login($source: Source!, $email: String," +
                " $password: String, $facebookAccessToken: String, $pushToken: String) {\\n  login(source: $source, email: $email," +
                " password: $password, facebookAccessToken: $facebookAccessToken, pushToken: $pushToken) " +
                "{\\n    token\\n    refreshToken\\n    user {\\n      email\\n      phoneNumber\\n      givenName\\n " +
                "     familyName\\n      birthdate\\n      document\\n      phoneNumberConfirmed\\n      memberGetMemberCode\\n   " +
                "   totalOrdersCount\\n      dashButtonDeviceSerialNumber\\n    }\\n    errors {\\n      category\\n      target\\n   " +
                "   key\\n      args\\n      message\\n    }\\n  }\\n}\\n\"}");
        response = requestSpecification.post("/public-api");
        Assert.assertEquals(200,response.getStatusCode());
        token = response.jsonPath().get("token");
        Map<String,String> map = (Map<String, String>) response.jsonPath().getMap("data").get("login");
        token = map.get("token");
        refreshToken = map.get("refreshToken");
    }

    @Then("the beer (.*) will be displayed on homepage")
    public void theBeerWillBeDisplayedOnHomepage(String beer) {
        requestSpecification.body("{\"operationName\":\"loadShowCase\",\"variables\":{},\"query\":\"query loadShowCase($filter: ShowCaseFilter) {\\n  loadShowCase(filter: $filter) {\\n    messages {\\n      category\\n      target\\n      key\\n      message\\n    }\\n    showCase {\\n      images\\n      shelves {\\n        id\\n        type\\n        displayName\\n        items {\\n          id\\n          type\\n          images\\n          displayName\\n          applicableDiscount {\\n            presentedDiscountValue\\n            discountType\\n            finalValue\\n          }\\n          category {\\n            id\\n            displayName\\n          }\\n          brand {\\n            id\\n            displayName\\n          }\\n          price {\\n            min\\n            max\\n          }\\n        }\\n      }\\n    }\\n  }\\n}\\n\"}");
        requestSpecification.header("x-refreshtoken",refreshToken);
        requestSpecification.header("x-accesstoken",token);
        requestSpecification.header("Referer","https://www.ze.delivery/produtos");
        response = requestSpecification.post("/public-api");
        Assert.assertEquals(200,response.getStatusCode());
        Gson gson = new Gson();
        ProductsResponse productsResponse = gson.fromJson(response.getBody().asString(),ProductsResponse.class);
        Item beerDetails = productsResponse.getData().getLoadShowCase().getShowCase().getShelve("Cervejas").getItem(beer);
        if(beerDetails == null)
            Assert.fail("Beer: " + beer + " or category: Cervejas not found");
        Assert.assertEquals(beer, beerDetails.getDisplayName());
    }
}
