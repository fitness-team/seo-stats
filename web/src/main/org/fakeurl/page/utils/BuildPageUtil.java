package main.org.fakeurl.page.utils;

import main.org.fakeurl.db.PoolDBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuildPageUtil {

	/**
	 * Create html code for navbar
	 * @param authorize
	 * @return
	 */
	public static String createNavbar(boolean authorize){
		
		StringBuilder navbar = new StringBuilder();
		
		navbar.append("<div class='navbar navbar-default navbar-fixed-top'>"+
			"<div class='container'>"+
		  		"<div class='navbar-header'>"+
		    		"<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>"+
						"<span class='icon-bar'></span>"+
						"<span class='icon-bar'></span>"+
						"<span class='icon-bar'></span>"+
		    		"</button>"+
		    		"<a class='navbar-brand' href='index.jsp'>Seo Positions</a>"+
		  		"</div>"+
		  		"<div class='collapse navbar-collapse'>"+
//		    		"<ul class='nav navbar-nav pull-right'>"+
//		      			"<li><a href='login.jsp'>Ввійти</a></li>"+
//		      			"<li><a href='#'><img src='assets/pictures/vkontakte_512x512.png' alt='vk'/></a></li>"+
//		      			"<li><a href='#'><img src='assets/pictures/facebook_512x512.png' alt='fb'/></a></li>"+
//		      			"<li><a href='#'><img src='assets/pictures/twitter_512x512.png' alt='google'/></a></li>"+
//		      			"<li><a href='#'>Зареєструватись</a></li>"+
//		    		"</ul>"+
		  		"</div><!--/.nav-collapse -->"+
			"</div>"+
		"</div><!-- navbar -->"+
		
		"<div class='subnav subnav-fixed'>"+
    		"<ul class='nav nav-pills'>"+
      			"<li><a href='stats.jsp'>Просмотреть статистику</a></li>"+
              	"<li><a href='project.jsp'>Добавить проект</a></li>"+
    		"</ul>"+
  		"</div>");
		return navbar.toString();
	}
	
	private static String createList(String type) throws SQLException{
		StringBuilder list = new StringBuilder();
		
		Connection conn = null;
		try {
			conn = PoolDBConnector.getInstance().getConnection();
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT text AS li FROM excercise_features EF "
					+ " JOIN texts T ON EF.feature_id = T.id");
			
			while(rs.next()){
				String li = rs.getString("li");
				list.append("<li><a href='#'>")
					.append(li).append(" <span class='badge'>0</span></a></li>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		}
		
		return list.toString();
	}
	
	public static String createExerciseCategorySidebar(){
		StringBuilder sidebar = new StringBuilder();
		
		String listMusculsGroup = "";
		String listEquipmentGroup = "";
		String listDifficultyGroup = "";
		String listTypeGroup = "";

		
		sidebar.append("<div class='sidebar-background right'>")
					.append("<div class='primary-sidebar-background'></div>")
				.append("</div>")
				.append("<div class='primary-sidebar right'>")
					.append("<ul class='nav nav-list'>")
					.append("<li class=''>")
						.append("<h3><span>Група мязів</span></h3>")
						.append("<ul class='nav nav-list'>")
							.append(listMusculsGroup)
						.append("</ul>")
					.append("</li>")
					.append("<li class=''>")
						.append("<h3><span>Тренажер</span></h3>")
						.append("<ul class='nav nav-list'>")
							.append(listEquipmentGroup)
						.append("</ul>")
					.append("</li>")
					.append("<li class=''>")
						.append("<h3><span>Складність</span></h3>")
						.append("<ul class='nav nav-list'>")
							.append(listDifficultyGroup)
						.append("</ul>")
					.append("</li>")
					.append("<li class=''>")
						.append("<h3><span>Тип</span></h3>")
						.append("<ul class='nav nav-list'>")
							.append(listTypeGroup)
						.append("</ul>")
					.append("</li>")
				.append("</div>");
						
						
	     /*"         		<li>"+
	      "        			<a href='#'>Руки <span class='badge'>143</span></a>"+
	       "       		</li>"+
	        "      		<li>"+
	         "     			"+
	          "    			<a href='#'>Ноги <span class='badge'>223</span></a>"+
	           "   		</li>"+
	            "  		<li>"+
	             " 			"+
	              "			<a href='#'>Рама <span class='badge'>98</span></a>"+
	              	"	</li>"+
	              	"</ul>"+*/
//	   "         </li>"+
//	"			<li class=''>"+
//	"              	<h3>"+
//	"                  <span>Тренажер</span>"+
//	"              	</h3>"+
//	"              	<ul class='nav nav-list'>"+
//	"              		<li>"+
//	"              			<a href='#'>Гантелі <span class='badge'>443</span></a>"+
//	"              		</li>"+
//	"              		<li>"+
//	"              			"+
//	"              			<a href='#'>Штанга <span class='badge'>12</span></a>"+
//	"              		</li>"+
//	"              		<li>"+
//	"              			"+
//	"              			<a href='#'>Тіло <span class='badge'>664</span></a>"+
//	"              		</li>"+
//	"              	</ul>"+
//	"            </li>"+
//	"			<li class=''>"+
//	"              	<h3>"+
//	"                  <span>Складність</span>"+
//	"              	</h3>"+
//	"              	<ul class='nav nav-list'>"+
//	"              		<li>"+
//	"              			<a href='#'>Новачок <span class='badge'>23</span></a>"+
//	"              		</li>"+
//	"              		<li>"+
//	"              			"+
//	"              			<a href='#'>Продвинутий <span class='badge'>67</span></a>"+
//	"              		</li>"+
//	"              		<li>"+
//	"              			"+
//	"              			<a href='#'>Ветиран <span class='badge'>12</span></a>"+
//	"              		</li>"+
//	"              	</ul>"+
//	"            </li>"+
//	"			<li class=''>"+
//	"              	<h3>"+
//	"                  <span>Тип</span>"+
//	"              	</h3>"+
//	"              	<ul class='nav nav-list'>"+
//	"              		<li>"+
//	"              			<a href='#'>На масу <span class='badge'>45</span></a>"+
//	"              		</li>"+
//	"              		<li>"+
//	"              			"+
//	"              			<a href='#'>Для сушки <span class='badge'>33</span></a>"+
//	"              		</li>"+
//	"              	</ul>"+
//	"            </li>"+
//	"			"+
//	"		</ul>"+
//	"	</div>");
		
		return sidebar.toString();
	}
	
	public static String createUserSidebar(boolean authorize){
		StringBuilder sidebar = new StringBuilder();
		
		sidebar.append("<div class='sidebar-background'>")
	 			.append("<div class='primary-sidebar-background'></div>")
	 		.append("</div>");
		
		if(authorize){
			sidebar.append("<div class='primary-sidebar'>"+
		"	<ul class='nav nav-list'>"+
		"		<li class=''>"+
	    "          	<a href='#'>"+
	    "              <span>Мої тренування</span>"+
	    "          	</a>"+
	    "        </li>"+
		"		<li class=''>"+
	    "          	<a href='#'>"+
	    "              <span>Мої вправи</span>"+
	    "          	</a>"+
	    "        </li>"+
		"		<li class=''>"+
	    "          	<a href='#'>"+
	    "              <span>Статистика</span>"+
	    "          	</a>"+
	    "        </li>"+
		"		<li class=''>"+
	       "       	<a href='#'>"+
	       "           <span>Порад</span>"+
	      "        	</a>"+
	     "       </li>"+
		"		"+
		"	</ul>"+
		"</div>");	
		}
		
		return sidebar.toString();
	}
	
	public static String createFooter(){
		StringBuilder footer = new StringBuilder();
		
		footer.append("<footer>"+
		"	<div class='container'>"+
		"	<div class='row'>"+
		"		<div class='col-md-4'>"+ 
		"			<p class='text-muted credit'>© Company Gym Advicer 2013</p>"+
		"		</div>"+
		"		<div class='col-md-4'>"+ 
		"			<p class='text-muted credit'>Follow us</p>"+
		"			<div class='row'>"+
		"				<img src='assets/pictures/facebook_512x512.png' alt='google'/>"+
		"				<img src='assets/pictures/vkontakte_512x512.png' alt='google'/>"+
		"				<img src='assets/pictures/twitter_512x512.png' alt='google'/>"+				
		"			</div>"+
		"		</div>"+
		"		<div class='col-md-4'>"+ 
		"			<p class='text-muted credit'>Download app for android</p>"+
		"			<img src='assets/pictures/androidm.png' alt='androidapp'/>"+
		"		</div>"+			
		"	</div>"+
		"</footer>");
		
		return footer.toString();
	}
	
	public static String createExerciseListItem(Integer exerciseId){
		StringBuilder exercise = new StringBuilder();
		
		exercise.append("<div class='row'>"+
			"	<div class='col-md-12'>"+
			"		"+
			"		<div class='row'>"+
			"			<div class='col-md-4'>"+
			"				<img width='200' height='200' src='http://dailyfit.ru/wp-content/uploads/2013/04/855_1-120x120.jpg' class='fl-l' alt='Муж. поз. 1' style='margin-right: 20px;'>"+
			"			</div>"+
			"			<div class='col-md-5'>"+
			"				<h2><a href='#'>Вправа з гантелями</a></h2>"+
			"				<p>Група мязів: біцепс</p>"+
			"				<p>Тренажер: гантелі</p>"+
			"				<p>Рівень складності: підсніжник</p>"+
			"				<p>Тип: на масу</p>"+
			"			</div>"+
			"			<div class='col-md-3'>"+
			"				<div class='btn-group-vertical'>"+
			"					<button  type='button' class='btn btn-primary'>Детальніше</button>"+
			"					<button type='button' class='btn btn-warning'>Додати в вибрані</button>"+
			"					<button type='button' class='btn btn-success'>Додати до тренування</button>"+
			"				</div>"+
			"			</div>"+
			"		</div>"+
			"	</div>"+
			"</div>");
		
		return exercise.toString();
	}
}
