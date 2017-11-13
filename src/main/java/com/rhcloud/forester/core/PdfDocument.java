package com.rhcloud.forester.core;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.rhcloud.forester.model.Entry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfDocument {

    public static final String Destination = "/tmp/forester.pdf";
    private static final int diameter = 90;

    public static void main(String[] args) {
        PdfDocument pdfDocument = new PdfDocument();
        try {
            List<Entry> entries = new ArrayList<Entry>();
            for (int i=0; i<5; i++) {
                Entry e = new Entry();
                e.setPreviousPointAngle("210");
                e.setNextPointAngle("170");
                e.setPoint1Angle("100");
                e.setPoint2Angle("160");
                e.setPoint3Angle("230");
                e.setPoint1Length("40");
                e.setPoint2Length("50");
                e.setPoint3Length("60");

                e.setStartPoint("11");
                e.setNextPoint("12");
                e.setPreviousPoint("10");
                entries.add(e);
            }
            pdfDocument.create(entries);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void create( List<Entry> entries) throws IOException, DocumentException {

        File file = new File(Destination);
        file.getParentFile().mkdirs();
        float firstColumnPositionX = PageSize.A4.getWidth()*0.25f;
        float secondColumnPositionX = PageSize.A4.getWidth()*0.75f;
        float fourthRowPositionY = PageSize.A4.getHeight()*1/8;
        float thirdRowPositionY = PageSize.A4.getHeight()*3/8;
        float secondRowPositionY = PageSize.A4.getHeight()*5/8;
        float firstRowPositionY = PageSize.A4.getHeight()*7/8;

        List<Float> rowPositionY = new ArrayList<Float>();
        rowPositionY.add(firstRowPositionY);
        rowPositionY.add(secondRowPositionY);
        rowPositionY.add(thirdRowPositionY);
        rowPositionY.add(fourthRowPositionY);
        List<Float> columnPositionX = new ArrayList<Float>();
        columnPositionX.add(firstColumnPositionX);
        columnPositionX.add(secondColumnPositionX);

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(Destination));
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        BaseColor blackColor = BaseColor.BLACK;
        BaseColor grayColor = BaseColor.GRAY;

        canvas.setColorStroke(blackColor);
        Drawer drawer = new Drawer(canvas, diameter);
        Line lineNext;
        Point endPointNext;
        int cnt = 0;
        outerloop:
        for (int x=0; x<=1; x++) {
            for (int y=0; y<=3; y++) {
                if (cnt >= entries.size()) {
                    break outerloop;
                }
                cnt++;
                Point startPoint = new Point(columnPositionX.get(x), rowPositionY.get(y));
                endPointNext = new Point(columnPositionX.get(x), rowPositionY.get(y) + diameter);
                lineNext = new Line(startPoint, endPointNext, 0, blackColor);
                drawer.drawLine(lineNext);
                drawer.drawTriangle(lineNext);
                drawer.drawTextN(lineNext);
                Entry e = null;
                if (x==0) {
                    e = entries.get(y);
                } else {
                    e = entries.get(y+4);
                }
                endPointNext = Position.getNextPoint(startPoint, Integer.parseInt(e.getNextPointAngle()), diameter);
                lineNext = new Line(startPoint, endPointNext, Integer.parseInt(e.getNextPointAngle()), blackColor);
                drawer.drawLine(lineNext);
                drawer.drawTriangle(lineNext);
                drawer.drawTextPoint(lineNext, "T", e.getNextPoint());

                endPointNext = Position.getNextPoint(startPoint, Integer.parseInt(e.getPreviousPointAngle()), diameter);
                lineNext = new Line(startPoint, endPointNext, Integer.parseInt(e.getPreviousPointAngle()), blackColor);
                drawer.drawLine(lineNext);
                drawer.drawTriangle(lineNext);
                drawer.drawTextPoint(lineNext, "T", e.getPreviousPoint());

                endPointNext = Position.getNextPoint(startPoint, Integer.parseInt(e.getPoint1Angle()), diameter);
                lineNext = new Line(startPoint, endPointNext, Integer.parseInt(e.getPoint1Angle()), grayColor);
                drawer.drawLine(lineNext);
                drawer.drawCircle(lineNext);
                drawer.drawTextPoint(lineNext, "1");
                drawer.drawTextAbove(lineNext, e.getPoint1Length()+"m");
                drawer.drawTextBelow(lineNext, e.getPoint1Angle());
                drawer.drawTextCenterAndCenterPoint(lineNext, e.getStartPoint());

                endPointNext = Position.getNextPoint(startPoint, Integer.parseInt(e.getPoint2Angle()), diameter);
                lineNext = new Line(startPoint, endPointNext, Integer.parseInt(e.getPoint2Angle()), grayColor);
                drawer.drawLine(lineNext);
                drawer.drawCircle(lineNext);
                drawer.drawTextPoint(lineNext, "2");
                drawer.drawTextAbove(lineNext, e.getPoint2Length()+"m");
                drawer.drawTextBelow(lineNext, e.getPoint2Angle());
                drawer.drawTextCenterAndCenterPoint(lineNext, e.getStartPoint());

                endPointNext = Position.getNextPoint(startPoint, Integer.parseInt(e.getPoint3Angle()), diameter);
                lineNext = new Line(startPoint, endPointNext, Integer.parseInt(e.getPoint3Angle()), grayColor);
                drawer.drawLine(lineNext);
                drawer.drawCircle(lineNext);
                drawer.drawTextPoint(lineNext, "3");
                drawer.drawTextAbove(lineNext, e.getPoint3Length()+"m");
                drawer.drawTextBelow(lineNext, e.getPoint3Angle());
                drawer.drawTextCenterAndCenterPoint(lineNext, e.getStartPoint());

            }
        }











//        float azimuth1 = 60;
//        float azimuth2 = 150;
//        float azimuth3 = 240;
//        float azimuth4 = 330;
//        float azimuth5 = 30;
//        float azimuth6 = 130;

        //left vertical

//        endPointNext = Position.getNextPoint(startPointLeft, azimuth1, diameter);
//        lineNext = new Line(startPointLeft, endPointNext, azimuth1, blackColor);
//        drawer.drawLine(lineNext);
//        drawer.drawTriangle(lineNext);
//
//        endPointNext = Position.getNextPoint(startPointLeft, azimuth4, diameter);
//        lineNext = new Line(startPointLeft, endPointNext, azimuth4, grayColor);
//        drawer.drawLine(lineNext);
//        drawer.drawCircle(lineNext);
//        drawer.drawText(lineNext, "13.5m/100");
//        drawer.drawTextPoint(lineNext, "10");
//
//        endPointNext = Position.getNextPoint(startPointLeft, azimuth6, diameter);
//        lineNext = new Line(startPointLeft, endPointNext, azimuth6, grayColor);
//        drawer.drawLine(lineNext);
//        drawer.drawCircle(lineNext);
//        drawer.drawTextCenterAndCenterPoint(lineNext, "11");
//
//        //right vertical
//        Point startPointRight = new Point(secondColumnPositionX, firstRowPositionY);
//        endPointNext =new Point(secondColumnPositionX, firstRowPositionY+diameter);
//        lineNext = new Line(startPointRight, endPointNext, 0, blackColor);
//        drawer.drawLine(lineNext);
//        drawer.drawTriangle(lineNext);
//        drawer.drawTextN(lineNext);
//
//        endPointNext = Position.getNextPoint(startPointRight, azimuth2, diameter);
//        lineNext = new Line(startPointRight, endPointNext, azimuth2, blackColor);
//        drawer.drawLine(lineNext);
//        drawer.drawTriangle(lineNext);
//
//        endPointNext = Position.getNextPoint(startPointRight, azimuth3, diameter);
//        lineNext = new Line(startPointRight, endPointNext, azimuth3, grayColor);
//        drawer.drawLine(lineNext);
//        drawer.drawCircle(lineNext);
//        drawer.drawText(lineNext, "13.5m/100");
//
//        endPointNext = Position.getNextPoint(startPointRight, azimuth5, diameter);
//        lineNext = new Line(startPointRight, endPointNext, azimuth5, grayColor);
//        drawer.drawLine(lineNext);
//        drawer.drawCircle(lineNext);
//        drawer.drawText(lineNext, "13.5m/100");
//        drawer.drawTextCenterAndCenterPoint(lineNext, "12");

        canvas.closePathStroke();
        document.close();

    }

}