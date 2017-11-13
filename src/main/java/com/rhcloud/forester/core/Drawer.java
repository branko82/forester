package com.rhcloud.forester.core;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;

import java.io.IOException;

public class Drawer {

    private PdfContentByte canvas;
    private int diameter;

    public Drawer(PdfContentByte canvas, int diameter) {
        this.canvas = canvas;
        this.diameter = diameter;
    }

    public void drawLine(Line line) {
        canvas.moveTo(line.getStartPoint().getX(), line.getStartPoint().getY());
        canvas.lineTo(line.getEndPoint().getX(), line.getEndPoint().getY());
    }

    public void drawTriangle(Line line) {
        canvas.setColorFill(line.getColor());
        canvas.setColorStroke(line.getColor());
        canvas.moveTo(line.getEndPoint().getX(), line.getEndPoint().getY());
        Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle()-1, diameter-5);
        Point p2 = Position.getNextPoint(line.getStartPoint(), line.getAngle()+1, diameter-5);
        canvas.lineTo(p1.getX(), p1.getY());
        canvas.lineTo(p2.getX(), p2.getY());
        canvas.lineTo(line.getEndPoint().getX(), line.getEndPoint().getY());
        canvas.fillStroke();
    }

    public void drawCircle(Line line) {
        canvas.setColorFill(line.getColor());
        canvas.setColorStroke(line.getColor());
        canvas.circle(line.getEndPoint().getX(), line.getEndPoint().getY(), 2);
        canvas.fillStroke();
    }

    public void drawTextAbove(Line line, String text) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont();
        canvas.setColorFill(BaseColor.BLACK);
        canvas.beginText();
        canvas.setFontAndSize(bf, 7);
        if (line.getAngle() <  180 ) {
            Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle()-1, diameter-40);
            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, text, p1.getX(), p1.getY(), 90-line.getAngle());
        } else {
            Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle()+1, diameter-40);
            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, text, p1.getX(), p1.getY(), 270-line.getAngle());
        }
        canvas.endText();
    }

    public void drawTextBelow(Line line, String text) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont();
        canvas.setColorFill(BaseColor.BLACK);
        canvas.beginText();
        canvas.setFontAndSize(bf, 7);
        if (line.getAngle() <  180 ) {
            Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle()+6, diameter-40);
            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, text, p1.getX(), p1.getY(), 90-line.getAngle());
        } else {
            Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle()-6, diameter-40);
            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, text, p1.getX(), p1.getY(), 270-line.getAngle());
        }
        canvas.endText();
    }

    public void drawTextN(Line line) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont();
        canvas.setColorFill(BaseColor.BLACK);
        canvas.beginText();
        canvas.setFontAndSize(bf, 12);
        Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle(), diameter-40);
        //perhaps instead letter,  picture should be used or unicode character
        canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, "N", p1.getX(), p1.getY(), 0);
        canvas.endText();
    }

    public void drawTextCenterAndCenterPoint(Line line, String number) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont();
        canvas.setColorFill(BaseColor.BLACK);
        canvas.beginText();
        canvas.setFontAndSize(bf, 7);
        canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, "T", line.getStartPoint().getX()-10, line.getStartPoint().getY()-5, 0);
        canvas.setFontAndSize(bf, 5);
        canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, number, line.getStartPoint().getX()-5, line.getStartPoint().getY()-5, 0);
        canvas.endText();
        canvas.setColorFill(BaseColor.BLACK);
        canvas.setColorStroke(BaseColor.BLACK);
        canvas.circle(line.getStartPoint().getX(), line.getStartPoint().getY(), 1);
        canvas.fillStroke();
    }

    public void drawTextPoint(Line line, String number) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont();
        canvas.setColorFill(BaseColor.BLACK);
        canvas.beginText();
        canvas.setFontAndSize(bf, 7);
        Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle(), diameter+7);
        canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, number, p1.getX(), p1.getY(), 0);
        canvas.endText();
    }

    public void drawTextPoint(Line line, String letter, String number) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont();
        canvas.setColorFill(BaseColor.BLACK);
        canvas.beginText();
        canvas.setFontAndSize(bf, 7);
        Point p1 = Position.getNextPoint(line.getStartPoint(), line.getAngle(), diameter+7);
        canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, letter, p1.getX(), p1.getY(), 0);
        canvas.setFontAndSize(bf, 5);
        Point p2 = Position.getNextPoint(line.getStartPoint(), line.getAngle(), diameter+7);
        canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, number, p2.getX()+5, p2.getY(), 0);
        canvas.endText();
    }


}
