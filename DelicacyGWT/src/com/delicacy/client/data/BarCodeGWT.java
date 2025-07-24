package com.delicacy.client.data;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;
import org.vectomatic.dom.svg.utils.SVGConstants;

/**
 *
 * @author caoguangxun
 */
public class BarCodeGWT {

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

    public BarCodeGWT(String strbc) {
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

    public OMSVGSVGElement generateSVG() {
        OMSVGDocument doc = OMSVGParser.currentDocument();
        OMSVGSVGElement svg = doc.createSVGSVGElement();
        for (int i = 0; i < EANSTM.length; i++) {
            int x = EANSTM[i] * BARCODEUNIT;
            OMSVGLineElement line = doc.createSVGLineElement(x, 0f, x, HEIGHT);
            line.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
            line.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_WIDTH_PROPERTY, String.valueOf(BARCODEUNIT));
            svg.appendChild(line);
        }
        for (int i = 0; i < 113; i++) {
            if (barcodeArray[i] == 0) {
                continue;
            }
            int x = i * BARCODEUNIT;
            OMSVGLineElement line = doc.createSVGLineElement(x, 0f, x, HEIGHT - 15);
            line.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
            line.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_WIDTH_PROPERTY, String.valueOf(BARCODEUNIT));
            svg.appendChild(line);
        }
        OMSVGTextElement text1 = doc.createSVGTextElement(3 * BARCODEUNIT + 1, HEIGHT - 2, OMSVGLength.SVG_LENGTHTYPE_PX, String.valueOf(barcodeString.charAt(0)));
        text1.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
        text1.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_WIDTH_PROPERTY, String.valueOf(BARCODEUNIT));
        svg.appendChild(text1);
        for (int i = 1; i < 13; i++) {
            int idx = i < 7 ? (i - 1) * 7 + 14 : (i - 7) * 7 + 61;
            OMSVGTextElement text = doc.createSVGTextElement(idx * BARCODEUNIT + 1, HEIGHT - 2, OMSVGLength.SVG_LENGTHTYPE_PX, String.valueOf(barcodeString.charAt(i)));
            text.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
            text.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_WIDTH_PROPERTY, String.valueOf(BARCODEUNIT));
            svg.appendChild(text);
        }
        return svg;
    }

}
