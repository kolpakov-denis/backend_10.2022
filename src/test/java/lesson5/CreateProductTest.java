package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;


public class CreateProductTest extends BaseTest {

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    @Order(1)
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        setNewId(response.body().getId().toString());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
    @Test
    @Order(2)
    void requestCreadtedProductWithIdTest() throws IOException {
        Response<Product> response = productService.getProductById(getNewId()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }





}
