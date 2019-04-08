package JavaScript.Test;
import java.io.IOException;
import org.testng.annotations.Test;

public class TestTatoc extends TatocPages{
	
	
	//First Page Test Case
	@Test(priority = 0)
	public void Grid() throws IOException {
		
		grid.GridErrorTest();  //When Red color box is clicked
		grid.GridPassTest();   //When Green color box is clicked	
		
	}
	
	@Test(priority = 1) 
	public void Frame() throws IOException {
		
		
		frame.BoxCheck();          //verify box 1 and box 2 are displayed
		frame.FrameErrorTest();     
		frame.FrameColor();         
	
	}
	
	@Test(priority = 3)
	public void DragDrop() throws IOException {
		
		
		drag.DragButton();
		drag.DragErrorTest();
		drag.DragTest();
		
	}
	
	@Test(priority = 4)
	public void WindowPopup() throws IOException {
		
		
		window.WindowLinks();
		window.WindowButton();
		window.WindowErrorTest();
		window.WindowNameTest();
		window.WindowTest();
		
	}
	
	@Test(priority = 5)
	public void CookieHandling() throws IOException {
		
		cookie.CookieGenerateErrorTest();
		cookie.CookieErrorTest();
		cookie.CookieGenerateTest();
		
	}
	
	
	
	
	
}
