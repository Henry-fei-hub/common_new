package com.delicacy.client.data;

import com.smartgwt.client.widgets.drawing.DrawGroup;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawPane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BarCode {

	private static final int[][] EANA
			= {
				{0, 0, 0, 1, 1, 0, 1},
				{0, 0, 1, 1, 0, 0, 1},
				{0, 0, 1, 0, 0, 1, 1},
				{0, 1, 1, 1, 1, 0, 1},
				{0, 1, 0, 0, 0, 1, 1},
				{0, 1, 1, 0, 0, 0, 1},
				{0, 1, 0, 1, 1, 1, 1},
				{0, 1, 1, 1, 0, 1, 1},
				{0, 1, 1, 0, 1, 1, 1},
				{0, 0, 0, 1, 0, 1, 1}
			};
	private static final int[][] EANB
			= {
				{0, 1, 0, 0, 1, 1, 1},
				{0, 1, 1, 0, 0, 1, 1},
				{0, 0, 1, 1, 0, 1, 1},
				{0, 1, 0, 0, 0, 0, 1},
				{0, 0, 1, 1, 1, 0, 1},
				{0, 1, 1, 1, 0, 0, 1},
				{0, 0, 0, 0, 1, 0, 1},
				{0, 0, 1, 0, 0, 0, 1},
				{0, 0, 0, 1, 0, 0, 1},
				{0, 0, 1, 0, 1, 1, 1}
			};
	private static final int[][] EANC
			= {
				{1, 1, 1, 0, 0, 1, 0},
				{1, 1, 0, 0, 1, 1, 0},
				{1, 1, 0, 1, 1, 0, 0},
				{1, 0, 0, 0, 0, 1, 0},
				{1, 0, 1, 1, 1, 0, 0},
				{1, 0, 0, 1, 1, 1, 0},
				{1, 0, 1, 0, 0, 0, 0},
				{1, 0, 0, 0, 1, 0, 0},
				{1, 0, 0, 1, 0, 0, 0},
				{1, 1, 1, 0, 1, 0, 0}
			};
	private static final int[][] EAND
			= {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 1, 0, 1, 1},
				{0, 0, 1, 1, 0, 1},
				{0, 0, 1, 1, 1, 0},
				{0, 1, 0, 0, 1, 1},
				{0, 1, 1, 0, 0, 1},
				{0, 1, 1, 1, 0, 0},
				{0, 1, 0, 1, 0, 1},
				{0, 1, 0, 1, 1, 0},
				{0, 1, 1, 0, 1, 0}
			};
	private static final int[] EANSTM = {11, 13, 57, 59, 103, 105};

	private final int[] barcodeArray = new int[113];
	public int BARCODEUNIT = 2;
	public int HEIGHT = 100;
	String barcodeString;

	public BarCode(String strbc) {
		barcodeString = strbc;
		int fc = strbc.charAt(0) - '0';
		int[] larr = EAND[fc];
		for (int i = 1; i < 13; i++) {
			char c = strbc.charAt(i);
			int cc = c - '0';
			int[] bbcode;
			if (i < 7) {
				if (larr[i - 1] == 0) {
					bbcode = EANA[cc];
				} else {
					bbcode = EANB[cc];
				}
			} else {
				bbcode = EANC[cc];
			}
			int idx = i < 7 ? (i - 1) * 7 + 14 : (i - 7) * 7 + 61;
			System.arraycopy(bbcode, 0, barcodeArray, idx, 7);
		}
	}

	public void addToDrawPane(DrawPane dp, int startx, int starty) {
		for (int i = 0; i < EANSTM.length; i++) {
			int x = EANSTM[i] * BARCODEUNIT + startx;
			DrawLine line = new DrawLine();
			line.setStartLeft(x);
			line.setStartTop(starty);
			line.setEndLeft(x);
			line.setEndTop(starty + HEIGHT);
			line.setLineColor("#000000");
			line.setLineWidth(BARCODEUNIT);
			dp.addDrawItem(line, true);
		}
		for (int i = 0; i < 113; i++) {
			if (barcodeArray[i] == 0) {
				continue;
			}
			int x = i * BARCODEUNIT;
			DrawLine line = new DrawLine();
			line.setStartLeft(startx + x);
			line.setStartTop(starty);
			line.setEndLeft(startx + x);
			line.setEndTop(starty + HEIGHT - 15);
			line.setLineColor("#000000");
			line.setLineWidth(BARCODEUNIT);
			dp.addDrawItem(line, true);
		}
		DrawLabel dl = new DrawLabel();
		dl.setLineColor("#000000");
		dl.setFontSize(12);
		dl.setContents(String.valueOf(barcodeString.charAt(0)));
		dl.setLeft(startx + 3 * BARCODEUNIT + 3);
		dl.setTop(starty + HEIGHT - 25);
		dp.addDrawItem(dl, true);
		for (int i = 1; i < 13; i++) {
			int idx = i < 7 ? (i - 1) * 7 + 14 : (i - 7) * 7 + 61;
			dl = new DrawLabel();
			dl.setLineColor("#000000");
			dl.setFontSize(12);
			dl.setContents(String.valueOf(barcodeString.charAt(i)));
			dl.setLeft(startx + idx * BARCODEUNIT + 3);
			dl.setTop(starty + HEIGHT - 25);
			dp.addDrawItem(dl, true);
		}
	}

	public DrawGroup generateDrawGroup(int startx, int starty) {
		DrawGroup dg = new DrawGroup();
		dg.setTop(starty);
		dg.setLeft(startx);
		dg.setHeight(HEIGHT);
		dg.setWidth(226);

		List<DrawItem> items = new ArrayList<DrawItem>();
		for (int i = 0; i < EANSTM.length; i++) {
			int x = EANSTM[i] * BARCODEUNIT;
			DrawLine line = new DrawLine();
			line.setStartLeft(x);
			line.setStartTop(0);
			line.setEndLeft(x);
			line.setEndTop(HEIGHT);
			line.setLineColor("#000000");
			line.setLineWidth(BARCODEUNIT);
			items.add(line);
		}
		for (int i = 0; i < 113; i++) {
			if (barcodeArray[i] == 0) {
				continue;
			}
			int x = i * BARCODEUNIT;
			DrawLine line = new DrawLine();
			line.setStartLeft(x);
			line.setStartTop(0);
			line.setEndLeft(x);
			line.setEndTop(HEIGHT - 15);
			line.setLineColor("#000000");
			line.setLineWidth(BARCODEUNIT);
			items.add(line);
		}
		DrawLabel dl = new DrawLabel();
		dl.setLineColor("#000000");
		dl.setFontSize(12);
		dl.setContents(String.valueOf(barcodeString.charAt(0)));
		dl.setLeft(3 * BARCODEUNIT + 3);
		dl.setTop(HEIGHT - 25);
		items.add(dl);
		for (int i = 1; i < 13; i++) {
			int idx = i < 7 ? (i - 1) * 7 + 14 : (i - 7) * 7 + 61;
			dl = new DrawLabel();
			dl.setLineColor("#000000");
			dl.setFontSize(12);
			dl.setContents(String.valueOf(barcodeString.charAt(i)));
			dl.setLeft(idx * BARCODEUNIT + 3);
			dl.setTop(HEIGHT - 25);
			items.add(dl);
		}
		DrawItem[] dis = new DrawItem[items.size()];
		int ii = 0;
		for (DrawItem di : items) {
			dis[ii++] = di;
		}
		dg.setDrawItems(dis);
		return dg;
	}

}
