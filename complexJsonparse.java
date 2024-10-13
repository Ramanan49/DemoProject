import javax.swing.border.TitledBorder;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class complexJsonparse {

	public static void main(String[] args) {
	

JsonPath js=new JsonPath(Payload.coursePrice());
int count=js.getInt("courses.size()");
System.out.println(count);
		
		
/*	int totalAmount=js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
		
	
	String firstCoursetitle=js.get("courses[0].title");
	System.out.println(firstCoursetitle); */
	
	for(int i=0;i<count;i++) {
	String fullCoursestitle=	js.get("courses["+i+"].title");
	System.out.println(fullCoursestitle);
	
	int totalPrice=js.get("courses["+i+"].price");
	System.out.println(totalPrice);
	
	int totalCopies =js.get("courses["+i+"].copies");
	System.out.println(totalCopies);
			
	}
			
	//Print no of copies sold by RPA Course
	System.out.println("Print no of copies sold by RPA Course");	
	for (int i=0;i<count;i++) {
		String fullCoursestitle=js.get("courses["+i+"].title");
		int totalCopies =js.get("courses["+i+"].copies");
		if (fullCoursestitle.equalsIgnoreCase("RPA")) {
		System.out.println(totalCopies);
		break;
		}
	}
			
	}

}
