/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnalgo;

/**
 *
 * @author USER
 */
import java.applet.*;
import java.awt.*;
import java.util.*;
import static javafx.application.Application.launch;
import static javafx.scene.paint.Color.THISTLE;


class VisPanel extends Panel {
	public static final int PANEL_WIDTH=600;			// panel width in pixels
	public static final int PANEL_HEIGHT=370;			// panel height in pixels

	// vis margins
	public static final int MARGIN_X=30;				// left margin in pixels
	public static final int MARGIN_Y=30;				// top margin in pixels

	// animation step constants
	public static final int MOVE_STEPS=100;				// typical animation steps

	// element colors
	public Color oBackCol=Color.WHITE;				// background color
	public Color oIndexCol=Color.blue;					// index drawing color
	public Color oArrayCol=Color.white;					// array box color
	public Color oNodeCol=Color.LIGHT_GRAY;					// node box color
	public Color oValueCol=Color.black;					// value drawing color
	public Color oCompCol=Color.yellow;					// comparison color

	// box drawing parameters
	public int iBoxSize=20;								// size of boxes in pixels
	public int iAYOffset=310;							// height offset to array draw

	// visualization grid data
	public int iBoxX[]=									// node x offsets for drawing
		{ 0, 19, 38, 57, 76, 95, 114, 133, 152, 171, 190, 209, 228, 247, 266 };
	public int iBoxY[]=									// node y offsets for drawing
		{ 140, 60, 220, 20, 100, 180, 260, 0, 40, 80, 120, 160, 200, 240, 280 };

	// heap bounds
	public int iHeapStart=0;							// start of array to draw
	public int iHeapEnd=0;								// end of array to draw
	
	// Vis panel animation objects
	public int iAnimTime=2;								// animation pause time
	public int iBlinkTime=100;							// blink pause time
	public int iBlinkTimes=3;							// number of blinks
	public Image oBackBuffer=null;						// panel back buffer

	// the parent applet
	public HeapVisu oHSApplet=null;						// reference to parent applet
	public ExplainBox oExplainBox=new ExplainBox();		// explanation box

	// constructor
	// applet: reference to parent applet
	public VisPanel(HeapVisu applet) {
		// save the parent applet
		oHSApplet=applet;

		// add the explain box to the panel
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(oExplainBox);
		oExplainBox.hide();
	}

	// paint the panel
	// g: reference to graphics context
	public void paint(Graphics g) {
		// get the offscreen buffer
		if(oBackBuffer==null)
			oBackBuffer=createImage(bounds().width,bounds().height);

		// draw the panel
		draw(oBackBuffer.getGraphics(),true,true,true);
		g.drawImage(oBackBuffer,0,0,this);
	}

	// draw the panel
	// g: reference to graphics context
	// bErase: whether to erase the background
	// bDrawHeap: whether to render the current heap boxes
	// bDrawArray: whether to render the array boxes
	public void draw(Graphics g, boolean bErase, boolean bDrawHeap, 
		boolean bDrawArray) {
		// erase the background
		if(bErase==true) {
			g.setColor(oBackCol);
			g.fillRect(0,0,bounds().width,bounds().height);
		}

		// draw the vis panel
		if(bDrawHeap) drawHeap(g,false);
		if(bDrawArray) drawArray(g,false);
	}

	// blit the backbuffer
	// oBuffer: reference to the back buffer
	public void blit(Image oBuffer) {
		if(oBuffer==null) return;

		// draw the provided buffer
		getGraphics().drawImage(oBuffer,0,0,this);
	}

	// draw a box
	// g: graphics context
	// iX: x coord for left of box
	// iY: y coord for top of box
	// iValue: the value to place in the box
	// oBoxCol: the color to paint the box
	public void drawBox(Graphics g, int iX, int iY, int iValue,
		Color oBoxCol) {

		// fill the box
		g.setColor(oBoxCol);
		g.fillRect(iX,iY,iBoxSize,iBoxSize);
		g.setColor(oValueCol);
		g.drawRect(iX,iY,iBoxSize,iBoxSize);
		
		// draw the contents
		g.drawString(String.valueOf(iValue),iX+2,iY+iBoxSize-2);
	}

	// draw array elements
	// g: graphics context
	// bErase: whether to erase previous array boxes
	public void drawArray(Graphics g, boolean bErase) {
		if(bErase==true) {
			g.setColor(oBackCol);
			g.fillRect(0,iAYOffset+MARGIN_Y,bounds().width,
				bounds().height-(iAYOffset+MARGIN_Y));
		}

		for(int iCount=0;iCount<oHSApplet.iHeapSize;iCount++) {
			// draw array boxes
			drawBox(g,iBoxX[iCount]+MARGIN_X,iAYOffset+MARGIN_Y,
				oHSApplet.iHeap[iCount],oArrayCol);

			// draw array indices
			g.setColor(oIndexCol);
			g.drawString(String.valueOf(iCount),iBoxX[iCount]+2+MARGIN_X,
				iAYOffset+2*(iBoxSize-1)+MARGIN_Y);
		}
	}

	// draw the heap
	// g: graphics context
	// bErase: whether to erase previous heap boxes
	public void drawHeap(Graphics g, boolean bErase) {
		if(bErase==true) {
			g.setColor(oBackCol);
			g.fillRect(0,MARGIN_Y,bounds().width,iAYOffset);
		}

		for(int iCount=iHeapStart;iCount<iHeapEnd;iCount++) {
			int iBox=iCount;

			// draw heap boxes
			drawBox(g,iBoxX[iBox]+MARGIN_X,iBoxY[iBox]+MARGIN_Y,
				oHSApplet.iHeap[iCount],oNodeCol);

			// draw connecting lines
			// right child
			if((2*iBox+1)<iHeapEnd) {
				g.setColor(oValueCol);
				g.drawLine(iBoxX[iBox+1]+MARGIN_X,iBoxY[iBox]+MARGIN_Y+
					(iBoxSize/2),iBoxX[2*iBox+1]+MARGIN_X,iBoxY[2*iBox+1]+
					MARGIN_Y+(iBoxSize/2));
			}

			// left child
			if((2*iBox+2)<iHeapEnd) {
				g.setColor(oValueCol);
				g.drawLine(iBoxX[iBox+1]+MARGIN_X,iBoxY[iBox]+MARGIN_Y+
					(iBoxSize/2),iBoxX[2*iBox+2]+MARGIN_X,iBoxY[2*iBox+2]+
					MARGIN_Y+(iBoxSize/2));
			}
		}
	}

	// minimum panel size
	public Dimension minimumSize() {
		return(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
	}

	// preferred size
	public Dimension preferredSize() {
		return(minimumSize());
	}

	// add to the heap
	// oBuffer: reference to panel back buffer
	public void addHeap(Image oBuffer) {
		if(oBuffer==null)
			return;

		// shift start backward
		iHeapStart--;
		if(iHeapStart<0)
			iHeapStart=0;

		int iPrevY=-1;

		// move start box from array to heap
		Graphics g=oBuffer.getGraphics();
		for(int iCount=0;iCount<MOVE_STEPS;iCount++) {
			// calculate offsets
			int iX=iBoxX[iHeapStart]+MARGIN_X;
			int iY=iAYOffset+MARGIN_Y-iBoxSize-1-
				((iAYOffset-iBoxY[iHeapStart]-iBoxSize-1)*iCount)/(MOVE_STEPS-1);

			// erase previous box
			if(iPrevY>=0) {
				g.setColor(oBackCol);
				g.fillRect(iX,iPrevY,iBoxSize+1,iBoxSize+1);
			}

			// draw the box
			drawBox(g,iX,iY,oHSApplet.iHeap[iHeapStart],oNodeCol);
			blit(oBuffer);
			try { Thread.sleep(iAnimTime); }
                        
			catch(InterruptedException e){
                            
                        };
//                        

			// save old position
			iPrevY=iY;
		}

		// draw the panel
		draw(g,false,true,false);
		blit(oBuffer);
	}

	// remove from the heap
	// oBuffer: reference to panel back buffer
	public void remHeap(Image oBuffer) {
		if(oBuffer==null)
			return;
		
		// shift end backward
		iHeapEnd--;
		if(iHeapEnd<0)
			iHeapEnd=0;

		int iPrevY=-1;

		// move end box from heap to array
		Graphics g=oBuffer.getGraphics();
		for(int iCount=0;iCount<MOVE_STEPS;iCount++) {
			// calculate offsets
			int iX=iBoxX[iHeapEnd]+MARGIN_X;
			int iY=iBoxY[iHeapEnd]+MARGIN_Y+
				((iAYOffset-iBoxY[iHeapEnd]-iBoxSize-1)*iCount)/(MOVE_STEPS-1);

			// erase previous box
			if(iPrevY>=0) {
				g.setColor(oBackCol);
				g.fillRect(iX,iPrevY,iBoxSize+1,iBoxSize+1);
			}

			// draw the box
			drawBox(g,iX,iY,oHSApplet.iHeap[iHeapEnd],oNodeCol);
			blit(oBuffer);
			try { Thread.sleep(iAnimTime); }
			catch(InterruptedException e){
                            
                        };

			// save the old position
			iPrevY=iY;
		}

		// draw the panel
		draw(g,true,true,true);
		blit(oBuffer);
	}

	// swap two node boxes
	// oBuffer: reference to back buffer
	// iNode1: heap index of first node to swap
	// iNode2: heap index of second node to swap
	public void swapNodes(Image oBuffer, int iNode1, int iNode2) {
		if(oBuffer==null)
			return;
		if(iNode1==iNode2)
			return;

		int iPrev1X=-1;
		int iPrev1Y=-1;
		int iPrev2X=-1;
		int iPrev2Y=-1;

		Graphics g=oBuffer.getGraphics();
		for(int iCount=0;iCount<MOVE_STEPS;iCount++) {
			// calculate
			int iBox1X=iBoxX[iNode1]+MARGIN_X+
				((iBoxX[iNode2]-iBoxX[iNode1])*(MOVE_STEPS-iCount-1))/(MOVE_STEPS-1);
			int iBox1Y=iBoxY[iNode1]+MARGIN_Y+
				((iBoxY[iNode2]-iBoxY[iNode1])*(MOVE_STEPS-iCount-1))/(MOVE_STEPS-1);
			int iBox2X=iBoxX[iNode2]+MARGIN_X+
				((iBoxX[iNode1]-iBoxX[iNode2])*(MOVE_STEPS-iCount-1))/(MOVE_STEPS-1);
			int iBox2Y=iBoxY[iNode2]+MARGIN_Y+
				((iBoxY[iNode1]-iBoxY[iNode2])*(MOVE_STEPS-iCount-1))/(MOVE_STEPS-1);

			// erase previous boxes
			if(iPrev1X>=0) {
				g.setColor(oBackCol);
				g.fillRect(iPrev1X,iPrev1Y,iBoxSize+1,iBoxSize+1);
				g.fillRect(iPrev2X,iPrev2Y,iBoxSize+1,iBoxSize+1);
			}

			// draw the boxes
			drawBox(g,iBox1X,iBox1Y,oHSApplet.iHeap[iNode1],oNodeCol);
			drawBox(g,iBox2X,iBox2Y,oHSApplet.iHeap[iNode2],oNodeCol);
			blit(oBuffer);
			try { Thread.sleep(iAnimTime); }
			catch(InterruptedException e){
                            
                        };

			// save previous positions
			iPrev1X=iBox1X;
			iPrev1Y=iBox1Y;
			iPrev2X=iBox2X;
			iPrev2Y=iBox2Y;
		}

		// draw the panel
		draw(g,false,true,false);
		blit(oBuffer);
	}

	// compare two nodes
	// oBuffer: reference to back buffer
	// iNode1: first heap node index to compare
	// iNode2: second heap node index to compare
	public void compNodes(Image oBuffer, int iNode1, int iNode2) {
		if(oBuffer==null)
			return;

		Graphics g=oBuffer.getGraphics();

		// blink two node boxes
		for(int iCount=0;iCount<iBlinkTimes;iCount++) {
			// highlight nodes
			drawBox(g,iBoxX[iNode1]+MARGIN_X,iBoxY[iNode1]+MARGIN_Y,
				oHSApplet.iHeap[iNode1],oCompCol);
			drawBox(g,iBoxX[iNode2]+MARGIN_X,iBoxY[iNode2]+MARGIN_Y,
				oHSApplet.iHeap[iNode2],oCompCol);

			// highlight array items
			drawBox(g,iBoxX[iNode1]+MARGIN_X,iAYOffset+MARGIN_Y,
				oHSApplet.iHeap[iNode1],oCompCol);
			drawBox(g,iBoxX[iNode2]+MARGIN_X,iAYOffset+MARGIN_Y,
				oHSApplet.iHeap[iNode2],oCompCol);
			blit(oBuffer);
			try { Thread.sleep(iBlinkTime); }
			catch(InterruptedException e){
                            
                        };

			// redraw normal nodes
			drawBox(g,iBoxX[iNode1]+MARGIN_X,iBoxY[iNode1]+MARGIN_Y,
				oHSApplet.iHeap[iNode1],oNodeCol);
			drawBox(g,iBoxX[iNode2]+MARGIN_X,iBoxY[iNode2]+MARGIN_Y,
				oHSApplet.iHeap[iNode2],oNodeCol);

			// redraw array items
			drawBox(g,iBoxX[iNode1]+MARGIN_X,iAYOffset+MARGIN_Y,
				oHSApplet.iHeap[iNode1],oArrayCol);
			drawBox(g,iBoxX[iNode2]+MARGIN_X,iAYOffset+MARGIN_Y,
				oHSApplet.iHeap[iNode2],oArrayCol);
			blit(oBuffer);
			try { Thread.sleep(iBlinkTime); }
			catch(InterruptedException e){
                            
                        };
		}
	}

	// handle mouse movement
	// event: reference to mouse event information
	// x: x coord of event
	// y: y coord of event
	public boolean mouseExit(Event event, int x, int y) {
		oExplainBox.hide();
		return true;
	}

	// handle a mouse click
	// event: reference to mouse event information
	// x: x coord of event
	// y: y coord of event
	public boolean mouseUp(Event event, int x, int y) {
		// set the explanation text
		if(y<(iAYOffset+MARGIN_Y))
			oExplainBox.sText="current heap display";
		else
			oExplainBox.sText="current array display";
		
		// show the explanation box
		if(x>=0 && y>=0)
			oExplainBox.move(x,y);
		oExplainBox.validate();
		oExplainBox.show();
		return true;
	}
}


//
// This class handles management of the code display and all
// associated graphics for the heap sort algorithm.
//==============================================================================
class CodePanel extends Panel {
	// panel size
	public static final int PANEL_WIDTH=400;			// panel width in pixels
	public static final int PANEL_HEIGHT=460;			// panel height in pixels

	// code margins
	public static final int MARGIN_X=4;					// left margin in pixels
	public static final int MARGIN_Y=4;					// top margin in pixels

	// line parameters
	public int iLineSize;								// height of line in pixels
	public int iYOffset;								// vertical drawing offset

	// element colors
	public Color oBackCol=Color.white;					// panel background color
	public Color oCodeCol=Color.black;					// panel code color
	public Color oCurrCol=Color.cyan;					// panel highlight color

	// line constants
	public static final int NO_LINE=-1;					// no line no.
	public static final int SIFTLINE=0;					// the line no. of sift()
	public static final int MAKELINE=13;				// the line no. of make()
	public static final int SORTLINE=18;				// the line no. of sort()

	// lines of code
	public int iCurrLine=NO_LINE;						// the current line
	public int iPrevLine=NO_LINE;						// the previous line
//	public static String sCode[]={						// array of code lines
//	"public void sift(int iHeap[], int iHeapSize, int iNode) {",
//	"  int i, j;",
//	"  j=iNode;",
//	"  do {",
//	"    i=j;",
//	"    if((2*i+1)<iHeapSize && iHeap[2*i+1]>iHeap[j])",
//	"      j=2*i+1;",
//	"    if((2*i+2)<iHeapSize && iHeap[2*i+2]>iHeap[j])",
//	"      j=2*i+2;",
//	"    swap(i,j);",
//	"  } while(i!=j);",
//	"}",
//	"",
//	"public void makeHeap(int iHeap[], int iHeapSize) {",
//	"  for(int iCount=iHeapSize-1;iCount>=0;iCount--)",
//	"    sift(iHeap,iHeapSize,iCount);",
//	"}",
//	"",
//	"public void sortHeap(int iHeap[], int iHeapSize) {",
//	"  for(int iCount=iHeapSize-1;iCount>0;iCount--) {",
//	"    swap(iHeap[0],iHeap[iCount]);",
//	"    sift(iHeap,iCount,0);",
//	"  }",
//	"}" };

	// lines of explanation
	public static String sExplain[]={					// array of code explanations
	"sift() filters the provided node to the bottom",
	"node indices for parent/child comparisons",
	"start with the provided node",
	"start the loop",
	"set the current node as the parent",
	"compare the node with the right child",
	"if the right child is larger, set it as the parent",
	"compare the node with the left child",
	"if the left child is larger, set is as the parent",
	"swap the parent and larger child nodes",
	"perform the loop until reaching the bottom",
	"",
	"",
	"makeHeap() constructs a heap from an array",
	"loop through all elements working backward",
	"sift the current element to the bottom",
	"",
	"",
	"sortHeap() sorts a heap in ascending order",
	"loop through all elements working backward",
	"swap the top (largest) value with the last node",
	"sift the topmost node to the bottom",
	"",
	"" };

	// the parent applet
	public HeapVisu oHSApplet=null;						// reference to parent applet
	public Image oBackBuffer=null;						// reference to back buffer
	public ExplainBox oExplainBox=new ExplainBox();		// explanation box

	// constructor
	// applet: reference to parent applet
	public CodePanel(HeapVisu applet) {
		// save the parent applet
		oHSApplet=applet;

		// add the explain box to the panel
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(oExplainBox);
		oExplainBox.hide();
	}

	// paint the panel
	// g: graphics context
	public void paint(Graphics g) {
		// get the back buffer
		if(oBackBuffer==null)
			oBackBuffer=createImage(bounds().width,bounds().height);

		// get font metrics
		FontMetrics fm=g.getFontMetrics();

		// save line parameters
		iLineSize=fm.getHeight();
		iYOffset=fm.getDescent();

		// draw the panel
		//draw(oBackBuffer.getGraphics());
		g.drawImage(oBackBuffer,0,0,this);
	}

	// draw the panel
	// g: graphics context
//	public void draw(Graphics g) {
//		drawCode(g);
//		drawCurrLine(g);
//	}

	// draw the code
	// g: graphics context
//	public void drawCode(Graphics g) {
//		g.setColor(oBackCol);
//		g.fillRect(0,0,bounds().width,bounds().height);
//
//		// draw all lines of code
//		g.setColor(oCodeCol);
//		for(int iCount=0;iCount<sCode.length;iCount++)
//			g.drawString(sCode[iCount],MARGIN_X,iLineSize*(iCount+1)-
//				iYOffset+MARGIN_Y);
//	}

	// draw the current line
	// g: graphics context
//	public void drawCurrLine(Graphics g) {
//		// redraw prev line if possible
//		if(iPrevLine>=0 && iPrevLine<sCode.length) {
//			// clear the background
//			g.setColor(oBackCol);
//			g.fillRect(0,iPrevLine*iLineSize+MARGIN_Y,bounds().width,iLineSize);
//
//			// code string
//			g.setColor(oCodeCol);
//			g.drawString(sCode[iPrevLine],MARGIN_X,iLineSize*(iPrevLine+1)-
//				iYOffset+MARGIN_Y);
//		}
//
//		if(iCurrLine<0 || iCurrLine>=sCode.length)
//			return;
//
//		// highlight box
//		g.setColor(oCurrCol);
//		g.fillRect(0,iCurrLine*iLineSize+MARGIN_Y,bounds().width,iLineSize);
//
//		// code string
//		g.setColor(oCodeCol);
//		g.drawString(sCode[iCurrLine],MARGIN_X,iLineSize*(iCurrLine+1)-
//			iYOffset+MARGIN_Y);
//	}

	// minimum panel size
	public Dimension minimumSize() {
		return(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
	}

	// preferred size
	public Dimension preferredSize() {
		return(minimumSize());
	}

//	// advance the current line
//	public void advanceLine() {
//		// increment current line
//		iPrevLine=iCurrLine;
//		iCurrLine++;
//
//		// check array bounds
//		if(iCurrLine>=sCode.length)
//			iCurrLine=0;
//
//		// check if valid line
//		while(sCode[iCurrLine].equals("")) {
//			iCurrLine++;
//			if(iCurrLine>=sCode.length)
//				iCurrLine=0;
//		}
//
//		// redraw current line
//		drawCurrLine(getGraphics());
//	}

//	// set the current line
//	// iLine: line no. to set as current
//	public void setLine(int iLine) {
//		if(iLine<0 || iLine>=sCode.length)
//			return;
//
//		// save current line
//		iPrevLine=iCurrLine;
//		iCurrLine=iLine;
//
//		// check if valid line
//		while(sCode[iCurrLine].equals("")) {
//			iCurrLine++;
//			if(iCurrLine>=sCode.length)
//				iCurrLine=0;
//		}
//
//		// redraw current line		
//		drawCurrLine(getGraphics());
//	}

	// handle mouse movement
	// event: reference to mouse event info
	// x: x coord of event
	// y: y coord of event
	public boolean mouseExit(Event event, int x, int y) {
		oExplainBox.hide();
		return true;
	}

	// handle a mouse click
	// event: reference to mouse event info
	// x: x coord of event
	// y: y coord of event
	public boolean mouseUp(Event event, int x, int y) {
		// set the explanation text
		int iLine=(y-MARGIN_Y-iYOffset)/iLineSize;
		if((iLine<sExplain.length) && (!sExplain[iLine].equals(""))) {
			oExplainBox.sText=sExplain[iLine];
			if(x>=0 && y>=0)
				oExplainBox.move(x,y);
			oExplainBox.validate();
			oExplainBox.show();
		} 
		return true;
	}
}


class ExplainBox extends Canvas {
	// box margins
	public static final int MARGIN_X = 6;				// left margin for text
	public static final int MARGIN_Y = 3;				// top margin for text
	
	// box colors
	public Color oBackCol=Color.darkGray;				// box background color
	public Color oDrawCol=Color.cyan;					// box draw color

	// the explanation text
	public String sText="explanation text...";			// box text to draw

	// constructor
	public ExplainBox() {
		// set box font
		setFont(new Font("Helvetica",Font.PLAIN,12));
		validate();
	}

	// rebuild the explanation box
	public void validate() {
		FontMetrics fm=getFontMetrics(getFont());
		int iHeight=fm.getAscent()+fm.getDescent()+2*MARGIN_Y;
		int iWidth=fm.stringWidth(sText)+2*MARGIN_X;
		resize(iWidth+1,iHeight+1);
	}
  
	// paint the box
	// g: graphics context
	public void paint(Graphics g) {
		// get the font metrics
		FontMetrics fm=g.getFontMetrics();
		int iHeight=fm.getAscent()+fm.getDescent()+2*MARGIN_Y;
		int iWidth=fm.stringWidth(sText)+2*MARGIN_X;

		// paint the box
		g.setColor(oBackCol);
		g.fillRect(0,0,iWidth,iHeight);
		g.setColor(oDrawCol);
		g.drawRect(0,0,iWidth,iHeight);
		
		// paint the text
		g.drawString(sText,MARGIN_X,MARGIN_Y+fm.getAscent());
  }
}


class UtilPanel extends Panel {
	public static final int PANEL_WIDTH=600;			// panel width in pixels
	public static final int PANEL_HEIGHT=38;			// panel height in pixels

	// element colors
	public Color oBackCol=Color.white;					// panel background color

	// array controls
	public String sArray="1 15 2 14 3 13 4 12 5 11 6 10 7 9 8";	// default array string
	//public Choice oArrayChoice=new Choice();					// array choice control
	public String sArrChoices[]={								// choice strings
		"User case", "Avg. case", "Best case", "Worst case" };
	public static final int USER_CASE=0;				// user array choice
	public static final int AVG_CASE=1;					// average case array choice
	public static final int BEST_CASE=2;				// best case array choice
	public static final int WORST_CASE=3;				// worst case array choice
	public String sArrays[]={							// array choices
		"1 15 2 14 3 13 4 12 5 11 6 10 7 9 8",
		"15 14 13 12 11 10 9 8 7 6 5 4 3 2 1",
		"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15" };
	public TextField oArrayField=new TextField(32);
	public Button oApplyButton=new Button("Input Given");	// array apply button

	// execution controls
	public Button oRunButton=new Button("Start Heap Sort");		// execute button
	public Button oStopButton=new Button("Pause");		// pause button
	//public Choice oRunChoice=new Choice();				// granularity choice control
	public String sGranChoices[]={						// granularity choices
		"Entire algorithm",
		"Next algorithm stage",
		"Next sift procedure",
		"Next loop iteration",
		"Next statement" };

	// current granularity
	public int iGranularity=0;							// current granularity

	// the parent applet
	public HeapVisu oHSApplet=null;						// reference to parent applet

	// constructor
	// applet: reference to parent applet
	public UtilPanel(HeapVisu applet) {
		// save the parent applet
		oHSApplet=applet;
		
		// init the array choices
		for(int iCount=0;iCount<sArrChoices.length;iCount++)
			//oArrayChoice.addItem(sArrChoices[iCount]);

		// init the run choices
		for(int iCount2=0;iCount2<sGranChoices.length;iCount2++)
			//oRunChoice.addItem(sGranChoices[iCount2]);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		// add the array utility components
		//add(oArrayChoice);
		oArrayField.setText(sArray);
		add(oArrayField);
		add(oApplyButton);

		// add the execution controls
		add(oRunButton);
		//add(oRunChoice);
		add(oStopButton);
	}

	// paint the panel
	// g: graphics context
	public void paint(Graphics g) {
		g.setColor(oBackCol);
		g.fillRect(0,0,bounds().width,bounds().height);
	}

	// minimum panel size
	public Dimension minimumSize() {
		return(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
	}

	// preferred size
	public Dimension preferredSize() {
		return(minimumSize());
	}

	// parse the user supplied array
	public void parseArray() {
		// get the array parameters
		sArray=oArrayField.getText();

		int iIndex=0;
		Integer iElement=new Integer(0);
		StringTokenizer ST=new StringTokenizer(sArray," ");

		// parse the array
		while(ST.hasMoreTokens() && iIndex<HeapVisu.MAX_NODES) {
			// get the next element
			String sTemp=ST.nextToken(" ");

			// convert the array element
			try { iElement=Integer.valueOf(sTemp); }
			catch(NumberFormatException e) {iElement=new Integer(0);}
			
			// restrict the value size
			if(iElement.intValue()<0) iElement=new Integer(0);
			else if(iElement.intValue()>99) iElement=new Integer(99);

			// store the array element
			oHSApplet.iHeap[iIndex]=iElement.intValue();

			// update the index
			iIndex++;
		}
		
		// store the heap size
		oHSApplet.iHeapSize=iIndex;
	
		// reset current execution line
		oHSApplet.oCodePanel.iPrevLine=CodePanel.NO_LINE;
		oHSApplet.oCodePanel.iCurrLine=CodePanel.NO_LINE;

		// reset the heap bounds
		oHSApplet.oVisPanel.iHeapStart=oHSApplet.iHeapSize;
		oHSApplet.oVisPanel.iHeapEnd=oHSApplet.iHeapSize;
	}

	// handle all events
	// event: reference to event info
       
	public boolean handleEvent(Event event) {
		switch(event.id) {
			case Event.ACTION_EVENT: {
				// handle the array choice list
//				if(event.target==oArrayChoice) 
//					doArray();

				// handle the "apply" button
				if(event.target==oApplyButton) 
					doApply();

				// handle the "execute" button
				if(event.target==oRunButton) 
					doRun();

				// handle the granularity choice list
//				if(event.target==oRunChoice) 
//					doChoice();

				// handle the stop button
				if(event.target==oStopButton) 
					doStop();
				return true;
			}
		}
		return false;
	}

	// set the array choice
//	public void doArray() {
//		//int iArray=oArrayChoice.getSelectedIndex();
//		
//		// allow user entered array
//		if(iArray==USER_CASE) {
//			oArrayField.enable();
//			oArrayField.setText(sArray);
//		}
//
//		// otherwise use stock scenario
//		else {
//			oArrayField.disable();
//			oArrayField.setText(sArrays[iArray-1]);
//		}
//	}

	// apply the user provided array
	public void doApply() {
		if(oHSApplet.oHSThread!=null) {
			oHSApplet.oHSThread.stop();
			oHSApplet.oHSThread=null;
		}
		
		// parse the array
		parseArray();

		// redraw the code
		//oHSApplet.oCodePanel.drawCode(oHSApplet.oCodePanel.getGraphics());

		// draw array contents
		Image oBuffer=oHSApplet.oVisPanel.oBackBuffer;
		oHSApplet.oVisPanel.draw(oBuffer.getGraphics(),
			true,true,true);
		oHSApplet.oVisPanel.blit(oBuffer);
	}

	// execute the algorithm
	public void doRun() {
		// launch a new thread
		if(oHSApplet.oHSThread==null) {
			oHSApplet.oHSThread=new HSThread(oHSApplet);
			oHSApplet.oHSThread.start();
		}

		// otherwise resume the thread
		else oHSApplet.oHSThread.resume();

		// disable the buttons
		oRunButton.disable();
		oApplyButton.disable();
	}

	// change the run granularity
//	public void doChoice() {
//		iGranularity=oRunChoice.getSelectedIndex();
//	}

	// stop algorithm execution
	public void doStop() {
		if(oHSApplet.oHSThread==null)
			return;
		if(oHSApplet.oHSThread.isAlive()==false)
			return;

		// suspend the thread
		oHSApplet.oHSThread.suspend();

		// enable the utility buttons
		oRunButton.enable();
		oApplyButton.enable();
	}
}



public class HeapVisu extends Applet {
	// applet size values
	public static final int APPLET_WIDTH=600;			// applet width in pixels
	public static final int APPLET_HEIGHT=400;			// applet height in pixels

	// the main array
	public static final int MAX_NODES=15;				// max no. of heap nodes

    static void main(String[] string) {
       HeapVisu g = new HeapVisu();
	Frame frame = new Frame("Heap sort");
        g.init();
        frame.add("Center", g);
        frame.resize(720,650);
        frame.show();
        frame.resize(720,650);
        frame.setLocationRelativeTo(null);
    }
  
	public int iHeapSize=0;								// current heap size
	public int iHeap[]=new int[MAX_NODES];				// current heap

	// the heap sort thread
	public HSThread oHSThread=null;						// reference to heap sort thread

	// the interface panels
	public VisPanel oVisPanel=new VisPanel(this);		// visualization panel
	public CodePanel oCodePanel=new CodePanel(this);	// code panel
	public UtilPanel oUtilPanel=new UtilPanel(this);	// utility panel

	// init the applet
	public void init() {
		// size the applet window
		resize(APPLET_WIDTH,APPLET_HEIGHT);

		// add the interface panels
		setLayout(new BorderLayout());
		add("Center",oVisPanel);
		//add("East",oCodePanel);
		add("South",oUtilPanel);

		// parse the default array
		oUtilPanel.parseArray();
	}
}


class HSThread extends Thread {
	// granularity constants
	public static final int GRAN_ENTIRE=0;				// entire algorithm
	public static final int GRAN_STAGE=1;				// next algorithm stage
	public static final int GRAN_SIFT=2;				// next sift procedure
	public static final int GRAN_LOOP=3;				// next loop iteration
	public static final int GRAN_SINGLE=4;				// next statement
	
	// sleep time
	public int iSleepTime=200;							// delay between statements

	// store the parent applet
	public Image oBuffer=null;							// reference to a buffer
	public HeapVisu oHSApplet=null;						// reference to parent applet

	// the Heap sort thread constructor
	// applet: reference to parent applet
	public HSThread(HeapVisu applet) {
		oHSApplet=applet;
	}

	// execute the heap sort thread
	public void run() {
		// store the back buffer
		oBuffer=oHSApplet.oVisPanel.oBackBuffer;

		// run the sort
		doSort(oHSApplet.iHeap,oHSApplet.iHeapSize);

		// reset applet's thread
		oHSApplet.oHSThread=null;

		// reset current execution line
		oHSApplet.oCodePanel.iPrevLine=CodePanel.NO_LINE;
		oHSApplet.oCodePanel.iCurrLine=CodePanel.NO_LINE;

		// reset the heap bounds
		oHSApplet.oVisPanel.iHeapStart=oHSApplet.iHeapSize;
		oHSApplet.oVisPanel.iHeapEnd=oHSApplet.iHeapSize;

		// redraw the code
	//	oHSApplet.oCodePanel.drawCode(oHSApplet.oCodePanel.getGraphics());

		// draw the panel
		oHSApplet.oVisPanel.draw(oBuffer.getGraphics(),
			true,true,true);
		oHSApplet.oVisPanel.blit(oBuffer);

		// enable execute button
		oHSApplet.oUtilPanel.oRunButton.enable();
		oHSApplet.oUtilPanel.oApplyButton.enable();
	}

	// the heap sort functions
	// sift a node down
	// iHeap[]: heap array
	// iHeapSize: heap size in nodes
	// iNode: node index to sift downward
	public void siftNode(int iHeap[], int iHeapSize, int iNode) {
		// set the current line
		//oHSApplet.oCodePanel.setLine(CodePanel.SIFTLINE);
		iteration(GRAN_SINGLE);

		// advance the line
		//oHSApplet.oCodePanel.advanceLine();
		int i, j;
		iteration(GRAN_SINGLE);

		// advance the line
		//oHSApplet.oCodePanel.advanceLine();
		j=iNode;
		iteration(GRAN_SINGLE);

		// advance the line
		//oHSApplet.oCodePanel.advanceLine();
		do {
			iteration(GRAN_LOOP);

			// advance the line
			//oHSApplet.oCodePanel.setLine(CodePanel.SIFTLINE+4);

			// get the node as the parent
			i=j;
			iteration(GRAN_SINGLE);

			// advance the line
			//oHSApplet.oCodePanel.advanceLine();

			// find the larger child of node i
			if((2*i+1)<iHeapSize && iHeap[2*i+1]>iHeap[j]) {
				iteration(GRAN_SINGLE);
				
				// advance the line
				oHSApplet.oVisPanel.compNodes(oBuffer,i,2*i+1);
				//oHSApplet.oCodePanel.advanceLine();
				j=2*i+1;
			}
			iteration(GRAN_SINGLE);

			// advance the line
			//oHSApplet.oCodePanel.setLine(CodePanel.SIFTLINE+7);

			// check left child
			if((2*i+2)<iHeapSize && iHeap[2*i+2]>iHeap[j]) {
				iteration(GRAN_SINGLE);
				
				// advance the line
				oHSApplet.oVisPanel.compNodes(oBuffer,i,2*i+2);
				//oHSApplet.oCodePanel.advanceLine();
				j=2*i+2;
			}
			iteration(GRAN_SINGLE);

			// advance the current line
			//oHSApplet.oCodePanel.setLine(CodePanel.SIFTLINE+9);

			// swap the larger child and the parent
			int iTemp=iHeap[j];
			iHeap[j]=iHeap[i];
			iHeap[i]=iTemp;
			iteration(GRAN_SINGLE);

			// advance to the while
			oHSApplet.oVisPanel.swapNodes(oBuffer,i,j);
			//oHSApplet.oCodePanel.advanceLine();
		}
		// if i=j, then the node is done
		while(i!=j);
	}

	// build a heap from the array
	// iHeap[]: heap array
	// iHeapSize: size of heap in nodes
	public void makeHeap(int iHeap[], int iHeapSize) {
		// set the line
		//oHSApplet.oCodePanel.setLine(CodePanel.MAKELINE);
		iteration(GRAN_STAGE);

		// advance the current line
		//oHSApplet.oCodePanel.advanceLine();
		for(int iCount=iHeapSize-1;iCount>=0;iCount--) {
			// add to the heap
			iteration(GRAN_LOOP);

			// add to the heap
			oHSApplet.oVisPanel.addHeap(oBuffer);

			// advance the current line
			//oHSApplet.oCodePanel.advanceLine();
			iteration(GRAN_SIFT);

			// sift new node down
			siftNode(iHeap,iHeapSize,iCount);

			// set for loop line
			//oHSApplet.oCodePanel.setLine(CodePanel.MAKELINE+1);
		}
	}

	// sort the heap
	// iHeap[]: heap array
	// iHeapSize: size of heap in nodes
	public void sortHeap(int iHeap[], int iHeapSize) {
		// set the line
		//oHSApplet.oCodePanel.setLine(CodePanel.SORTLINE);
		iteration(GRAN_STAGE);

		// advance the current line
		//oHSApplet.oCodePanel.advanceLine();
		for(int iCount=iHeapSize-1;iCount>0;iCount--) {
			iteration(GRAN_LOOP);

			// advance current line
			//oHSApplet.oCodePanel.advanceLine();

			// swap root with current node
			int iTemp=iHeap[0];
			iHeap[0]=iHeap[iCount];
			iHeap[iCount]=iTemp;
			iteration(GRAN_SINGLE);

			// remove from the heap
			oHSApplet.oVisPanel.swapNodes(oBuffer,0,iCount);
			oHSApplet.oVisPanel.remHeap(oBuffer);

			// advance current line
			//oHSApplet.oCodePanel.advanceLine();
			iteration(GRAN_SIFT);

			// sift the node down
			siftNode(iHeap,iCount,0);

			// set for loop line
			//oHSApplet.oCodePanel.setLine(CodePanel.SORTLINE+1);
		}
	}

	// perform a heap sort
	// iHeap[]: heap array
	// iHeapSize: size of heap in nodes
	public void doSort(int iHeap[], int iHeapSize) {
		makeHeap(iHeap,iHeapSize);
		sortHeap(iHeap,iHeapSize);

		// remove last node
		oHSApplet.oVisPanel.remHeap(oBuffer);
	}

	// perform an iteration
	// iGranule: granularity of statement iteration
	public void iteration(int iGranule) {
		// just sleep if within granularity
		if(iGranule>oHSApplet.oUtilPanel.iGranularity) {
			try { sleep(iSleepTime); }
			catch(InterruptedException e){
		};
                }
		// otherwise suspend execution
		else {
			oHSApplet.oUtilPanel.oRunButton.enable();
			oHSApplet.oUtilPanel.oApplyButton.enable();
			suspend();

			// disable utility buttons
			oHSApplet.oUtilPanel.oRunButton.disable();
			oHSApplet.oUtilPanel.oApplyButton.disable();
		}

		// draw the panel
		oHSApplet.oVisPanel.draw(oBuffer.getGraphics(),
			false,true,true);
		oHSApplet.oVisPanel.blit(oBuffer);
	

}

// public static void main(String[] args) {
//        launch(args);
//    }
}