/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, TowerDef.java
 * 
 * purpose: Create a tower defense game with extra feature that including
 * 			language setting option and select levels option, after clicking
 * 			levels, click new game, it will start with the level that you
 * 			picked from. The default level is from level 1. Furthermore, in the
 * 			game stage, click menu file then click start in order to let
 * 			enemies wave begin to run. We have a total of 3 levels. After 1st
 * 			and 2nd level, if you are still alive, you will receive a pop up 
 * 			window that ask you if you want to continue. After the level 3, if
 * 			you finish all enemies and still alive, you will receive the
 * 			winning window that tells you win the game. 
 *			Congratulation and enjoy the game. 
 */

import javafx.application.Application;
import view.View;

public class TowerDef {

	public static void main(String[] args) {
		Application.launch(View.class, args);
	}

}

