import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.Item;
import app.model.Products;

public class ItemTest {

	Logger logger = LoggerFactory.getLogger(ItemTest.class);

	@Test
	public void checkItemFormOpen() throws IOException {
		byte[] body = Files.readAllBytes(new File("./webapp/productForm.html").toPath());
		logger.debug(body.length+"");
	}

	@Test
	public void getProductsTest(){
		Products 과자리스트 = new Products();

		과자리스트.getItems().stream().forEach(item -> {
			logger.info(item.getName());
		});

	}

	@Test
	public void addItemTest(){

		Products 과자리스트 = new Products();
		Item testItem = new Item("P999", "햇반",1700);
		/*TODO 테스트를 어떻게 해야하는지...
		 * 리스트 추가된 후 사이즈 증가로 체크?
		 * 구조가 잘못된건가?
		 *
		 */

		과자리스트.addItem("P999", "햇반",1700);

		과자리스트.getItems().stream().forEach(item -> {
			logger.info(item.getName());
		});



	}
}
