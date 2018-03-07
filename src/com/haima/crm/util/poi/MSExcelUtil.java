package com.haima.crm.util.poi;

import java.util.Arrays;

public class MSExcelUtil {

    public static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;
    public static final int UNIT_OFFSET_LENGTH = 7;
    public static final int[] UNIT_OFFSET_MAP = new int[] { 0, 36, 73, 109, 146, 182, 219 };

    /**
     * pixel units to excel width units(units of 1/256th of a character width)
     *
     * @param pxs
     * @return
     */
    public static short pixel2WidthUnits(int pxs) {
        short widthUnits = (short) (EXCEL_COLUMN_WIDTH_FACTOR * (pxs / UNIT_OFFSET_LENGTH));
        widthUnits += UNIT_OFFSET_MAP[(pxs % UNIT_OFFSET_LENGTH)];
        return widthUnits;
    }

    /**
     * excel width units(units of 1/256th of a character width) to pixel units
     *
     * @param widthUnits
     * @return
     */
    public static int widthUnits2Pixel(int widthUnits) {
        int pixels = (widthUnits / EXCEL_COLUMN_WIDTH_FACTOR) * UNIT_OFFSET_LENGTH;
        int offsetWidthUnits = widthUnits % EXCEL_COLUMN_WIDTH_FACTOR;
        pixels += Math.round(offsetWidthUnits
                / ((float) EXCEL_COLUMN_WIDTH_FACTOR / UNIT_OFFSET_LENGTH));

        return pixels;
    }


    public static float[] rgb2hsb(int rgbR, int rgbG, int rgbB) {
        assert 0 <= rgbR && rgbR <= 255;
        assert 0 <= rgbG && rgbG <= 255;
        assert 0 <= rgbB && rgbB <= 255;
        int[] rgb = new int[] { rgbR, rgbG, rgbB };
        Arrays.sort(rgb);
        int max = rgb[2];
        int min = rgb[0];

        float hsbB = max / 255.0f;
        float hsbS = max == 0 ? 0 : (max - min) / (float) max;

        float hsbH = 0;
        if (max == rgbR && rgbG >= rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;
        } else if (max == rgbR && rgbG < rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;
        } else if (max == rgbG) {
            hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;
        } else if (max == rgbB) {
            hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;
        }

        return new float[] { hsbH, hsbS, hsbB };
    }

    public static int[] hsb2rgb(float h, float s, float v) {
        assert Float.compare(h, 0.0f) >= 0 && Float.compare(h, 360.0f) <= 0;
        assert Float.compare(s, 0.0f) >= 0 && Float.compare(s, 1.0f) <= 0;
        assert Float.compare(v, 0.0f) >= 0 && Float.compare(v, 1.0f) <= 0;

        float r = 0, g = 0, b = 0;
        int i = (int) ((h / 60) % 6);
        float f = (h / 60) - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);
        switch (i) {
            case 0:
                r = v;
                g = t;
                b = p;
                break;
            case 1:
                r = q;
                g = v;
                b = p;
                break;
            case 2:
                r = p;
                g = v;
                b = t;
                break;
            case 3:
                r = p;
                g = q;
                b = v;
                break;
            case 4:
                r = t;
                g = p;
                b = v;
                break;
            case 5:
                r = v;
                g = p;
                b = q;
                break;
            default:
                break;
        }
        return new int[] { (int) (r * 255.0), (int) (g * 255.0),
                (int) (b * 255.0) };
    }


    public static void main(String[] args) {
        float[] floats = rgb2hsb(91, 155, 213);
        System.out.println("RGB:》》》"+floats[0]+","+floats[1]+","+floats[2]);
    }
}